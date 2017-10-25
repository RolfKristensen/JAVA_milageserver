#!groovy
// see https://dzone.com/refcardz/continuous-delivery-with-jenkins-workflow for tutorial
// see https://documentation.cloudbees.com/docs/cookbook/_pipeline_dsl_keywords.html for dsl reference
// This Jenkinsfile should simulate a minimal Jenkins pipeline and can serve as a starting point.
// NOTE: sleep commands are solelely inserted for the purpose of simulating long running tasks when you run the pipeline
node {
    // Mark the code checkout 'stage'....
    stage 'checkout'

    // Get some code from a GitHub repository
    git url: 'https://github.com/RolfKristensen/JAVA_milageserver'
    sh 'git clean -fdx'

    // Get the maven tool.
    // ** NOTE: This 'mvn' maven tool must be configured
    // **       in the global configuration.
    def mvnHome = tool 'maven'

    stage 'build'
    // set the version of the build artifact to the Jenkins BUILD_NUMBER so you can
    // map artifacts to Jenkins builds
    //sh "${mvnHome}/bin/mvn versions:set -DnewVersion=${env.BUILD_NUMBER}"
    sh "${mvnHome}/bin/mvn package"

    stage 'test'
    parallel 'test': {
     sh "${mvnHome}/bin/mvn test"
    }, 'verify': {
     sh "${mvnHome}/bin/mvn verify"
    }

    stage 'install in local repo'
    sh "${mvnHome}/bin/mvn install"

    stage 'docker'
    def dockerTool = tool 'docker'

    sh "mkdir docker"
    sh "cd docker"
    git url: 'https://github.com/RolfKristensen/dockerfiles.git'
    sh "cd dockerfiles/milageApp"
    sh "cp ../../../target/milageserver-0.0.1-SNAPSHOT.jar milage.jar"
    sh "docker build -t rolfkristensen/milage-rest:latest" .
}