package com.tccs

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import com.tccs.type.StatusType
import com.tccs.type.ReasonType
import com.tccs.type.EntryType
import com.tccs.exception.InvalidSelectionException


class CorrectionController {
    CorrectionService correctionService
    def springSecurityService
    ProofService proofService

    @Secured(['ROLE_USER'])
    def createOne() {

    }


    @Secured(['ROLE_USER'])
    def save() {

        User user = springSecurityService.currentUser

        List files = request.getFiles('proof')

        Correction correction = correctionService
        .saveCorrection(stringToDate(params.dateTimeCorrection), ReasonType.valueOf(params.reason), EntryType.valueOf(params.entryRequired), params.comment, user)

        files.each { file->
            if(file.originalFilename.endsWith(".png") || file.originalFilename.endsWith(".jpg")) {
                proofService.createFile(correction, file.originalFilename, file.bytes)
                flash.message = "Successfully Sent"
            }else {
                throw new IllegalArgumentException()
                // flash.message = "Only PNG or JPEG files please."
            }
        }

        redirect(action: "createOne")
    }

    @Secured(['ROLE_USER'])
    def index() {
        def corrections = Correction.list()
        [corrections: corrections]
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

    // @Secured(['ROLE_USER'])
    // def createTwo() {
    //     String dateTimeCorrection = params.dateTimeCorrection
    //     String reason = params.reason
    //     String entryRequired = params.entryRequired
    //     String comment = params.comment

    //     def user = springSecurityService.currentUser

    //     render(view:"createTwo", model:[dateTimeCorrection: dateTimeCorrection, reason: reason, entryRequired: entryRequired, comment: comment, user: user])
    // }

    def handleIllegalArgumentException(IllegalArgumentException e) {
        flash.message = "Invalid input"
        redirect(action: "createOne")
    }

    def handleParseException(java.text.ParseException e) {
        flash.message = "Invalid input"
        redirect(action: "createOne")
    }

    private Date stringToDate(String date) {
        return Date.parse('MM/dd/yyyy hh:mm a', date)
    }

    private String dateToString(Date date) {
        return date.format('MM/dd/yyyy hh:mm a')
    }
}
