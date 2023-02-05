pipeline {
  agent {
    label 'docker'
  }
  triggers {
    pollSCM('H/5 * * * *')
  }
  stages {
    stage('Backend Build') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/user/repo.git']]])
        sh 'cd backend && mvn clean install'
        sh 'docker build -t backend .'
      }
    }
    stage('Backend Deploy') {
      steps {
        sh 'docker tag backend backend:$BUILD_NUMBER'
        sh 'docker push backend:$BUILD_NUMBER'
      }
    }
    stage('Frontend Build') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/user/repo.git']]])
        sh 'cd frontend && npm install'
        sh 'cd frontend && npm run build'
        sh 'docker build -t frontend .'
      }
    }
    stage('Frontend Deploy') {
      steps {
        sh 'docker tag frontend frontend:$BUILD_NUMBER'
        sh 'docker push frontend:$BUILD_NUMBER'
      }
    }
  }
}
