#!groovy
// see https://dzone.com/refcardz/continuous-delivery-with-jenkins-workflow for tutorial
// see https://documentation.cloudbees.com/docs/cookbook/_pipeline_dsl_keywords.html for dsl reference
// This Jenkinsfile should simulate a minimal Jenkins pipeline and can serve as a starting point.
// NOTE: sleep commands are solelely inserted for the purpose of simulating long running tasks when you run the pipeline
node {
    // adds job parameters within jenkinsfile
    properties([
     parameters([
       stringParam(
         description: 'Define a specific build version',
         name: 'buildVersion',
         defaultValue: 'NOT_DEFINED'
       )
     ])
    ])

    // Mark the code checkout 'stage'....
    stage 'checkout'

    // Get some code from a GitHub repository
    git url: 'https://github.com/RolfKristensen/JAVA_milageserver'
    sh 'git clean -fdx'

    def pomTree = readMavenPom file: 'pom.xml'
    echo "pom version: ${pomTree.version}"
    echo "build parameter: {params.buildVersion}"

    def buildVersion;
    if (params.buildVersion == 'NOT_DEFINED') {
     buildVersion = pomTree.version
    } else {
     buildVersion = params.buildVersion
    }
    currentBuild.displayName = "#${env.BUILD_NUMBER} v${buildVersion}"
    currentBuild.description = "Build version: ${buildVersion}, Branch: ${env.BRANCH_NAME}"

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

    stage 'nexus deploy'
    sh "${mvnHome}/bin/mvn deploy"

    stage 'docker build'
    sh "${mvnhome}/bin/mvn dockerfile:build

    stage 'docker push'
    sh "${mvnhome}/bin/mvn dockerfile:push
}
