job {
    name "build-testclinic"
    scm {
        git("https://github.com/Diabol/spring-petclinic.git", "origin/pipe2")
    }
    steps {
        maven("install -B -DskipTests=true -Dproject.name=testclinic")
    }
    publishers {
      downstream("test-testclinic", "SUCCESS")
    }
}

job {
    name "test-testclinic"
    scm {
        git("https://github.com/Diabol/spring-petclinic.git", "origin/pipe2")
    }
    steps {
        maven("test -B -Dproject.name=testclinic")
    }
    publishers {
      archiveJunit("target/surefire-reports/*.xml", true, false, true)
    }
}
