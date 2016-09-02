package com.tccs



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CorrectionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Correction.list(params), model:[correctionInstanceCount: Correction.count()]
    }

    def show(Correction correctionInstance) {
        respond correctionInstance
    }

    def create() {
        respond new Correction(params)
    }

    @Transactional
    def save(Correction correctionInstance) {
        if (correctionInstance == null) {
            notFound()
            return
        }

        if (correctionInstance.hasErrors()) {
            respond correctionInstance.errors, view:'create'
            return
        }

        correctionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'correction.label', default: 'Correction'), correctionInstance.id])
                redirect correctionInstance
            }
            '*' { respond correctionInstance, [status: CREATED] }
        }
    }

    def edit(Correction correctionInstance) {
        respond correctionInstance
    }

    @Transactional
    def update(Correction correctionInstance) {
        if (correctionInstance == null) {
            notFound()
            return
        }

        if (correctionInstance.hasErrors()) {
            respond correctionInstance.errors, view:'edit'
            return
        }

        correctionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Correction.label', default: 'Correction'), correctionInstance.id])
                redirect correctionInstance
            }
            '*'{ respond correctionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Correction correctionInstance) {

        if (correctionInstance == null) {
            notFound()
            return
        }

        correctionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Correction.label', default: 'Correction'), correctionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'correction.label', default: 'Correction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
