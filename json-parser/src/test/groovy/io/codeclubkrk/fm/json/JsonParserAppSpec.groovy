package io.codeclubkrk.fm.json

import spock.lang.Specification

class JsonParserAppSpec extends Specification {

    def "should run the App"() {
        when:
        JsonParserApp.main()

        then:
        noExceptionThrown()
    }
}
