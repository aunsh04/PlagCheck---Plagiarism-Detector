pipeline {
   agent {
       docker {
           image 'maven:3-alpine'
           args '-v /root/.m2:/root/.m2'
       }
   }

   stages {
       stage('Build') {
           steps {
               echo "Building"
               sh 'mvn compile -f cs5500-sp2018-team105/pom.xml'
               sh 'mvn package -f cs5500-sp2018-team105/pom.xml'
           }
       }
       stage('Test'){
           steps {
               echo "Testing"
               sh 'mvn test -f cs5500-sp2018-team105/pom.xml'
           }
       }
       stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                        sh 'mvn clean install -f cs5500-sp2018-team105/pom.xml -Dsonar.projectKey=Team-105'
                        sh 'mvn sonar:sonar -f cs5500-sp2018-team105/pom.xml -Dsonar.projectKey=Team-105'
                }
            }
        }
            
        stage('Quality') {
          steps {
            sh 'sleep 30'
            timeout(time: 10, unit: 'SECONDS') {
               retry(5) {
                  script {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
            }
          }
        }
      }
    }
  }
}
