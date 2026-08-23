pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean Previous Reports') {
            steps {
                bat 'if exist test-output rmdir /s /q test-output'
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

        stage('Check Test Report') {
            steps {
                bat '''
                echo ===== test-output =====
                if exist test-output (
                    dir /s test-output
                ) else (
                    echo test-output folder NOT FOUND
                )

                echo ===== target/surefire-reports =====
                if exist target\\surefire-reports (
                    dir /s target\\surefire-reports
                ) else (
                    echo target\\surefire-reports NOT FOUND
                )
                '''
            }
        }
    }

    post {

        always {

            bat '"C:\\Users\\rasmi\\.docker\\cli-plugins\\docker-compose.exe" down'

            archiveArtifacts artifacts: 'test-output/**/*',
                             allowEmptyArchive: true

            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'index.html',
                reportName: 'Selenium TestNG Report'
            ])
        }

        success {
            echo 'Selenium tests completed successfully.'
        }

        failure {
            echo 'Selenium tests failed. Check the report.'
        }
    }
}
