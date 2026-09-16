pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Test') {
            steps {
                sh 'make test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Jar') {
            steps {
                sh 'make jar'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
        }
    }
}
