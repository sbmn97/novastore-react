pipeline {
    agent any
    options { timestamps                sh '''
                    set -eu
                    echo "📁 Packaging build artifacts..."
                    # Ensure build directory exists
                    if [ ! -d "build" ]; then
                        echo "❌ Build failed - no build directory found"
                        exit 1
                    fi
                    echo "📋 Build contents:"
                    ls -la build/
                '''
    environment {
        NODE_VERSION = '18'
        SITE_DIR = 'src'
        BUILD_DIR = 'build'
        DEPLOY_DIR = '/var/www/site'
        GITHUB_REPO = 'https://github.com/sbmn97/novastore-react.git'
    }
    
    stages {
        stage('Checkout') {
            steps { 
                checkout scm
                echo '✅ Source code retrieved successfully'
            }
        }
        
        stage('Validate') {
            steps {
                sh '''
                    set -eu
                    echo "📋 Environment Information:"
                    docker run --rm -v "$(pwd):/workspace" -w /workspace node:18-alpine sh -c "
                        node --version
                        npm --version
                        echo '✅ Environment validation complete'
                    "
                '''
            }
        }
        
        stage('Install & Build') {
            steps {
                sh '''
                    set -eu
                    echo "📦 Installing dependencies..."
                    docker run --rm -v "$(pwd):/workspace" -w /workspace node:18-alpine sh -c "
                        npm ci
                        echo '🏗️ Building React application...'
                        npm run build
                        echo '✅ Build completed successfully'
                    "
                '''
            }
        }
        
        stage('Package') {
            steps {
                sh '''
                    set -euo pipefail
                    echo "📁 Packaging build artifacts..."
                    # Ensure build directory exists
                    if [ ! -d "build" ]; then
                        echo "❌ Build failed - no build directory found"
                        exit 1
                    fi
                    echo "� Build contents:"
                    ls -la build/
                '''
                archiveArtifacts artifacts: "${BUILD_DIR}/**", fingerprint: true
            }
        }
        
        stage('Deploy to Nginx') {
            steps {
                sh '''
                    set -eu
                    echo "🚀 Deploying to Nginx web server..."
                    mkdir -p "${DEPLOY_DIR}"
                    rm -rf "${DEPLOY_DIR:?}/"*
                    cp -r "${BUILD_DIR}/." "${DEPLOY_DIR}/"
                    echo "✅ Deployment completed successfully"
                '''
            }
        }
    }
    
    post {
        always {
            echo '🧹 Pipeline execution completed.'
        }
        
        success {
            echo '🎉 NovaStore deployment successful!'
            echo 'Deployed. Open http://localhost:8081'
        }
        
        failure {
            echo '❌ Pipeline failed. Check logs for details.'
        }
    }
}