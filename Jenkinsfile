pipeline {
    agent any

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('SCM') {
            steps {
                checkout scm
            }
        }

        stage('Unit Test') {
            steps {
                sh './gradlew clean test'
            }
        }

        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    sh './gradlew sonarqube'
                }
            }
        }

        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            junit 'build/reports/**/*.xml'
        }
    }
}