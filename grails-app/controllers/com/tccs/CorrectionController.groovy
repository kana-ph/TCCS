package com.tccs

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

class CorrectionController {
    CorrectionService correctionService
    def springSecurityService

    @Secured(['ROLE_USER'])
    def create() {

    }

    private Date stringToDate(String date) {
        return Date.parse('MM/dd/yyyy hh:mm a', date)
    }

    private String dateToString(Date date) {
        return date.format('MM/dd/yyyy hh:mm a')
    }

    @Secured(['ROLE_USER'])
    def save() {
        println springSecurityService.getCurrentUser()

        Date dateTimeCorrection = stringToDate(params.dateTimeCorrection)
        String reason = params.reason
        String entryRequired = params.entryRequired
        String comment = params.comment

        def user = springSecurityService.currentUser

        correctionService.saveCorrection(dateTimeCorrection, reason, entryRequired, comment, user)
        flash.message = "Successfully Sent"
        redirect(action: "create")
    }

    @Secured(['ROLE_USER'])
    def index() {
        def corrections = Correction.list()
        [corrections: corrections]
    }

    @Secured(['ROLE_USER'])
    def uploadProof() {
        
    }
}
