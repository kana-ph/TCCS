package com.tccs

import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Build(User)
@TestFor(UserService)
class UserServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "saveUser should insert into the table"() {
    	given:
    	String firstName = 'Kenichi'
    	String lastName = 'Uang'
    	String username = 'kenichigouang'
		String password = 'InnaGwapa11790'
		String email = 'kenichi.uang@synacy.com'
		Set<Role> roles = [new Role(authority: 'user'), new Role(authority: 'admin'), new Role(authority: 'approver')]

		expect:
		0 == User.all.size()

		when:
		service.saveUser(firstName, lastName, username, password, email, roles)

		then:
		1 == User.all.size()
    }

    void "saveUser saves correct values in columns"() {
    	given:
    	String firstName = 'Kenichi'
    	String lastName = 'Uang'
    	String username = 'kenichigouang'
		String password = 'InnaGwapa11790'
		String email = 'kenichi.uang@synacy.com'
		Set<Role> roles = [new Role(authority: 'user'), new Role(authority: 'admin'), new Role(authority: 'approver')]

		when:
		User user = service.saveUser(firstName, lastName, username, password, email, roles)

		then:
		firstName == user.firstName
		lastName == user.lastName
		username == user.username
		password == user.password
		email == user.email
		roles == user.roles
    }

    void "getUsers must retrieve all users"() {
    	given:
    	User kenichi = new User(firstName: 'Kenichi', lastName: 'Uang', username: 'kenichigouang', password: 'InnaGwapa11790', email: 'kenichi.uang@synacy.com', roles: [new Role(authority: 'user'), new Role(authority: 'admin'), new Role(authority: 'approver')])
    	User ichi = new User(firstName: 'Kenichi', lastName: 'Uang', username: 'kenichigouang', password: 'InnaGwapa11790', email: 'kenichi.uang@synacy.com', roles: [new Role(authority: 'user'), new Role(authority: 'admin'), new Role(authority: 'approver')])
    	
    	when:
    	getUsers()

    }
}
