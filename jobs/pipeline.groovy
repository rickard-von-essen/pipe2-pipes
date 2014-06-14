env = System.getenv()

project = env['project']
git_url = env['git_url']
branch = env['branch']

env.each {
  println it
}

job {
    name "build-${project}"
    scm {
        git("${git_url}", "${branch}")
    }
    steps {
        maven("clean install -B -DskipTests=true -Dproject.name=${project}")
    }
    publishers {
      downstream("test-testclinic", "SUCCESS")
    }
}

job {
    name "test-${project}"
    scm {
        git("${git_url}", "${branch}")
    }
    steps {
        maven("clean test -B -Dproject.name=${project}")
    }
    publishers {
      archiveJunit("target/surefire-reports/*.xml", true, false, true)
    }
}
