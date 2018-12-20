package io.codeclubkrk.fm.http

import spock.lang.Specification

class HttpServerAppSpec extends Specification {

    def "should run the App"() {
        when:
        HttpServerApp.main()

        then:
        noExceptionThrown()
    }
}
