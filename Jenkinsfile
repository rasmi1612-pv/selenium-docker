pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
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