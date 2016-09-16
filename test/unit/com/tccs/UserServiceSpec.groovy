package com.tccs

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.buildtestdata.mixin.Build
import spock.lang.Specification
import grails.test.mixin.web.ControllerUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Build(User)
@TestFor(UserService)
@TestMixin(ControllerUnitTestMixin)
class UserServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "saveUser should insert into the table"() {
    	given:
        String firstName = 'firstName'
        String middleName = 'middleName'
        String lastName = 'lastName'
    	String username = 'username'
		String password = 'password'
        String email = 'email@synacy.com'
        String position = 'position'
        String department = 'department'

		expect:
		0 == User.all.size()

		when:
		service.saveUser(firstName, middleName, lastName, username, password, email, position, department)

		then:
		1 == User.all.size()
    }

    void "saveUser saves correct values in columns"() {
    	given:
    	String firstName = 'firstName'
        String middleName = 'middleName'
        String lastName = 'lastName'
        String username = 'username'
        String password = 'password'
        String email = 'email@synacy.com'
        String position = 'position'
        String department = 'department'

		when:
		User user = service.saveUser(firstName, middleName, lastName, username, password, email, position, department)

		then:
        firstName == user.firstName
        middleName == user.middleName
        lastName == user.lastName
		username == user.username
		password == user.password
        email == user.email
        position == user.position
        department == user.department
    }

    void "getUsers must retrieve all users"() {
    	given:
    	User kenichi = new User(firstName: 'Kenichi', lastName: 'Uang', username: 'kenichigouang', password: 'InnaGwapa11790', email: 'kenichi.uang@synacy.com', roles: [new Role(authority: 'user'), new Role(authority: 'admin'), new Role(authority: 'approver')]).save(failOnError: true)
    	User ichi = new User(firstName: 'ichi', lastName: 'ang', username: 'enichigouang', password: 'InnaGwapa11790', email: 'enichi.uang@synacy.com', roles: [new Role(authority: 'user'), new Role(authority: 'admin'), new Role(authority: 'approver')]).save(failOnError: true)
    	
    	when:
    	def users = service.getUsers()
        def size = users.all.size()

        then:
        2 == size

    }
}
