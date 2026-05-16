def runCommand(String command) {
    if (isUnix()) {
        sh command
    } else {
        bat command
    }
}

pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
        nodejs 'Node20'
    }

    environment {
        BACKEND_DIR = 'backend'
        FRONTEND_DIR = 'frontend'
        SONARQUBE_ENV = 'SonarQube'
        SONAR_SCANNER = 'SonarScanner'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Backend Build and Test') {
            steps {
                dir(env.BACKEND_DIR) {
                    script {
                        runCommand('mvn clean verify')
                    }
                }
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'backend/target/surefire-reports/*.xml'
                    archiveArtifacts allowEmptyArchive: true, artifacts: 'backend/target/*.jar'
                }
            }
        }

        stage('Frontend Install and Build') {
            steps {
                dir(env.FRONTEND_DIR) {
                    script {
                        runCommand('npm install')
                        runCommand('npm run build')
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool env.SONAR_SCANNER
                    withSonarQubeEnv(env.SONARQUBE_ENV) {
                        if (isUnix()) {
                            sh "\"${scannerHome}/bin/sonar-scanner\""
                        } else {
                            bat "\"${scannerHome}\\bin\\sonar-scanner.bat\""
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Build') {
            when {
                expression { fileExists('docker-compose.yml') }
            }
            steps {
                script {
                    runCommand('docker compose build')
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check the failed stage logs above.'
        }
        always {
            cleanWs deleteDirs: true, notFailBuild: true
        }
    }
}
