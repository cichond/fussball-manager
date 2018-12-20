package io.codeclubkrk.fm.repository

import spock.lang.Specification

class RepositoryAppSpec extends Specification {

    def "should run the App"() {
        when:
        RepositoryApp.main()

        then:
        noExceptionThrown()
    }
}
