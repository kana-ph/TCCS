import com.tccs.*
import com.tccs.type.ReasonType
import com.tccs.type.EntryType
import com.tccs.type.StatusType

class BootStrap {

    def init = { servletContext ->
    	def userRole = new Role(authority: "ROLE_USER").save(failOnError: true)
    	def adminRole = new Role(authority: "ROLE_ADMIN").save(failOnError: true)
    	def headRole = new Role(authority: "ROLE_HEAD").save(failOnError: true)
    	
        def admin = new User(firstName: 'I', middleName: 'am', lastName: 'admin', username: 'admin', password: 'password', email: 'admin@synacy.com', position: 'administrator', department: 'admin').save(failOnError: true, flush: true)
        def head = new User(firstName: 'head', middleName: 'of', lastName: 'department', username: 'head', password: 'password', email: 'head@synacy.com', position: 'department head', department: 'App Dev').save(failOnError: true, flush: true)
        def user = new User(firstName: 'Kenichi', middleName: 'Go', lastName: 'Uang', username: 'kenichi.uang', password: 'password', email: 'kenichi.uang@synacy.com', position: 'Intern', department: 'App Dev').save(failOnError: true, flush: true)
        
        new UserRole(user: admin, role: userRole).save(failOnError: true, flush: true)
        new UserRole(user: admin, role: adminRole).save(failOnError: true, flush: true)

        new UserRole(user: head, role: userRole).save(failOnError: true, flush: true)
        new UserRole(user: head, role: headRole).save(failOnError: true, flush: true)

        new UserRole(user: user, role: userRole).save(failOnError: true, flush: true)

        def dateTimeCorrection = new Date() - 1

        def correction = new Correction(dateTimeCorrection: dateTimeCorrection, reason: ReasonType.REASON_MISSEDLOG, entryRequired: EntryType.ENTRY_CLOCKIN, comment: 'none', status: StatusType.STATUS_PENDING, user: user).save(failOnError: true, flush: true)

        // def headRole = new Role(authority: 'ROLE_HEAD').save(failOnError: true)
        // def head = new User(username: 'head', password: 'head').save(failOnError: true)

        // def userRole = new Role(authority: 'ROLE_USER').save(failOnError: true)
        // def user = new User(username: 'user', password: 'user').save(failOnError: true)

        // if(!user.authorities.contains('ROLE_HEAD')) {
        //     def head_userRole = new UserRole(user: head, role: headRole).save(failOnError: true)
        // }

        // if(!user.authorities.contains('ROLE_USER')) {
        //     def user_userRole = new UserRole(user: user, role: userRole).save(failOnError: true)
        // }    
	}
    def destroy = { 
    }
}
