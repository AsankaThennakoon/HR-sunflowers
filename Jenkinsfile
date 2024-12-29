pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "tmat1560/hr-sunflowers-backend"
        DOCKER_CREDENTIALS_ID = "asanka-dockerhub"
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }
        stage('Build Application') {
            steps {
                script {
                    docker.image('openjdk:23-jdk-slim').inside {
                        sh 'apt-get update && apt-get install -y maven'
                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:latest")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKER_CREDENTIALS_ID}") {
                        docker.image("${DOCKER_IMAGE}:latest").push()
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sh 'docker-compose down'
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed."
        }
        failure {
            echo "Pipeline failed."
        }
    }
}
