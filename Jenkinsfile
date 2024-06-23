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
                        def image = docker.build("yonightfury/campus-placement:${BRANCH_NAME}-${BUILD_NUMBER}")
                        if(BRANCH_NAME=="main"){
                            image.push("latest")
                        }
                    image.push()
                }
            }
        }
        stage('Deploy') {
            when {
                expression {
                    (currentBuild.result == null || currentBuild.result == 'SUCCESS')
                }
            }
            steps {
                script{
                    withCredentials([sshUserPrivateKey(credentialsId: "web_id", keyFileVariable: 'KEY'), string(credentialsId:"SSH_HOST", variable:"SSH_HOST"),string(credentialsId:"SSH_USER", variable:"SSH_USER"),]) {
                        def remote = [:]
                        remote.host = SSH_HOST
                        remote.name = "${SSH_USER}@${SSH_HOST}"
                        remote.user = SSH_USER
                        remote.identityFile = KEY
                        sshCommand remote: remote, command: "docker run hello-world"
                    }
                }
            }
        }
    }
}
