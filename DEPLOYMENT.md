# Deployment Guide

This guide covers various deployment strategies for the TPCafe application.

## Table of Contents

- [Local Deployment](#local-deployment)
- [Docker Deployment](#docker-deployment)
- [Cloud Deployment](#cloud-deployment)
- [Production Checklist](#production-checklist)

## Local Deployment

### Development Mode

```bash
# Start MySQL only
docker-compose -f docker-compose.dev.yml up -d

# Run application locally
./mvnw spring-boot:run
```

### Production Mode (Local)

```bash
# Build JAR
./mvnw clean package -DskipTests

# Run JAR
java -jar target/TPCafe-Zied-SNOUSSI-0.0.1-SNAPSHOT.jar
```

## Docker Deployment

### Single Server Deployment

```bash
# On your server
git clone https://github.com/yourusername/TPCafe-Zied-SNOUSSI.git
cd TPCafe-Zied-SNOUSSI

# Create .env file
cp .env.example .env
# Edit .env with your credentials

# Start services
docker-compose up -d

# Check status
docker-compose ps

# View logs
docker-compose logs -f
```

### Docker Swarm (Multi-node)

```bash
# Initialize swarm
docker swarm init

# Deploy stack
docker stack deploy -c docker-compose.yml -c docker-compose.prod.yml tpcafe

# Check services
docker stack services tpcafe

# Scale application
docker service scale tpcafe_app=3

# Remove stack
docker stack rm tpcafe
```

## Cloud Deployment

### AWS Deployment

#### Option 1: AWS EC2

```bash
# 1. Launch EC2 instance (Ubuntu 22.04)
# 2. SSH into instance
ssh -i your-key.pem ubuntu@your-ec2-ip

# 3. Install Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
sudo usermod -aG docker ubuntu

# 4. Install Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# 5. Clone and deploy
git clone https://github.com/yourusername/TPCafe-Zied-SNOUSSI.git
cd TPCafe-Zied-SNOUSSI
docker-compose up -d
```

#### Option 2: AWS ECS (Elastic Container Service)

1. **Create ECR Repository**
```bash
aws ecr create-repository --repository-name tpcafe
```

2. **Build and Push Image**
```bash
# Login to ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com

# Build and tag
docker build -t tpcafe .
docker tag tpcafe:latest YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/tpcafe:latest

# Push
docker push YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/tpcafe:latest
```

3. **Create ECS Task Definition**
```json
{
  "family": "tpcafe",
  "networkMode": "awsvpc",
  "requiresCompatibilities": ["FARGATE"],
  "cpu": "512",
  "memory": "1024",
  "containerDefinitions": [
    {
      "name": "tpcafe",
      "image": "YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/tpcafe:latest",
      "portMappings": [
        {
          "containerPort": 8081,
          "protocol": "tcp"
        }
      ],
      "environment": [
        {
          "name": "SPRING_PROFILES_ACTIVE",
          "value": "prod"
        }
      ]
    }
  ]
}
```

#### Option 3: AWS Elastic Beanstalk

```bash
# Install EB CLI
pip install awsebcli

# Initialize
eb init -p docker tpcafe

# Create environment
eb create tpcafe-prod

# Deploy
eb deploy

# Open in browser
eb open
```

### Google Cloud Platform (GCP)

#### Cloud Run

```bash
# Build and submit
gcloud builds submit --tag gcr.io/YOUR_PROJECT_ID/tpcafe

# Deploy
gcloud run deploy tpcafe \
  --image gcr.io/YOUR_PROJECT_ID/tpcafe \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated \
  --port 8081
```

#### GKE (Google Kubernetes Engine)

```bash
# Create cluster
gcloud container clusters create tpcafe-cluster --num-nodes=3

# Get credentials
gcloud container clusters get-credentials tpcafe-cluster

# Deploy
kubectl apply -f k8s/
```

### Microsoft Azure

#### Azure Container Instances

```bash
# Create resource group
az group create --name tpcafe-rg --location eastus

# Create container
az container create \
  --resource-group tpcafe-rg \
  --name tpcafe \
  --image yourusername/tpcafe:latest \
  --dns-name-label tpcafe \
  --ports 8081
```

#### Azure App Service

```bash
# Create app service plan
az appservice plan create --name tpcafe-plan --resource-group tpcafe-rg --is-linux

# Create web app
az webapp create --resource-group tpcafe-rg --plan tpcafe-plan --name tpcafe --deployment-container-image-name yourusername/tpcafe:latest
```

### Heroku

```bash
# Login
heroku login

# Create app
heroku create tpcafe

# Add MySQL addon
heroku addons:create cleardb:ignite

# Get database URL
heroku config:get CLEARDB_DATABASE_URL

# Deploy
heroku container:push web
heroku container:release web

# Open app
heroku open
```

### DigitalOcean

#### Droplet

```bash
# 1. Create droplet with Docker pre-installed
# 2. SSH into droplet
ssh root@your-droplet-ip

# 3. Clone and deploy
git clone https://github.com/yourusername/TPCafe-Zied-SNOUSSI.git
cd TPCafe-Zied-SNOUSSI
docker-compose up -d
```

#### App Platform

```bash
# Install doctl
snap install doctl

# Authenticate
doctl auth init

# Create app
doctl apps create --spec app-spec.yaml
```

## Production Checklist

### Before Deployment

- [ ] Update all dependencies to latest stable versions
- [ ] Run all tests (`./mvnw test`)
- [ ] Set strong database passwords
- [ ] Configure SSL/TLS certificates
- [ ] Set up domain name and DNS
- [ ] Configure firewall rules
- [ ] Set up monitoring and logging
- [ ] Configure backup strategy
- [ ] Review security settings
- [ ] Set environment-specific configurations

### Security

```properties
# application-prod.properties
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=validate
management.endpoints.web.exposure.include=health,info
server.error.include-stacktrace=never
```

### Environment Variables

```bash
# Production environment variables
export SPRING_PROFILES_ACTIVE=prod
export SPRING_DATASOURCE_URL=jdbc:mysql://prod-db:3306/tpcafe
export SPRING_DATASOURCE_USERNAME=prod_user
export SPRING_DATASOURCE_PASSWORD=strong_password
export SERVER_PORT=8081
```

### SSL/TLS Configuration

```yaml
# docker-compose with SSL
services:
  nginx:
    image: nginx:alpine
    ports:
      - "80:80"
      - "443:443"
    volumes:
      - ./nginx.conf:/etc/nginx/nginx.conf
      - ./ssl:/etc/nginx/ssl
    depends_on:
      - app
```

### Monitoring

```yaml
# Add Prometheus and Grafana
services:
  prometheus:
    image: prom/prometheus
    ports:
      - "9090:9090"
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml
      
  grafana:
    image: grafana/grafana
    ports:
      - "3000:3000"
    depends_on:
      - prometheus
```

### Backup Strategy

```bash
# Automated MySQL backup
#!/bin/bash
BACKUP_DIR="/backups"
DATE=$(date +%Y%m%d_%H%M%S)

docker exec tpcafe-mysql mysqldump -u root -p$MYSQL_ROOT_PASSWORD tpcafe > $BACKUP_DIR/tpcafe_$DATE.sql

# Keep only last 7 days
find $BACKUP_DIR -name "tpcafe_*.sql" -mtime +7 -delete
```

### Load Balancing

```yaml
# nginx.conf for load balancing
upstream tpcafe_backend {
    server app1:8081;
    server app2:8081;
    server app3:8081;
}

server {
    listen 80;
    location / {
        proxy_pass http://tpcafe_backend;
    }
}
```

### Health Checks

```bash
# Health check script
#!/bin/bash
HEALTH_URL="http://localhost:8081/api/actuator/health"

if curl -f $HEALTH_URL > /dev/null 2>&1; then
    echo "Application is healthy"
    exit 0
else
    echo "Application is unhealthy"
    exit 1
fi
```

### Rollback Strategy

```bash
# Tag current version
docker tag tpcafe:latest tpcafe:v1.0.0

# Deploy new version
docker-compose pull
docker-compose up -d

# If issues, rollback
docker tag tpcafe:v1.0.0 tpcafe:latest
docker-compose up -d
```

## Post-Deployment

### Verification

```bash
# Check application health
curl http://your-domain/api/actuator/health

# Check API endpoints
curl http://your-domain/api/clients

# Check Swagger UI
open http://your-domain/api/swagger-ui.html
```

### Monitoring

```bash
# View logs
docker-compose logs -f app

# Check resource usage
docker stats

# Monitor database
docker exec -it tpcafe-mysql mysql -u root -p -e "SHOW PROCESSLIST;"
```

## Troubleshooting

### Common Issues

1. **Application won't start**
   - Check logs: `docker-compose logs app`
   - Verify database connection
   - Check environment variables

2. **Database connection failed**
   - Verify MySQL is running: `docker-compose ps`
   - Check credentials
   - Verify network connectivity

3. **Out of memory**
   - Increase container memory limits
   - Check for memory leaks
   - Optimize JVM settings

4. **Slow performance**
   - Enable database query caching
   - Add database indexes
   - Scale horizontally

## Support

For deployment issues:
- Check logs: `docker-compose logs -f`
- Review [DOCKER.md](DOCKER.md)
- Review [CI-CD.md](CI-CD.md)
- Open an issue on GitHub
