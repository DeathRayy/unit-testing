pipeline{
  agent any
  environment {
  PATH = "/opt/maven/bin:$PATH"
  }
  stages{
    stage('Build'){
      steps{
        sh "mvn clean install"
        }
        }
    stage('SonarQube analysis'){
      steps{
        withSonarQubeEnv('sonarqube'){
          sh "mvn sonar:sonar"
      }
    }
   }
  }        
}
      
