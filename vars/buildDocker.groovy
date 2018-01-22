/*
 * Toolform-compatible Jenkins 2 Pipeline build step for simple Docker artifacts
 * Zips up the specified directory as long as it contains a Dockerfile
 */

def call(Map config) {
  container('build-docker') {

  stage('Verify Contents') {
    sh "cat \"${config.baseDir}/Dockerfile\""
  }

  stage('Archive to Jenkins') {
    def zipName = "${config.project}-${config.component}-${config.buildNumber}.zip"
    sh "zip -r \"${zipName}\" \"${config.baseDir}\""
    archiveArtifacts zipName
  }

}
