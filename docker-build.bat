@echo off
REM Docker Build Script for TPCafe (Windows)

echo Building TPCafe Docker Image
echo ==================================

REM Get version from pom.xml
for /f "tokens=2 delims=<>" %%a in ('findstr /C:"<version>" pom.xml') do (
    set VERSION=%%a
    goto :version_found
)
:version_found

REM Get git commit hash
for /f %%i in ('git rev-parse --short HEAD 2^>nul') do set GIT_COMMIT=%%i
if "%GIT_COMMIT%"=="" set GIT_COMMIT=unknown

echo Image Name: tpcafe
echo Version: %VERSION%
echo Git Commit: %GIT_COMMIT%
echo ==================================

echo Building Docker image...
docker build ^
  -t tpcafe:latest ^
  -t tpcafe:%VERSION% ^
  -t tpcafe:%GIT_COMMIT% ^
  .

if %ERRORLEVEL% EQU 0 (
    echo.
    echo [SUCCESS] Docker image built successfully!
    echo.
    echo Available tags:
    echo   - tpcafe:latest
    echo   - tpcafe:%VERSION%
    echo   - tpcafe:%GIT_COMMIT%
    echo.
    echo To run the container:
    echo   docker-compose up -d
) else (
    echo.
    echo [ERROR] Docker build failed!
    exit /b 1
)
