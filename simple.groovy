properties([disableConcurrentBuilds()])

pipeline {
    agent none

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
                pytest -s -l -v tests/test.py
            }
        }

        stage("Testing two ...") {
            steps {
                echo "two"
                pytest -s -l -v tests/test_second.py
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
