/* Requires the Docker Pipeline plugin */
pipeline {
   agent any
    stages {
        stage('test'){
            steps{
                sh 'sh ./mvnw test'
            }
        }
        stage('build') {
            agent {
                docker {
                    image 'docker'
                    args '-u root -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                script{
                    def image = docker.build("yonightfury/campus-placement:${BUILD_NUMBER}")
                    if(BRANCH_NAME=="main"){
                        image.tag("latest")
                    }
                    image.push()
                }
            }
        }
    }
}
