pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    def appImage = docker.build("chatbot-ecommerce-api:${env.BUILD_NUMBER}")
                    echo "Imagen construida: ${appImage.id}"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Verifica si hay un contenedor corriendo en el mismo puerto y lo elimina
                    def containerNamePrefix = "chatbotEcommerceApi-"
                    def previousContainer = sh(script: "docker ps -q --filter 'name=${containerNamePrefix}*'", returnStdout: true).trim()

                    if (previousContainer) {
                        echo "Deteniendo y eliminando contenedor anterior: ${previousContainer}"
                        sh "docker stop ${previousContainer} || true"
                        sh "docker rm ${previousContainer} || true"
                    }

                    // Ejecuta el nuevo contenedor
                    def container = docker.image("chatbot-ecommerce-api:${env.BUILD_NUMBER}")
                        .run("-p 7778:7778 --name ${containerNamePrefix}${env.BUILD_NUMBER}")

                    echo "Nuevo contenedor en ejecución: ${container.id}"
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
    }
}
