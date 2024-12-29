pipeline {
    agent any

    tools {
        jdk "JDK 23"
        maven "my-maven" // Use the 'my-maven' tool defined in your Global Tool Configuration
    }

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
                sh 'mvn clean package -DskipTests'
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
                    // If on Linux, use sh instead of bat
                    bat 'docker-compose down'
                    bat 'docker-compose up -d'
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
