#!/bin/bash

# Docker Build Script for TPCafe
# This script builds the Docker image with proper tagging

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Configuration
IMAGE_NAME="tpcafe"
VERSION=$(grep -m 1 '<version>' pom.xml | sed 's/.*<version>\(.*\)<\/version>/\1/')
GIT_COMMIT=$(git rev-parse --short HEAD 2>/dev/null || echo "unknown")
BUILD_DATE=$(date -u +'%Y-%m-%dT%H:%M:%SZ')

echo -e "${GREEN}Building TPCafe Docker Image${NC}"
echo "=================================="
echo "Image Name: $IMAGE_NAME"
echo "Version: $VERSION"
echo "Git Commit: $GIT_COMMIT"
echo "Build Date: $BUILD_DATE"
echo "=================================="

# Build the image
echo -e "${YELLOW}Building Docker image...${NC}"
docker build \
  --build-arg VERSION=$VERSION \
  --build-arg GIT_COMMIT=$GIT_COMMIT \
  --build-arg BUILD_DATE=$BUILD_DATE \
  -t $IMAGE_NAME:latest \
  -t $IMAGE_NAME:$VERSION \
  -t $IMAGE_NAME:$GIT_COMMIT \
  .

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Docker image built successfully!${NC}"
    echo ""
    echo "Available tags:"
    echo "  - $IMAGE_NAME:latest"
    echo "  - $IMAGE_NAME:$VERSION"
    echo "  - $IMAGE_NAME:$GIT_COMMIT"
    echo ""
    echo "To run the container:"
    echo "  docker-compose up -d"
    echo ""
    echo "To push to Docker Hub:"
    echo "  docker tag $IMAGE_NAME:latest yourusername/$IMAGE_NAME:latest"
    echo "  docker push yourusername/$IMAGE_NAME:latest"
else
    echo -e "${RED}✗ Docker build failed!${NC}"
    exit 1
fi
