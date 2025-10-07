#!/usr/bin/env groovy

/*
 * NovaStore Jenkins Setup for sbmn97
 * Auto-configures Jenkins with custom admin credentials
 */

import jenkins.model.*
import hudson.security.*
import jenkins.install.*
import java.util.logging.Logger

def logger = Logger.getLogger("")
def instance = Jenkins.getInstance()

logger.info("🚀 Setting up Jenkins for sbmn97...")

// Skip setup wizard
if (!instance.getInstallState().isSetupComplete()) {
    logger.info("📋 Bypassing setup wizard...")
    InstallState.INITIAL_SETUP_COMPLETED.initializeState()
}

// Create custom admin user
logger.info("� Creating admin user: sbmn97")
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("sbmn97", "\$aA644653SBMN")
instance.setSecurityRealm(hudsonRealm)

// Set authorization strategy
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

// Save configuration
instance.save()

logger.info("✅ Jenkins configured successfully!")
logger.info("👤 Username: sbmn97")
logger.info("🔐 Password: \$aA644653SBMN")
logger.info("🌐 URL: http://localhost:8080")

println "Jenkins is ready for NovaStore CI/CD!"