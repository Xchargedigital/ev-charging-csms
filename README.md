# XCharge Digital CSMS Platform

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java 11](https://img.shields.io/badge/Java-11-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-5.3+-green.svg)](https://spring.io/projects/spring-boot)
[![OCPP](https://img.shields.io/badge/OCPP-1.2%2C%201.5%2C%201.6-orange.svg)](https://www.openchargealliance.org/)

> **Intelligent Electric Vehicle Charging Station Management System**

A comprehensive, Java-based Charging Station Management System (CSMS) built on the SteVe platform, supporting OCPP 1.2, 1.5, and 1.6 protocols with advanced features for EV charging infrastructure management.

## 🚀 Quick Start

### Prerequisites

- **Java 11+**
- **Maven 3.6+**
- **MySQL 8.0+**
- **Docker** (optional)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Xchargedigital/ev-charging-csms.git
   cd ev-charging-csms
   ```

2. **Set up database**
   ```bash
   # Create MySQL database
   mysql -u root -p
   CREATE DATABASE xcharge_csms;
   ```

3. **Configure application**
   ```bash
   # Copy and edit configuration
   cp backend/src/main/resources/config/prod/main.properties backend/src/main/resources/config/prod/main.properties.local
   # Edit database connection settings
   ```

4. **Build and run**
   ```bash
   # Build the project
   cd backend
   mvn clean package -DskipTests
   
   # Run the application
   java -jar target/cms.jar
   ```

5. **Access the application**
   - **Web Interface**: http://localhost:8080/steve
   - **OCPP Endpoint**: ws://localhost:8080/steve/websocket/CentralSystemService
   - **REST API**: http://localhost:8080/steve/api

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Frontend  │    │   CSMS Server   │    │   Mobile App    │
│   (JSP/JS)      │◄──►│   (Java/Spring) │◄──►│   (React Native)│
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Browser   │    │   MySQL DB      │    │   App Store     │
│   (Chrome/Safari)│    │   + JOOQ        │    │   (iOS/Android) │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🎯 Key Features

### Core CSMS Functionality
- **🔌 OCPP Protocol Support**: Full support for OCPP 1.2, 1.5, and 1.6
- **⚡ Charging Station Management**: Real-time monitoring, remote control, diagnostics
- **👥 User Management**: OCPP tag management, user authentication, access control
- **📊 Transaction Management**: Charging session tracking, billing, reporting
- **🔧 Configuration Management**: Remote configuration, firmware updates
- **📱 Web Interface**: Modern web-based management dashboard

### Advanced Features
- **🌐 Multi-Protocol Support**: SOAP and JSON WebSocket communication
- **🔒 Security**: Authentication, authorization, secure communication
- **📈 Analytics**: Usage statistics, performance monitoring, reporting
- **🔄 Real-time Updates**: WebSocket-based live status updates
- **🗄️ Data Management**: Comprehensive data storage and retrieval
- **🚀 Scalability**: High-performance architecture for large deployments

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 5.3+ with Spring MVC
- **Database**: MySQL 8.0+ with JOOQ ORM
- **Web Server**: Jetty 9.4+
- **Protocol**: OCPP 1.2, 1.5, 1.6 (SOAP & JSON)
- **Security**: Spring Security
- **Build Tool**: Maven 3.6+

### Frontend
- **Technology**: JSP + jQuery + Bootstrap
- **UI Components**: Custom CSMS management interface
- **Charts**: Real-time status monitoring
- **Responsive**: Mobile-friendly design

### Infrastructure
- **Containerization**: Docker + Docker Compose
- **Orchestration**: Kubernetes
- **Monitoring**: Log4j2 logging
- **CI/CD**: Maven-based build pipeline

## 📁 Project Structure

```
ev-charging-csms/
├── backend/                    # CSMS Server (Java/Spring)
│   ├── src/main/java/com/xcharge/digital/cms/
│   │   ├── Application.java           # Main application class
│   │   ├── ocpp/                     # OCPP protocol implementation
│   │   │   ├── ws/                   # WebSocket handlers
│   │   │   └── ...                   # Protocol-specific code
│   │   ├── web/                      # Web controllers
│   │   │   ├── controller/           # MVC controllers
│   │   │   ├── api/                  # REST API endpoints
│   │   │   └── dto/                  # Data transfer objects
│   │   ├── service/                  # Business logic services
│   │   ├── repository/               # Data access layer
│   │   │   ├── impl/                 # Repository implementations
│   │   │   └── dto/                  # Database DTOs
│   │   ├── config/                   # Configuration classes
│   │   └── utils/                    # Utility functions
│   ├── src/main/resources/
│   │   ├── config/                   # Environment configurations
│   │   │   ├── dev/                  # Development config
│   │   │   ├── prod/                 # Production config
│   │   │   └── docker/               # Docker config
│   │   ├── db/migration/             # Database migration scripts
│   │   └── webapp/                   # Web application resources
│   │       ├── static/               # CSS, JS, images
│   │       └── WEB-INF/views/        # JSP pages
│   ├── k8s/                          # Kubernetes configurations
│   ├── pom.xml                       # Maven configuration
│   └── Dockerfile                    # Docker configuration
├── frontend/                         # React frontend (optional)
├── mobile/                           # Mobile app (React Native)
├── docs/                             # Documentation
├── scripts/                          # Deployment scripts
└── README.md                         # Project documentation
```

## 🔧 Development

### Building the Project
```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package application
mvn package -DskipTests

# Run application
java -jar target/cms.jar
```

### Database Setup
```bash
# Create database
mysql -u root -p -e "CREATE DATABASE xcharge_csms;"

# Run migrations (automatic on startup)
# Or manually:
mysql -u root -p xcharge_csms < src/main/resources/db/migration/V0_6_6__inital.sql
```

### Configuration
Edit `src/main/resources/config/prod/main.properties`:
```properties
# Database configuration
db.ip=localhost
db.port=3306
db.schema=xcharge_csms
db.user=root
db.password=your_password

# Server configuration
server.port=8080
server.host=0.0.0.0

# OCPP configuration
ocpp.websocket.enabled=true
ocpp.soap.enabled=true
```

## 📊 OCPP Protocol Support

### Supported OCPP Versions
- **OCPP 1.2**: Core functionality and basic operations
- **OCPP 1.5**: Enhanced features and improved security
- **OCPP 1.6**: Latest features including smart charging

### Key OCPP Operations
- **Core Profile**: Authorize, Start/Stop Transaction, Meter Values
- **Firmware Management**: Update Firmware, Get Diagnostics
- **Remote Control**: Remote Start/Stop Transaction, Unlock Connector
- **Configuration**: Change Configuration, Get Configuration
- **Reservations**: Reserve Now, Cancel Reservation
- **Smart Charging**: Set Charging Profile, Clear Charging Profile

## 🚀 Deployment

### Docker Deployment
```bash
# Build Docker image
docker build -t xcharge-csms .

# Run with Docker Compose
docker-compose up -d
```

### Kubernetes Deployment
```bash
# Apply Kubernetes configurations
kubectl apply -f backend/k8s/yaml/

# Check deployment status
kubectl get pods
kubectl get services
```

### Production Deployment
1. **Database Setup**: Configure MySQL with proper security
2. **Application Configuration**: Update production properties
3. **SSL/TLS**: Configure HTTPS for secure communication
4. **Monitoring**: Set up logging and monitoring
5. **Load Balancing**: Configure for high availability

## 📈 Features Overview

### For Charging Station Operators
- Real-time station monitoring and control
- Transaction management and billing
- User and access management
- Configuration and firmware updates
- Comprehensive reporting and analytics

### For EV Drivers
- Station finder and status information
- Reservation management
- Transaction history
- Payment processing integration

### For System Administrators
- Multi-tenant support
- User management and permissions
- System configuration
- Monitoring and alerting
- Data export and reporting

## 🔒 Security Features

- **Authentication**: User login and session management
- **Authorization**: Role-based access control
- **Data Encryption**: Secure data transmission
- **OCPP Security**: Secure OCPP communication
- **Audit Logging**: Comprehensive activity logging

## 📚 API Documentation

### REST API Endpoints
- `GET /steve/api/chargepoints` - List charging stations
- `GET /steve/api/transactions` - List transactions
- `GET /steve/api/ocpp-tags` - List OCPP tags
- `POST /steve/api/chargepoints/{id}/operations` - Execute operations

### OCPP WebSocket Endpoints
- `ws://localhost:8080/steve/websocket/CentralSystemService` - OCPP 1.6 JSON
- `ws://localhost:8080/steve/websocket/CentralSystemService15` - OCPP 1.5 JSON
- `ws://localhost:8080/steve/websocket/CentralSystemService12` - OCPP 1.2 JSON

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

- **Documentation**: [Project Wiki](https://github.com/Xchargedigital/ev-charging-csms/wiki)
- **Issues**: [GitHub Issues](https://github.com/Xchargedigital/ev-charging-csms/issues)
- **Discussions**: [GitHub Discussions](https://github.com/Xchargedigital/ev-charging-csms/discussions)

## 🎯 Business Value

### For Charging Network Operators
- Comprehensive charging station management
- Real-time monitoring and control
- Advanced analytics and reporting
- Scalable and reliable platform

### For EV Drivers
- Easy station discovery and access
- Reliable charging services
- Transparent pricing and billing
- Excellent user experience

### For the EV Industry
- Standard OCPP compliance
- Open source and extensible
- Industry best practices
- Future-ready architecture

---

**Powering the Future of Electric Vehicle Charging Infrastructure** ⚡🚗

## 📚 Additional Documentation

- [Getting Started Guide](GETTING_STARTED.md)
- [OCPP Implementation Guide](docs/ocpp.md)
- [API Documentation](http://localhost:8080/steve/api)
- [Deployment Guide](docs/deployment.md)
- [Development Guide](docs/development.md)