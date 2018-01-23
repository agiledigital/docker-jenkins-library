/*
 * Toolform-compatible Jenkins 2 Pipeline build step for simple Docker artifacts
 * Zips up the specified directory as long as it contains a Dockerfile
 */

def call(Map config) {

  stage('Verify Contents') {
    sh "cat \"${config.baseDir}/Dockerfile\""
  }

  if(config.stage == 'dist') {

    stage('Archive to Jenkins') {
      def tarName = "${config.project}-${config.component}-${config.buildNumber}.tar.gz"
      sh "tar -czvf \"${tarName}\" -C \"${config.baseDir}\" ."
      archiveArtifacts tarName
    }
  }

}
