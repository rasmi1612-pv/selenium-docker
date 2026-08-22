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
                bat 'docker compose version'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker compose build'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat 'docker compose up --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            bat 'docker compose down'
        }
    }
}
