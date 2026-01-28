def buildJar(){
 echo "Building the application..."
 sh 'mvn package'   
}

def buildImage(){
    echo "Building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]){
        sh 'docker build -t krishnarane2005/java-maven-repo:v2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push krishnarane2005/java-maven-repo:v2.0"
    }
}

def deployApp(){
    echo "Deploying the application..."
}

return this

