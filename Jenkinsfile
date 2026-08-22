pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Docker Check') {
            steps {
                bat 'docker --version'
                bat '"C:\\Users\\rasmi\\.docker\\cli-plugins\\docker-compose.exe" version'
            }
        }

        stage('Docker Build') {
            steps {
                bat '"C:\\Users\\rasmi\\.docker\\cli-plugins\\docker-compose.exe" build'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat '"C:\\Users\\rasmi\\.docker\\cli-plugins\\docker-compose.exe" up --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            bat '"C:\\Users\\rasmi\\.docker\\cli-plugins\\docker-compose.exe" down'
        }
    }
}
