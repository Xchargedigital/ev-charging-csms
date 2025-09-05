@echo off
chcp 65001 >nul
echo ========================================
echo   Xcharge AI CSMS Platform Startup Script
echo ========================================
echo.

echo Starting Xcharge AI CSMS Platform...
echo.

cd /d "%~dp0"

echo Choose startup method:
echo 1. Development Environment (Docker Compose)
echo 2. Local Development (Requires dependencies)
echo 3. Production Environment (Docker Compose)
echo.

set /p choice=Please select (1-3): 

if "%choice%"=="1" (
    echo.
    echo 启动开发环境...
    docker-compose up --build
) else if "%choice%"=="2" (
    echo.
    echo 启动本地开发环境...
    echo.
    echo 启动后端服务...
    start "Backend" cmd /k "cd backend && python -m venv venv && venv\Scripts\activate && pip install -r requirements.txt && python main.py"
    echo.
    echo 等待后端启动...
    timeout /t 5 /nobreak
    echo.
    echo 启动前端服务...
    start "Frontend" cmd /k "cd frontend && npm install && npm start"
    echo.
    echo 服务已启动!
    echo 后端: http://localhost:8000
    echo 前端: http://localhost:3000
) else if "%choice%"=="3" (
    echo.
    echo 启动生产环境...
    docker-compose -f docker-compose.prod.yml up --build -d
    echo.
    echo 生产环境已启动!
    echo 访问: http://localhost
) else (
    echo 无效选择，退出...
    pause
    exit
)

echo.
echo 按任意键退出...
pause >nul
