# NovaStore - React E-commerce Application

**TechNova Solutions** - Premium Technology Products

## 🌟 Overview

NovaStore is a modern React.js e-commerce application featuring 5 premium technology products with automated CI/CD deployment using Jenkins and Nginx. Built for TechNova Solutions, this application showcases modern web development practices with containerized deployment.

## 🚀 Features

- **Premium Product Catalog:** 5 carefully curated tech products
- **Responsive Design:** Mobile-first, modern UI
- **Automated CI/CD:** Jenkins pipeline with GitHub integration
- **Containerized Deployment:** Docker Compose orchestration
- **Production Ready:** Nginx web server with optimized build

## 🛍️ Product Showcase

1. **Nova Wireless Pro Headphones** - $299.99
   - Noise cancellation, 30h battery, premium sound

2. **TechNova Smart Watch Elite** - $449.99
   - Health tracking, GPS, water resistant

3. **Nova Gaming Laptop X1** - $1,599.99
   - High-performance gaming, RTX graphics

4. **Smart Home Hub Central** - $199.99
   - Voice control, smart automation, security

5. **Nova Bluetooth Speaker 360** - $179.99
   - 360° sound, portable, waterproof

## 🏗️ Architecture

```
NovaStore Application Stack
├── Frontend: React 18.x + Modern CSS
├── CI/CD: Jenkins LTS with Pipeline
├── Web Server: Nginx Alpine
├── Container: Docker Compose
└── Repository: GitHub Integration
```

## 🚀 Quick Start

### Prerequisites
- Docker & Docker Compose
- Git
- GitHub account (for CI/CD)

### 1. Clone Repository
```bash
git clone https://github.com/sbmn97/novastore-react.git
cd novastore-react
```

### 2. Start Services
```bash
# Start Jenkins and Nginx
docker-compose up -d

# Check services status
docker-compose ps
```

### 3. Access Applications
- **NovaStore App:** http://localhost:8080
- **Jenkins Dashboard:** http://localhost:8081
- **Development Server:** http://localhost:3000 (with profile)

### 4. Development Mode (Optional)
```bash
# Start with development profile
docker-compose --profile development up -d
```

## 🔧 Jenkins Setup

### Initial Configuration
1. Access Jenkins at http://localhost:8081
2. Login with:
   - Username: `admin`
   - Password: `admin123`

### Create Pipeline Job
1. New Item → Pipeline → "NovaStore-CI-CD"
2. Pipeline from SCM:
   - Repository: `https://github.com/sbmn97/novastore-react.git`
   - Script Path: `Jenkinsfile`
3. Save and Build Now

### Pipeline Stages
- ✅ **Checkout:** Fetch code from GitHub
- ✅ **Install:** npm ci for dependencies
- ✅ **Build:** Create production build
- ✅ **Test:** Verify build artifacts
- ✅ **Archive:** Store build artifacts
- ✅ **Deploy:** Deploy to Nginx
- ✅ **Health Check:** Verify deployment

## 📁 Project Structure

```
novastore-react/
├── public/
│   ├── index.html          # React HTML template
│   └── manifest.json       # PWA manifest
├── src/
│   ├── components/
│   │   ├── Header.js       # Navigation header
│   │   ├── Footer.js       # Application footer
│   │   ├── ProductGrid.js  # Product display grid
│   │   └── ProductCard.js  # Individual product cards
│   ├── App.js              # Main application component
│   ├── App.css             # Application styles
│   └── index.js            # React entry point
├── nginx-html/             # Nginx web root
├── jenkins-config/         # Jenkins configuration
├── docker-compose.yml      # Container orchestration
├── Dockerfile.dev          # Development container
├── Jenkinsfile             # CI/CD pipeline definition
├── nginx.conf              # Nginx configuration
└── package.json            # Node.js dependencies
```

## 🐳 Docker Services

### Core Services
- **jenkins:** CI/CD automation server (Port 8081)
- **nginx:** Production web server (Port 8080)

### Development Services (Profile: development)
- **novastore-dev:** Live reload development server (Port 3000)

### Production Services (Profile: production)
- **database:** PostgreSQL for data persistence
- **redis:** Caching and session storage

## 🔄 CI/CD Workflow

1. **Trigger:** Push to GitHub repository
2. **Jenkins:** Automatically detects changes
3. **Build:** npm install → npm run build
4. **Test:** Verify build artifacts
5. **Deploy:** Copy to Nginx web root
6. **Health Check:** Verify application is live

## 🛠️ Development Commands

### Local Development
```bash
# Install dependencies
npm install

# Start development server
npm start

# Build for production
npm run build

# Run tests
npm test
```

### Docker Operations
```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Restart specific service
docker-compose restart nginx

# Stop all services
docker-compose down

# Clean up volumes
docker-compose down -v
```

### Jenkins Operations
```bash
# View Jenkins logs
docker logs novastore-jenkins

# Restart Jenkins
docker restart novastore-jenkins

# Access Jenkins container
docker exec -it novastore-jenkins bash
```

## 🌐 Ports Reference

| Service | Port | Purpose |
|---------|------|---------|
| Nginx (Production) | 8080 | Main application |
| Jenkins | 8081 | CI/CD dashboard |
| React Dev Server | 3000 | Development |
| PostgreSQL | 5432 | Database |
| Redis | 6379 | Cache |

## 🔐 Default Credentials

### Jenkins
- Username: `admin`
- Password: `admin123`

### Database (Production Profile)
- Database: `novastore`
- Username: `novastore_user`
- Password: `secure_password_123`

### Redis (Production Profile)
- Password: `redis_password_123`

## 📊 Monitoring & Health Checks

### Application Health
- **Nginx Health:** `curl http://localhost:8080/health`
- **Container Status:** `docker-compose ps`
- **Resource Usage:** `docker stats`

### Jenkins Pipeline Status
- Pipeline dashboard at http://localhost:8081/blue
- Build history and logs available
- Automatic deployment notifications

## 🚨 Troubleshooting

### Common Issues

**Jenkins CSRF Errors:**
```bash
docker restart novastore-jenkins
```

**Build Failures:**
```bash
# Check Jenkins logs
docker logs novastore-jenkins

# Verify Node.js version in Jenkins
# Should be Node.js 18+
```

**Nginx Not Serving Files:**
```bash
# Check Nginx logs
docker logs novastore-nginx

# Verify build artifacts
ls -la nginx-html/
```

**Port Conflicts:**
```bash
# Check what's using ports
netstat -tulpn | grep :8080
netstat -tulpn | grep :8081
```

## 🔄 Deployment Process

### Manual Deployment
1. Build React application: `npm run build`
2. Copy build files: `cp -r build/* nginx-html/`
3. Restart Nginx: `docker restart novastore-nginx`

### Automated Deployment (Recommended)
1. Push changes to GitHub
2. Jenkins automatically triggers build
3. Pipeline handles entire deployment process
4. Health checks verify successful deployment

## 📈 Performance Optimization

### Build Optimization
- **Code Splitting:** Automatic with Create React App
- **Asset Optimization:** Minification and compression
- **Caching:** Nginx static file caching

### Container Optimization
- **Multi-stage builds:** Separate dev/prod containers
- **Volume mounting:** Efficient file sharing
- **Health checks:** Automatic service recovery

## 🔮 Future Enhancements

### Phase 1: Enhanced Features
- User authentication and accounts
- Shopping cart functionality
- Payment integration
- Product search and filtering

### Phase 2: Scalability
- Database integration (PostgreSQL ready)
- Redis caching implementation
- Load balancing with multiple Nginx instances
- Kubernetes deployment manifests

### Phase 3: Advanced DevOps
- Multiple environment support (dev/staging/prod)
- Automated testing integration
- Security scanning in pipeline
- Monitoring and alerting (Prometheus/Grafana)

## 🤝 Contributing

1. Fork the repository
2. Create feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🏢 TechNova Solutions

**Premium Technology Products for Modern Living**

- Website: [Coming Soon]
- Email: info@technova-solutions.com
- Support: support@technova-solutions.com

---

**Built with ❤️ by TechNova Solutions Development Team**

*NovaStore - Where Innovation Meets Excellence*