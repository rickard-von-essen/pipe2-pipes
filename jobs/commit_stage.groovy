job {
    name "build-testclinic"
    scm {
        git("https://github.com/Diabol/spring-petclinic.git", "origin")
    }
    steps {
        maven("install -DskipTest -Dproject.name=testclinic")
    }
    publishers {
      downstream("test-testclinic", "SUCCESS")
    }
}

job {
    name "test-testclinic"
    scm {
        git("https://github.com/Diabol/spring-petclinic.git", "origin")
    }
    steps {
        maven("test -Dproject.name=testclinic")
    }
}
