#!/usr/bin/env groovy

/*
 * NovaStore Jenkins Auto-Configuration Script
 * Custom setup for user: sbmn97
 * This script automatically configures Jenkins with custom admin credentials and essential plugins
 */

import jenkins.model.*
import hudson.model.*
import hudson.security.*
import hudson.util.*
import jenkins.install.*
import hudson.tools.*
import jenkins.plugins.nodejs.tools.*
import java.util.logging.Logger

def logger = Logger.getLogger("")
def instance = Jenkins.getInstance()

logger.info("🚀 Starting NovaStore Jenkins Auto-Configuration for sbmn97...")

// Skip the setup wizard
if (!instance.getInstallState().isSetupComplete()) {
    logger.info("📋 Bypassing setup wizard and configuring Jenkins...")
    InstallState.INITIAL_SETUP_COMPLETED.initializeState()
}

// Configure security with custom admin user
logger.info("👤 Setting up custom admin user: sbmn97")
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("sbmn97", "\$aA644653SBMN")
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

// Configure global environment variables for NovaStore
logger.info("🌐 Setting up global environment variables...")
def globalNodeProperties = instance.getGlobalNodeProperties()
def envVarsNodePropertyList = globalNodeProperties.getAll(EnvironmentVariablesNodeProperty.class)

def newEnvVarsNodeProperty = null
def envVars = null

if (envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0) {
    newEnvVarsNodeProperty = new EnvironmentVariablesNodeProperty()
    globalNodeProperties.add(newEnvVarsNodeProperty)
    envVars = newEnvVarsNodeProperty.getEnvVars()
} else {
    envVars = envVarsNodePropertyList.get(0).getEnvVars()
}

// Set environment variables for NovaStore project
envVars.put("NOVASTORE_ENV", "production")
envVars.put("NODE_ENV", "production")
envVars.put("CI", "true")
envVars.put("REACT_APP_NAME", "NovaStore")
envVars.put("REACT_APP_COMPANY", "TechNova Solutions")

// Configure Node.js tool installation
logger.info("🔧 Configuring Node.js 18 installation...")
def nodeJSInstallations = []
def nodeJS18 = new NodeJSInstallation(
    "NodeJS-18",
    "/usr/local/lib/nodejs",
    [new NodeJSInstaller("18.20.4")]
)
nodeJSInstallations.add(nodeJS18)

def nodeJSDescriptor = instance.getDescriptorByType(NodeJSInstallation.DescriptorImpl.class)
nodeJSDescriptor.setInstallations(nodeJSInstallations.toArray(new NodeJSInstallation[0]))

// Configure Git tool
logger.info("📋 Configuring Git integration...")
def gitTool = new GitTool("Default", "/usr/bin/git", [])
def gitDescriptor = instance.getDescriptorByType(GitTool.DescriptorImpl.class)
gitDescriptor.setInstallations([gitTool].toArray(new GitTool[0]))

// Save all configurations
instance.save()

logger.info("✅ NovaStore Jenkins configuration completed successfully!")
logger.info("🌐 Access Jenkins at: http://localhost:8080")
logger.info("👤 Username: sbmn97")
logger.info("🔐 Password: \$aA644653SBMN")
logger.info("📦 Ready for NovaStore CI/CD pipeline setup!")

// Log completion message
println """
╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗
║                            🎉 JENKINS CONFIGURATION COMPLETE! 🎉                                       ║
╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣
║                                                                                                          ║
║  👤 Admin User: sbmn97                                                                                  ║
║  🔐 Password: \$aA644653SBMN                                                                            ║
║  🌐 URL: http://localhost:8080                                                                          ║
║  🏢 Project: NovaStore - TechNova Solutions                                                             ║
║                                                                                                          ║
║  ✅ Security configured                                                                                 ║
║  ✅ Environment variables set                                                                           ║
║  ✅ Node.js 18 configured                                                                               ║
║  ✅ Git integration ready                                                                               ║
║                                                                                                          ║
║  📋 Next: Install Pipeline plugins and create NovaStore-CI-CD job                                      ║
║                                                                                                          ║
╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝
"""