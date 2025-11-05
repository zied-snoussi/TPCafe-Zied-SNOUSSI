# 🚀 Quick Start Guide

Get TPCafe up and running in minutes!

## Choose Your Path

### 🐳 Docker (Recommended - Easiest)

**Prerequisites:** Docker Desktop installed

```bash
# 1. Clone the repository
git clone https://github.com/yourusername/TPCafe-Zied-SNOUSSI.git
cd TPCafe-Zied-SNOUSSI

# 2. Start everything
docker-compose up -d

# 3. Wait ~30 seconds for services to start

# 4. Access the application
# API: http://localhost:8081/api
# Swagger: http://localhost:8081/api/swagger-ui.html
```

**That's it!** ✅ MySQL and Spring Boot are running.

---

### 💻 Local Development

**Prerequisites:** Java 17, Maven, MySQL

```bash
# 1. Clone the repository
git clone https://github.com/yourusername/TPCafe-Zied-SNOUSSI.git
cd TPCafe-Zied-SNOUSSI

# 2. Create database
mysql -u root -p
CREATE DATABASE DB_Cafe;
exit;

# 3. Configure application.properties
# Edit src/main/resources/application.properties
# Update database credentials

# 4. Build and run
./mvnw clean install
./mvnw spring-boot:run

# 5. Access the application
# API: http://localhost:8081/api
# Swagger: http://localhost:8081/api/swagger-ui.html
```

---

## First API Call

Test the API with curl:

```bash
# Get all clients
curl http://localhost:8081/api/clients

# Create a client
curl -X POST http://localhost:8081/api/clients \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Doe",
    "prenom": "John",
    "dateNaissance": "1990-01-15"
  }'
```

Or use Swagger UI at http://localhost:8081/api/swagger-ui.html

---

## Useful Commands

### Docker

```bash
# View logs
docker-compose logs -f

# Stop services
docker-compose down

# Restart
docker-compose restart

# Clean restart (removes data)
docker-compose down -v
docker-compose up -d
```

### Maven

```bash
# Run tests
./mvnw test

# Clean build
./mvnw clean install

# Skip tests
./mvnw clean install -DskipTests

# Run application
./mvnw spring-boot:run
```

---

## Next Steps

1. ✅ **Explore the API** - Use Swagger UI to test endpoints
2. 📖 **Read the docs** - Check [README.md](README.md) for detailed info
3. 🐳 **Learn Docker** - See [DOCKER.md](DOCKER.md) for advanced usage
4. 🚀 **Setup CI/CD** - Review [CI-CD.md](CI-CD.md) for automation
5. 🌐 **Deploy** - Check [DEPLOYMENT.md](DEPLOYMENT.md) for production

---

## Troubleshooting

### Port already in use

**Problem:** Port 8081 or 3307 is already in use

**Solution:** 
```bash
# Option 1: Stop conflicting service
# Option 2: Change port in docker-compose.yml
ports:
  - "8082:8081"  # Change 8081 to 8082
```

### Database connection failed

**Problem:** Can't connect to MySQL

**Solution:**
```bash
# Check MySQL is running
docker-compose ps

# View MySQL logs
docker-compose logs mysql

# Restart MySQL
docker-compose restart mysql
```

### Application won't start

**Problem:** Spring Boot fails to start

**Solution:**
```bash
# View application logs
docker-compose logs app

# Check if database is ready
docker exec -it tpcafe-mysql mysql -u tpcafe_user -ptpcafe_pass -e "SHOW DATABASES;"

# Rebuild and restart
docker-compose down
docker-compose up -d --build
```

---

## Need Help?

- 📖 **Documentation**: [README.md](README.md)
- 🐳 **Docker Guide**: [DOCKER.md](DOCKER.md)
- 🚀 **CI/CD Guide**: [CI-CD.md](CI-CD.md)
- 🌐 **Deployment**: [DEPLOYMENT.md](DEPLOYMENT.md)
- 🐛 **Issues**: [GitHub Issues](https://github.com/yourusername/TPCafe-Zied-SNOUSSI/issues)

---

## API Endpoints Overview

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/clients` | GET | Get all clients |
| `/api/clients/{id}` | GET | Get client by ID |
| `/api/clients` | POST | Create client |
| `/api/clients` | PUT | Update client |
| `/api/clients/{id}` | DELETE | Delete client |
| `/api/articles` | GET | Get all articles |
| `/api/commandes` | GET | Get all orders |
| `/api/promotions` | GET | Get all promotions |
| `/api/swagger-ui.html` | GET | API Documentation |
| `/api/actuator/health` | GET | Health Check |

For complete API documentation, visit: http://localhost:8081/api/swagger-ui.html

---

**Happy Coding! ☕**
