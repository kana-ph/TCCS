package com.tccs



import grails.test.mixin.*
import spock.lang.*

@TestFor(CorrectionController)
@Mock(Correction)
class CorrectionControllerSpec extends Specification {

    void "Test if stringToDate() parses string to Date correctly"() {

        given:
        Date date = new Date()
        String stringDate

        when:
        stringDate = stringToDate(date)

        then:
        Date.parse(date, 'MM/dd/yyyy hh:mm a', Locale.US) == stringDate
    }
}
