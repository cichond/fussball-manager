package io.codeclubkrk.fm.jwt

import spock.lang.Specification

/**
 * @author darek
 * @since 27.12.2018
 */
class JWTEncoderSpec extends Specification {

    def "should create JWT with encoded 'name' claim"() {
        expect:
        jwt == JWTEncoder.create().withName(name).build()

        where:
        jwt                                            | name
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoiZGFyZWsifQ."  | "darek"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoibHVrYXN6In0." | "lukasz"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoiZGF3aWQifQ."  | "dawid"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoibWFjaWVrIn0." | "maciek"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjoia2FtaWwifQ."  | "kamil"
        "eyJhbGciOiJub25lIn0.eyJuYW1lIjpudWxsfQ."      | null
    }
}