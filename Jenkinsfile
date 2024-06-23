/* Requires the Docker Pipeline plugin */
pipeline {
    agent {
        docker {
            image 'docker'
            args '-u root -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('test'){
            steps{
                sh 'sh ./mvnw test'
            }
        }
        stage('build') {
            steps {
                script{
                    docker.build("jenkinsdemo:${BUILD_NUMBER}").push()
                }
            }
        }
    }
}
