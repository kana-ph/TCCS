package com.tccs

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import com.tccs.type.StatusType

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

    @Secured(['ROLE_USER'])
    def review() {
        def id = params.id
        Correction correction = Correction.get(id)
        def user = springSecurityService.currentUser
        Set<Role> roles = user.authorities

        render(view: 'review', model:[correction: correction, roles: roles*.authority])
    }

    @Secured(['ROLE_HEAD', 'ROLE_ADMIN'])
    def update() {
        def status = params.statusChange as StatusType
        def id = params.id as Long

        correctionService.updateCorrection(id, status)
        flash.message = "Successfully Updated"
        redirect(action: "review", params: [id: id])
    }
}
