pipeline {

    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Build & Unit') {
            agent {
                docker {
                    image 'maven:3.9-eclipse-temurin-17'
                    args '-v /var/jenkins_home/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                checkout scm
                sh 'mvn -B clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Trigger AQA') {
            when {
                anyOf {
                    branch 'master'
                    changeRequest()
                }
            }
            steps {
                build job: 'aqa-demo-mb/master',
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