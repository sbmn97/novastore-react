pipeline {
    agent any
    options { timestamps() }
    
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
                    echo "Working directory: $(pwd)"
                    echo "Files in workspace:"
                    ls -la
                    echo "Checking if package.json exists:"
                    if [ -f "package.json" ]; then
                        echo "✅ package.json found"
                        cat package.json | head -10
                    else
                        echo "❌ package.json NOT found"
                    fi
                    docker run --rm -v "$(pwd):/workspace" -w /workspace node:18-alpine sh -c "
                        node --version
                        npm --version
                        echo 'Files in Docker workspace:'
                        ls -la
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
                    # Create a temporary container to copy files and run build
                    CONTAINER_ID=$(docker create -w /app node:18-alpine sh -c "npm install && npm run build")
                    
                    # Copy source files to container
                    docker cp package.json "$CONTAINER_ID:/app/"
                    docker cp src "$CONTAINER_ID:/app/"
                    docker cp public "$CONTAINER_ID:/app/"
                    
                    # Start container and run build
                    docker start -a "$CONTAINER_ID"
                    
                    # Copy build results back
                    docker cp "$CONTAINER_ID:/app/build" .
                    docker cp "$CONTAINER_ID:/app/node_modules" . || true
                    
                    # Clean up
                    docker rm "$CONTAINER_ID"
                    
                    echo '✅ Build completed successfully'
                '''
            }
        }
        
        stage('Package') {
            steps {
                sh '''
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