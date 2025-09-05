# Xcharge AI CSMS Platform

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Python 3.9+](https://img.shields.io/badge/python-3.9+-blue.svg)](https://www.python.org/downloads/)
[![React 18](https://img.shields.io/badge/React-18-blue.svg)](https://reactjs.org/)
[![FastAPI](https://img.shields.io/badge/FastAPI-0.104+-green.svg)](https://fastapi.tiangolo.com/)

> **Intelligent Electric Vehicle Charging Station Management System**

A modern, AI-powered electric vehicle charging station management platform with real-time monitoring, user management, payment processing, and advanced analytics capabilities.

## 🚀 Quick Start

### Prerequisites

- **Python 3.9+**
- **Node.js 16+**
- **PostgreSQL 13+**
- **Redis 6+**
- **Docker** (optional)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/ev-charging-csms.git
   cd ev-charging-csms
   ```

2. **Set up environment variables**
   ```bash
   cp env.example .env
   # Edit .env with your configuration
   ```

3. **Install dependencies**
   ```bash
   # Backend
   cd backend
   pip install -r requirements.txt
   
   # Frontend
   cd ../frontend
   npm install
   ```

4. **Start the services**
   ```bash
   # Using startup script (Windows)
   ./start.bat
   
   # Or using Docker Compose
   docker-compose up --build
   ```

5. **Access the application**
   - **Frontend**: http://localhost:3000
   - **Backend API**: http://localhost:8000
   - **API Documentation**: http://localhost:8000/docs

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend       │    │   Mobile App    │
│   (React)       │◄──►│   (FastAPI)     │◄──►│   (React Native)│
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Browser   │    │   PostgreSQL    │    │   App Store     │
│   (Chrome/Safari)│    │   + Redis       │    │   (iOS/Android) │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🎯 Key Features

### Core Functionality
- **🔌 Charging Station Management**: Real-time monitoring, power management, fault diagnosis
- **👥 User Management**: Registration, authentication, charging history, balance management
- **💳 Payment System**: Multiple payment methods, automatic settlement, invoice management
- **📊 Data Analytics**: Charging statistics, revenue analysis, usage trends
- **🗺️ Map Services**: Station location display, navigation, booking functionality
- **📱 Mobile App**: User app, charging station operation interface

### Technical Features
- **⚡ Real-time Communication**: WebSocket for live data streaming
- **🏗️ Microservices Architecture**: Modular design, easy to scale
- **🐳 Cloud Native**: Docker containerization support
- **🔄 High Availability**: Load balancing, fault tolerance
- **🔒 Security**: JWT authentication, data encryption, access control

## 🛠️ Technology Stack

### Backend
- **Framework**: FastAPI (Python 3.9+)
- **Database**: PostgreSQL + Redis
- **Message Queue**: RabbitMQ
- **Real-time**: WebSocket
- **Authentication**: JWT + OAuth2
- **Payments**: Alipay, WeChat Pay, UnionPay

### Frontend
- **Framework**: React 18 + TypeScript
- **UI Library**: Ant Design + Tailwind CSS
- **State Management**: Redux Toolkit
- **Maps**: Amap API
- **Charts**: ECharts

### Mobile
- **Framework**: React Native
- **Navigation**: React Navigation
- **State Management**: Redux Toolkit
- **Payments**: Native payment SDKs

### Infrastructure
- **Containerization**: Docker + Docker Compose
- **Orchestration**: Kubernetes
- **Monitoring**: Prometheus + Grafana
- **Logging**: ELK Stack
- **CI/CD**: GitHub Actions

## 📁 Project Structure

```
ev-charging-csms/
├── backend/                 # Backend services
│   ├── app/
│   │   ├── core/           # Core configuration
│   │   ├── api/            # API routes
│   │   ├── models/         # Data models
│   │   ├── schemas/        # Data schemas
│   │   ├── services/       # Business logic
│   │   └── utils/          # Utility functions
│   ├── requirements.txt    # Python dependencies
│   ├── Dockerfile         # Docker configuration
│   └── main.py            # Main application
├── frontend/               # Web frontend
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── pages/          # Page components
│   │   ├── services/       # API services
│   │   └── utils/          # Utility functions
│   ├── package.json        # Node.js dependencies
│   └── Dockerfile         # Docker configuration
├── mobile/                 # Mobile app (React Native)
├── docs/                   # Documentation
├── scripts/                # Deployment scripts
├── k8s/                    # Kubernetes configuration
├── docker/                 # Docker configuration
├── docker-compose.yml      # Docker Compose configuration
├── start.bat              # Startup script
└── README.md              # Project documentation
```

## 🔧 Development

### API Design
- Follows RESTful design principles
- Unified response format
- Complete error handling
- Detailed API documentation

### Database Design
- Normalized design
- Index optimization
- Foreign key constraints
- Data integrity

### Security Design
- JWT token authentication
- Encrypted password storage
- API permission control
- Encrypted data transmission

## 📊 Core Modules

### 1. Charging Station Management
- Station registration and configuration
- Real-time status monitoring
- Fault diagnosis and alerts
- Remote control and maintenance

### 2. User System
- User registration and login
- Identity authentication and authorization
- Vehicle management
- Charging history query

### 3. Payment System
- Multiple payment methods
- Automatic billing
- Invoice management
- Refund processing

### 4. Data Analytics
- Charging data statistics
- Revenue analysis
- User behavior analysis
- Equipment utilization analysis

### 5. Map Services
- Charging station location display
- Route planning
- Booking functionality
- Real-time status updates

## 🚀 Deployment

### Docker Deployment
```bash
# Build and run with Docker Compose
docker-compose up --build

# Run in production mode
docker-compose -f docker-compose.prod.yml up -d
```

### Kubernetes Deployment
```bash
# Apply Kubernetes configurations
kubectl apply -f k8s/

# Check deployment status
kubectl get pods
```

### AWS Deployment
The application is currently deployed on AWS with the following services:
- **EC2**: Application servers
- **RDS**: PostgreSQL database
- **ElastiCache**: Redis cache
- **S3**: File storage
- **CloudFront**: CDN

## 📈 Roadmap

### Phase 1: Core Features ✅
- [x] Project architecture design
- [x] User authentication system
- [x] Charging station management
- [x] Basic API development

### Phase 2: Advanced Features 🚧
- [ ] Payment system integration
- [ ] Real-time monitoring
- [ ] Map services
- [ ] Mobile app development

### Phase 3: AI Features 📋
- [ ] Data analytics
- [ ] Smart recommendations
- [ ] Fault prediction
- [ ] Performance optimization

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

1. Fork the project
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

- **Documentation**: [Project Wiki](https://github.com/yourusername/ev-charging-csms/wiki)
- **Issues**: [GitHub Issues](https://github.com/yourusername/ev-charging-csms/issues)
- **Discussions**: [GitHub Discussions](https://github.com/yourusername/ev-charging-csms/discussions)

## 🎯 Business Value

### For Operators
- Increase charging station utilization
- Reduce operational costs
- Improve user experience
- Increase revenue

### For Users
- Convenient charging services
- Transparent pricing information
- Multiple payment methods
- Excellent user experience

### For Industry
- Promote EV adoption
- Advance charging infrastructure
- Improve industry standards
- Support green transportation

---

**Making Electric Vehicle Charging Smarter and More Convenient!** ⚡🚗

## 📚 Additional Documentation

- [Getting Started Guide](GETTING_STARTED.md)
- [Project Overview](PROJECT_OVERVIEW.md)
- [API Documentation](http://localhost:8000/docs)
- [Deployment Guide](docs/deployment.md)
- [Development Guide](docs/development.md)