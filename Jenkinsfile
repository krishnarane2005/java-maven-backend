#!/usr/bin/env groovy
@Library('jenkins-shared-library@main')_
def gv 
pipeline{
    agent any
    tools{
        maven 'maven-3.9'
    }
    stages{
        stage("init"){
            steps{
                script{
                    echo "✅ Jenkinsfile detected change and pipeline triggered automatically"
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
                    buildImage()
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
