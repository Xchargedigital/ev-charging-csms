# Getting Started with Xcharge AI CSMS Platform

## 🚀 Quick Setup

### Option 1: Using Startup Script (Recommended)
```bash
# Double-click start.bat or run in terminal
start.bat
```

### Option 2: Manual Setup

#### 1. Backend Setup
```bash
cd backend
python -m venv venv
venv\Scripts\activate  # Windows
# source venv/bin/activate  # Linux/Mac
pip install -r requirements.txt
python main.py
```

#### 2. Frontend Setup
```bash
cd frontend
npm install
npm start
```

#### 3. Database Setup
```bash
# Install PostgreSQL and Redis
# Update DATABASE_URL in .env file
# Run database migrations
```

### Option 3: Docker Setup
```bash
docker-compose up --build
```

## 🌐 Access Points

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8000
- **API Documentation**: http://localhost:8000/docs
- **Health Check**: http://localhost:8000/health

## 📋 Prerequisites

- Python 3.9+
- Node.js 16+
- PostgreSQL 13+
- Redis 6+
- Docker (optional)

## 🔧 Configuration

1. Copy `env.example` to `.env`
2. Update database and API keys
3. Run the application

## 📚 Next Steps

1. Explore the API documentation
2. Check the project structure
3. Review the development guide
4. Start building features

## 🆘 Troubleshooting

### Common Issues

1. **Port already in use**: Change ports in configuration
2. **Database connection failed**: Check PostgreSQL is running
3. **Dependencies not found**: Run `pip install -r requirements.txt`

### Getting Help

- Check the logs for error messages
- Review the API documentation
- Contact the development team

---

**Happy Coding!** 🚀
