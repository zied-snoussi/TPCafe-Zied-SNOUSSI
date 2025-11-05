# CI/CD Pipeline Documentation

This document describes the Continuous Integration and Continuous Deployment (CI/CD) pipeline for the TPCafe project.

## Overview

The project uses **GitHub Actions** for automated CI/CD workflows. The pipeline includes:

1. **Build and Test** - Compile code and run tests
2. **Code Quality Analysis** - Static code analysis
3. **Docker Build** - Build and push Docker images
4. **Deployment** - Deploy to production (configurable)

## Workflows

### 1. CI/CD Pipeline (`ci-cd.yml`)

**Triggers:**
- Push to `main`, `master`, or `develop` branches
- Pull requests to these branches

**Jobs:**

#### Build and Test
- Checks out code
- Sets up JDK 17
- Builds with Maven
- Runs unit and integration tests
- Generates test reports
- Uploads build artifacts

#### Code Quality
- Runs static code analysis
- Optional SonarCloud integration

#### Docker Build
- Builds Docker image
- Pushes to Docker Hub (on main/master branch)
- Tags with branch name, commit SHA, and 'latest'

#### Deploy
- Deploys to production (on main/master branch)
- Requires manual approval (production environment)

### 2. Docker Publish (`docker-publish.yml`)

**Triggers:**
- New release published
- Manual workflow dispatch

**Actions:**
- Builds multi-platform Docker images (amd64, arm64)
- Pushes to Docker Hub with semantic versioning tags

### 3. Pull Request Check (`pr-check.yml`)

**Triggers:**
- Pull requests to main/master/develop

**Actions:**
- Validates Maven project
- Compiles code
- Runs tests
- Comments on PR with results

## Setup Instructions

### 1. GitHub Secrets

Add these secrets to your GitHub repository:

**Settings → Secrets and variables → Actions → New repository secret**

Required secrets:
- `DOCKER_USERNAME`: Your Docker Hub username
- `DOCKER_PASSWORD`: Your Docker Hub password or access token

Optional secrets (for deployment):
- `SERVER_HOST`: Production server hostname
- `SERVER_USER`: SSH username
- `SSH_PRIVATE_KEY`: SSH private key for deployment
- `SONAR_TOKEN`: SonarCloud token (if using code analysis)

### 2. Docker Hub Setup

1. Create a Docker Hub account at https://hub.docker.com
2. Create a repository named `tpcafe`
3. Generate an access token:
   - Account Settings → Security → New Access Token
   - Use this token as `DOCKER_PASSWORD` secret

### 3. Enable GitHub Actions

1. Go to repository Settings → Actions → General
2. Set "Workflow permissions" to "Read and write permissions"
3. Enable "Allow GitHub Actions to create and approve pull requests"

## Workflow Details

### Build Process

```yaml
- Checkout code
- Setup JDK 17 with Maven cache
- Build: ./mvnw clean install -DskipTests
- Test: ./mvnw test
- Upload artifacts
```

### Docker Build Process

```yaml
- Setup Docker Buildx
- Login to Docker Hub
- Extract metadata (tags, labels)
- Build multi-stage image
- Push to registry (if main/master)
- Cache layers for faster builds
```

### Deployment Process

The deployment step is a placeholder. Configure based on your infrastructure:

#### Option 1: Deploy to VPS/Server via SSH

```yaml
- name: Deploy to server
  uses: appleboy/ssh-action@master
  with:
    host: ${{ secrets.SERVER_HOST }}
    username: ${{ secrets.SERVER_USER }}
    key: ${{ secrets.SSH_PRIVATE_KEY }}
    script: |
      cd /opt/tpcafe
      docker-compose pull
      docker-compose up -d
      docker system prune -f
```

#### Option 2: Deploy to AWS ECS

```yaml
- name: Deploy to ECS
  uses: aws-actions/amazon-ecs-deploy-task-definition@v1
  with:
    task-definition: task-definition.json
    service: tpcafe-service
    cluster: tpcafe-cluster
```

#### Option 3: Deploy to Kubernetes

```yaml
- name: Deploy to Kubernetes
  run: |
    kubectl set image deployment/tpcafe \
      tpcafe=${{ secrets.DOCKER_USERNAME }}/tpcafe:${{ github.sha }}
```

## Docker Image Tags

The pipeline creates multiple tags:

- `latest` - Latest build from main/master
- `main` or `master` - Latest from respective branch
- `develop` - Latest from develop branch
- `sha-<commit>` - Specific commit
- `v1.0.0` - Semantic version (on release)

Example:
```
yourusername/tpcafe:latest
yourusername/tpcafe:main
yourusername/tpcafe:sha-abc123
yourusername/tpcafe:v1.0.0
```

## Branch Strategy

Recommended Git workflow:

```
main/master (production)
  ↑
develop (staging)
  ↑
feature/* (development)
```

- **feature/** branches: Development work
- **develop**: Integration and testing
- **main/master**: Production-ready code

## Running Workflows Manually

### Via GitHub UI
1. Go to Actions tab
2. Select workflow
3. Click "Run workflow"
4. Choose branch

### Via GitHub CLI
```bash
# Trigger CI/CD pipeline
gh workflow run ci-cd.yml

# Trigger Docker publish
gh workflow run docker-publish.yml
```

## Monitoring Workflows

### View Workflow Runs
```bash
# List recent runs
gh run list

# View specific run
gh run view <run-id>

# Watch run in real-time
gh run watch
```

### Notifications

Configure notifications:
- Settings → Notifications → Actions
- Choose: All, Failed only, or None

## Best Practices

1. **Always run tests locally before pushing**
   ```bash
   ./mvnw clean test
   ```

2. **Use feature branches**
   ```bash
   git checkout -b feature/new-feature
   ```

3. **Write meaningful commit messages**
   ```bash
   git commit -m "feat: add new endpoint for promotions"
   ```

4. **Tag releases properly**
   ```bash
   git tag -a v1.0.0 -m "Release version 1.0.0"
   git push origin v1.0.0
   ```

5. **Review workflow logs for failures**
   - Check Actions tab
   - Review failed steps
   - Fix issues before merging

## Troubleshooting

### Build Fails

```bash
# Run locally to reproduce
./mvnw clean install

# Check Java version
java -version  # Should be 17

# Clear Maven cache
rm -rf ~/.m2/repository
```

### Docker Push Fails

- Verify Docker Hub credentials in secrets
- Check repository name matches
- Ensure you have push permissions

### Tests Fail in CI but Pass Locally

- Check for environment-specific issues
- Verify test database configuration
- Look for timing issues in tests

### Deployment Fails

- Verify server credentials
- Check server connectivity
- Review deployment logs
- Ensure Docker is installed on target server

## Cost Optimization

GitHub Actions free tier:
- 2,000 minutes/month for private repos
- Unlimited for public repos

Tips to reduce usage:
- Cache Maven dependencies
- Use matrix builds sparingly
- Skip builds for documentation changes
- Use self-hosted runners for heavy workloads

## Security

1. **Never commit secrets**
   - Use GitHub Secrets
   - Use environment variables

2. **Scan for vulnerabilities**
   ```yaml
   - name: Run Trivy vulnerability scanner
     uses: aquasecurity/trivy-action@master
   ```

3. **Sign Docker images**
   ```yaml
   - name: Sign image
     uses: sigstore/cosign-installer@main
   ```

4. **Use least privilege**
   - Limit secret access
   - Use read-only tokens when possible

## Additional Resources

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Docker Hub](https://hub.docker.com)
- [Maven Documentation](https://maven.apache.org)
- [Spring Boot Docker Guide](https://spring.io/guides/topicals/spring-boot-docker)
