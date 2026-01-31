#!/usr/bin/env groovy
@Library('jenkins-shared-library@main')_
def gv 
pipeline{
    agent any
    tools{
        maven 'maven-3.9'
    }
    stages{
        stage('Version_Update'){
            steps{
                script{
                    echo 'Version Updating...'
                    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\'${parsedVersion.nextMajorVersion}.${parsedVersion.minorVersion}.${parsedVersion.incrementalVersion}\' versions:commit'
                    def ver = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = ver[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage("init"){
            steps{
                script{
                    echo "Jenkinsfile detected change and pipeline triggered automatically"
                    gv = load "script.groovy"
                }
            }   
        }
        stage("build jar"){
            steps{
                script{
                   buildJar()
                }
            }
        }
        stage("build image"){
            steps{
                script{ 
                    echo "Building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]){
                        sh "docker build -t krishnarane2005/java-maven-repo:$IMAGE_NAME ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push krishnarane2005/java-maven-repo:$IMAGE_NAME"
                    }
                }
            }
        }    
        stage("deploy"){
            steps{
                script{
                    gv.deployApp()
                }
            }
        }
    }
}
