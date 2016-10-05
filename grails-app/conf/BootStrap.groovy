import com.tccs.*

class BootStrap {

    def init = { servletContext ->
    	def userRole = new Role(authority: "ROLE_USER").save(failOnError: true)
    	def adminRole = new Role(authority: "ROLE_ADMIN").save(failOnError: true)
    	def headRole = new Role(authority: "ROLE_HEAD").save(failOnError: true)
    	
        def admin = new User(firstName: 'first', middleName: 'middle', lastName: 'last', username: 'admin', password: 'password', email: 'email@synacy.com', position: 'position', department: 'department').save(failOnError: true, flush: true)
        def user = new User(firstName: 'Kenichi', middleName: 'Go', lastName: 'Uang', username: 'kenichi.uang', password: 'password', email: 'kenichi.uang@synacy.com', position: 'Intern', department: 'App Dev').save(failOnError: true, flush: true)
        
        new UserRole(user: admin, role: userRole).save(failOnError: true, flush: true)
        new UserRole(user: admin, role: adminRole).save(failOnError: true, flush: true)

        new UserRole(user: user, role: userRole).save(failOnError: true, flush: true)

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
