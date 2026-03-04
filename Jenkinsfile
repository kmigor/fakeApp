pipeline {

    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Unit') {
            steps {
                sh 'mvn -B clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Trigger AQA') {
            when { changeRequest() }
            steps {
                build job: 'aqa-pipeline/main',
                      parameters: [
                          booleanParam(name: 'RUN_SMOKE_FROM_APP', value: true)
                      ],
                      wait: false
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}