properties([disableConcurrentBuilds()])

pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        timestamps()
    }

    environment {
        VAR="123"
    }

    stages {

        stage("Build") {
            steps {
                echo 'building..'
                echo 'build done!'
            }
        }

        stage("Testing one ...") {
            steps {
                echo "one"
            }
        }

        stage("Testing two ...") {
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
