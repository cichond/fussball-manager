package io.codeclubkrk.fm.jwt

import io.codeclubkrk.fm.jwt.exceptions.JWTDecodeException
import spock.lang.Specification

/**
 * @author darek
 * @since 27.12.2018
 */
class JWTDecoderSpec extends Specification {

    def "should decode JWT with encoded 'name'"() {
        expect:
        name == JWTDecoder.decode(jwt).getName()

        where:
        jwt                                            | name
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoiZGFyZWsifQ."  | "darek"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoibHVrYXN6In0." | "lukasz"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoiZGF3aWQifQ."  | "dawid"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoibWFjaWVrIn0." | "maciek"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoia2FtaWwifQ."  | "kamil"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjpudWxsfQ."      | null
    }

    def "should throw exception when given JWT has illegal format"() {
        when:
        JWTDecoder.decode(jwt)

        then:
        thrown JWTDecodeException

        where:
        jwt << ["",
                "eyJhbGciOiJub25lIn0.eyJuYW1lIjoiZGFyZWsifQ",
                "eyJhbGciOiJub25lIn0eyJuYW1lIjoiZGFyZWsifQ.",
                "eyJhbGciOiJub25lIn0eyJuYW1lIjoiZGFyZWsifQ",
                "eyJhbGciOiJub25lIn0.eyJuYW1l.IjoiZGFyZWsifQ."]
    }

}
