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
        def head1 = new User(firstName: 'head', middleName: 'of', lastName: 'department', username: 'head1', password: 'password', email: 'head1@synacy.com', position: 'department head', department: 'App Dev').save(failOnError: true, flush: true)
        def head2 = new User(firstName: 'head', middleName: 'of', lastName: 'department', username: 'head2', password: 'password', email: 'head2@synacy.com', position: 'department head', department: 'HR').save(failOnError: true, flush: true)
        def user1 = new User(firstName: 'Kenichi', middleName: 'Go', lastName: 'Uang', username: 'kenichi.uang', password: 'password', email: 'kenichi.uang@synacy.com', position: 'Intern', department: 'App Dev').save(failOnError: true, flush: true)
        def user2 = new User(firstName: 'Kenyashi', middleName: 'Go', lastName: 'Uang', username: 'kenyashi.uang', password: 'password', email: 'kenyashi.uang@synacy.com', position: 'Intern', department: 'HR').save(failOnError: true, flush: true)
        
        new UserRole(user: admin, role: userRole).save(failOnError: true, flush: true)
        new UserRole(user: admin, role: adminRole).save(failOnError: true, flush: true)

        new UserRole(user: head1, role: userRole).save(failOnError: true, flush: true)
        new UserRole(user: head1, role: headRole).save(failOnError: true, flush: true)
        new UserRole(user: head2, role: userRole).save(failOnError: true, flush: true)
        new UserRole(user: head2, role: headRole).save(failOnError: true, flush: true)

        new UserRole(user: user1, role: userRole).save(failOnError: true, flush: true)
        new UserRole(user: user2, role: userRole).save(failOnError: true, flush: true)

        def dateTimeCorrection = new Date() - 1

        def correction1 = new Correction(dateTimeCorrection: dateTimeCorrection, reason: ReasonType.REASON_MISSEDLOG, entryRequired: EntryType.ENTRY_CLOCKIN, comment: 'none', status: StatusType.STATUS_PENDING, user: user1).save(failOnError: true, flush: true)
        def correction2 = new Correction(dateTimeCorrection: dateTimeCorrection, reason: ReasonType.REASON_MISSEDLOG, entryRequired: EntryType.ENTRY_CLOCKIN, comment: 'none', status: StatusType.STATUS_PENDING, user: user2).save(failOnError: true, flush: true)

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
