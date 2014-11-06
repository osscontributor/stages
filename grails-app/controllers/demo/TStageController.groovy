package demo



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TStageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TStage.list(params), model:[TStageInstanceCount: TStage.count()]
    }

    def show(TStage TStageInstance) {
        respond TStageInstance
    }

    def create() {
        respond new TStage(params)
    }

    @Transactional
    def save(TStage TStageInstance) {
        if (TStageInstance == null) {
            notFound()
            return
        }

        if (TStageInstance.hasErrors()) {
            respond TStageInstance.errors, view:'create'
            return
        }

        TStageInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'TStage.label', default: 'TStage'), TStageInstance.id])
                redirect TStageInstance
            }
            '*' { respond TStageInstance, [status: CREATED] }
        }
    }

    def edit(TStage TStageInstance) {
        respond TStageInstance
    }

    @Transactional
    def update(TStage TStageInstance) {
        if (TStageInstance == null) {
            notFound()
            return
        }

        if (TStageInstance.hasErrors()) {
            respond TStageInstance.errors, view:'edit'
            return
        }

        TStageInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TStage.label', default: 'TStage'), TStageInstance.id])
                redirect TStageInstance
            }
            '*'{ respond TStageInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TStage TStageInstance) {

        if (TStageInstance == null) {
            notFound()
            return
        }

        TStageInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TStage.label', default: 'TStage'), TStageInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'TStage.label', default: 'TStage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
