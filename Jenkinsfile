pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1'
        AWS_ACCOUNT_ID = '<your-aws-account-id>'  // Replace with your AWS Account ID
        AWS_CREDENTIALS_ID = 'aws-jenkins'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/sravanimadineni/TestUser.git']]])
            }
        }

        stage('Build & Start Services with Docker Compose') {
            steps {
                sh '''
                docker-compose -f docker-compose.yml build
                docker-compose -f docker-compose.yml up -d
                '''
            }
        }

        stage('Push Docker Image to AWS ECR') {
            steps {
                sh '''
                aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
                docker tag user-service:latest $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/user-service:latest
                docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/user-service:latest
                '''
            }
        }

        stage('Deploy to AWS EC2') {
            steps {
                sh '''
                ssh -o StrictHostKeyChecking=no -i your-key.pem ec2-user@your-ec2-instance "
                docker pull $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/user-service:latest &&
                docker stop user-service || true &&
                docker rm user-service || true &&
                docker-compose -f docker-compose.yml up -d
                "
                '''
            }
        }
    }
}
