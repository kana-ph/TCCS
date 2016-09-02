import com.tccs.*

class BootStrap {

    def init = { servletContext ->
    	def user = new Role(authority: "User").save(failOnError: true)
    	def admin = new Role(authority: "Admin").save(failOnError: true)
    	def approver = new Role(authority: "Approver").save(failOnError: true)

    	// def kenichi = new User(fullname: "Kenichi Go Uang", username: "kenichigouang", password: "InnaGwapa11790", email: "kenichi.uang@synacy.com", roles: [user, admin, approver]).save(failOnError: true)
	}
    def destroy = { 
    }
}
