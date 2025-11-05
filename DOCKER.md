# Docker Setup Guide for TPCafe

This guide explains how to build and run the TPCafe application using Docker.

## Prerequisites

- Docker Desktop installed (Windows/Mac) or Docker Engine (Linux)
- Docker Compose installed (usually comes with Docker Desktop)
- At least 4GB of RAM allocated to Docker

## Quick Start

### 1. Build and Run with Docker Compose

```bash
# Build and start all services (MySQL + Spring Boot App)
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down

# Stop and remove volumes (clean database)
docker-compose down -v
```

### 2. Access the Application

- **API Base URL**: http://localhost:8081/api
- **Swagger UI**: http://localhost:8081/api/swagger-ui.html
- **API Docs**: http://localhost:8081/api/api-docs
- **Health Check**: http://localhost:8081/api/actuator/health
- **MySQL**: localhost:3307 (username: tpcafe_user, password: tpcafe_pass)

## Docker Commands

### Build Docker Image Manually

```bash
# Build the image
docker build -t tpcafe:latest .

# Run the container (requires MySQL running)
docker run -p 8081:8081 \
  -e SPRING_PROFILES_ACTIVE=docker \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3307/tpcafe \
  tpcafe:latest
```

### Development Mode

For development with only MySQL in Docker:

```bash
# Start only MySQL
docker-compose -f docker-compose.dev.yml up -d

# Run Spring Boot locally
./mvnw spring-boot:run
```

### Docker Compose Commands

```bash
# Build images
docker-compose build

# Start services in background
docker-compose up -d

# View logs for specific service
docker-compose logs -f app
docker-compose logs -f mysql

# Restart a service
docker-compose restart app

# Stop services
docker-compose stop

# Remove containers and networks
docker-compose down

# Remove everything including volumes
docker-compose down -v

# Scale the application (multiple instances)
docker-compose up -d --scale app=3
```

## Environment Variables

You can override default values using environment variables:

```bash
# Example with custom database credentials
SPRING_DATASOURCE_USERNAME=myuser \
SPRING_DATASOURCE_PASSWORD=mypass \
docker-compose up -d
```

Available environment variables:
- `SPRING_PROFILES_ACTIVE`: Active Spring profile (default: docker)
- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password

## Troubleshooting

### Container won't start

```bash
# Check container logs
docker-compose logs app

# Check if MySQL is ready
docker-compose logs mysql

# Restart services
docker-compose restart
```

### Database connection issues

```bash
# Verify MySQL is healthy
docker-compose ps

# Test MySQL connection
docker exec -it tpcafe-mysql mysql -u tpcafe_user -ptpcafe_pass tpcafe
```

### Port already in use

```bash
# Change ports in docker-compose.yml or stop conflicting services
# For MySQL: Change "3307:3306" to "3308:3306"
# For App: Change "8081:8081" to "8082:8081"
```

### Clean restart

```bash
# Remove everything and start fresh
docker-compose down -v
docker-compose up -d --build
```

## Production Deployment

For production deployment:

1. **Use environment-specific configuration**
   ```bash
   docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d
   ```

2. **Use secrets for sensitive data**
   - Don't hardcode passwords
   - Use Docker secrets or environment variables from secure sources

3. **Enable monitoring**
   - Add Prometheus/Grafana for metrics
   - Configure log aggregation (ELK stack)

4. **Set resource limits**
   ```yaml
   deploy:
     resources:
       limits:
         cpus: '1'
         memory: 1G
   ```

## Health Checks

The application includes health checks:

```bash
# Check application health
curl http://localhost:8081/api/actuator/health

# Check database connectivity
curl http://localhost:8081/api/actuator/health/db
```

## Backup and Restore

### Backup MySQL Database

```bash
# Backup
docker exec tpcafe-mysql mysqldump -u tpcafe_user -ptpcafe_pass tpcafe > backup.sql

# Restore
docker exec -i tpcafe-mysql mysql -u tpcafe_user -ptpcafe_pass tpcafe < backup.sql
```

## Multi-stage Build Benefits

The Dockerfile uses multi-stage builds:
- **Stage 1**: Build the application with Maven
- **Stage 2**: Create minimal runtime image with only JRE

Benefits:
- Smaller final image size (~200MB vs ~800MB)
- Faster deployment
- Better security (no build tools in production image)
- Cached layers for faster rebuilds
