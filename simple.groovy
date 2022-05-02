properties([disableConcurrentBuilds()])

pipeline {
    agent {
        label 'master'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        timestamps()
    }

    environment {

    }

    stages {

        stage("Build") {
            steps {
                echo 'building..'
                echo 'build done!'
            }
        }

        stage("Testing ...") {
            steps {
                echo "one"
            }
        }

        stage("Testing ...") {
            steps {
                echo "two"
            }
        }
    }

    post {
        always {
            allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'alluredir']]
            ])
            cleanWs()
        }
    }
}
