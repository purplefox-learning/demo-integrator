#!/usr/bin/env groovy

pipeline {
    agent { node { label 'linux' } }

    stages {
        stage('Build config service') {
            steps {
                script {
                    library "edmi-pipeline-lib-core@${branchName}"
                    edmiBuild(tasks: 'clean build dockerBuild')
                }
            }
        }
        stage('Package config service') {
            steps {
                script {
                    edmiBuild(tasks: 'distTar')
                }
            }
        }
        stage('Publish to Artifactory') {
            steps {
                script {
                    edmiPublish()
                }
            }
        }

        stage('Auto Deploy to continuous deployment server') {
            steps {
                script {
                    edmiDeploy()
                }
            }
        }
    }

    post {
        always {
            script {
                edmiPostActions()
            }
        }
    }
}