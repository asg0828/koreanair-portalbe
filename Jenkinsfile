def TAG = "v1"
def DOCKER_REGISTRY_ADDRESS = "10.123.4.53:10153"
def NAMESPACE = "shinhan"
def DOCKER_IMAGE_NAME="${DOCKER_REGISTRY_ADDRESS}/shinhan-admin:${TAG}"
def DEPLOYMENT_NAME = "shinhan-admin"


pipeline {
   agent any
  tools {
      maven 'mvn3.6.3'
 }  
   
   stages {
      stage('maven build'){
         steps{
               script{
                  sh(script:"""mvn clean package -DskipTests=true""")
               }
         }
      }
      
    stage ("build docker") {
      steps {
         sh "docker build -t ${DOCKER_IMAGE_NAME} ."
      }
    }      
      
    stage("push docker image"){
      steps {
         withCredentials([usernamePassword(credentialsId: 'NEXUS', passwordVariable: 'REPO_PASS', usernameVariable: 'REPO_ID')]) {
            sh "docker login ${DOCKER_REGISTRY_ADDRESS} -u ${REPO_ID} -p ${REPO_PASS}"
            sh "docker push ${DOCKER_IMAGE_NAME}"
         }
      }
    }
    stage("remove docker image"){
      steps {
         sh "docker rmi ${DOCKER_IMAGE_NAME}"
      }
    }
      stage("restart k8s reousrces"){
         steps{
            script {
               sh(script:"""kubectl rollout restart deploy/${DEPLOYMENT_NAME} -n ${NAMESPACE}""")
            }
         }
      }
   }
}