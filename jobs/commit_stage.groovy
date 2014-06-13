job {
    name "build-app"
    scm {
        git("", "origin")
    }
    steps {
        maven("test -Dproject.name=")
    }
}