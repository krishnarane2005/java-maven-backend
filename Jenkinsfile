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
                    def version = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout').trim()
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
        stage("commit version update"){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'github-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]){
                        sh 'git config --global user.name "krishnarane2005"'
                        sh 'git config --global user.email "krishnarane2005@gmail.com"'

                        sh 'git status'
                        sh 'git branch'
                        sh 'git config --list'

                        sh "git remote set-url origin https://$USER:$PASS@github.com/krishnarane2005/java-maven-backend.git"
                        sh 'git add .'
                        sh 'git commit -m "Version update"'
                        sh 'git push origin HEAD:refs/heads/Jenkins'
                    }
                }   
            }
        }
    }
}
