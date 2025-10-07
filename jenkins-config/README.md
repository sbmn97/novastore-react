# NovaStore Jenkins Configuration

This directory contains Jenkins configuration files for the NovaStore project.

## Setup Instructions

1. **Start Jenkins Container:**
   ```bash
   docker-compose up jenkins -d
   ```

2. **Access Jenkins:**
   - URL: http://localhost:8081
   - Default Admin: admin
   - Default Password: admin123

3. **Configure Jenkins Job:**
   - Create new Pipeline job named "NovaStore-CI-CD"
   - Use Pipeline script from SCM
   - Repository URL: https://github.com/sbmn97/novastore-react.git
   - Script Path: Jenkinsfile

## Required Plugins

The following plugins should be installed:
- Pipeline
- Git
- NodeJS
- Docker Pipeline
- Blue Ocean (optional, for better UI)

## Environment Setup

Ensure the following tools are available in Jenkins:
- Node.js 18+
- Git
- Docker (for container operations)

## Troubleshooting

If you encounter CSRF errors:
1. Restart Jenkins container: `docker restart novastore-jenkins`
2. Check Jenkins logs: `docker logs novastore-jenkins`
3. Verify network connectivity between containers