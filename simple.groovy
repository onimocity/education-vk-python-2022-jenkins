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
                sh "pytest -s -l -v tests/test.py --alluredir ${WORKSPACE}/alluredir"
            }
        }

        stage("Testing two ...") {
            steps {
                echo "two"
                sh "pytest -s -l -v tests/test_second.py --alluredir ${WORKSPACE}/alluredir"
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
