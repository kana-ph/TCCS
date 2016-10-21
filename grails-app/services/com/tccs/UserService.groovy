package com.tccs

import grails.transaction.Transactional
import com.tccs.exception.InvalidInputException
import com.tccs.type.RoleAuthority

@Transactional 
class UserService {
	UserRoleService userRoleService
	User saveUser(String firstName, String middleName, String lastName, String username, String password, String email, String position, String department, List roles) {
			def user = new User(
							firstName: firstName,
                            middleName: middleName,
                            lastName: lastName,
                            username: username,
                            password: password,
                            email: email,
                            position: position,
                            department: department)
		
		if(user.validate()) {
			user.save(failOnError: true)
			userRoleService.saveUserRole(user, roles as Set)
		} else {
			throw new InvalidInputException ()
		}

		return user
	}

	User updateUser(User user, String firstName, String middleName, String lastName, String username, String email, String position, String department, List roles) {
		user.firstName = firstName
		user.middleName = middleName
		user.lastName = lastName
		user.username = username
		user.email = email
		user.position = position
		user.department = department

		if(user.validate(['firstName', 'middleName', 'lastName', 'username', 'email', 'position', 'department'])) {
			user.save(failOnError: true)
			userRoleService.updateUserRole(user, roles as Set)
		} else {
			user.errors.allErrors.each{ println it }
			throw new InvalidInputException ()
		}

		return user
	}

	User fetchById(Long id) {
		return User.get(id)
	}

	User fetchHeadOfDepartment(String department) {
		def role = Role.findByAuthority(RoleAuthority.ROLE_HEAD.name())
		def users = UserRole.findAllByRole(role)*.user
		return users.find { user ->
			department == user.department
		}
	}

	User fetchAdmin() {
		def role = Role.findByAuthority(RoleAuthority.ROLE_ADMIN.name())
		return user = UserRole.findByRole(role).user
	}

	// List<User> fetchAllHeadsOfDepartment(String department) {
	// 	def role = Role.findByAuthority(RoleAuthority.ROLE_HEAD.name())
	// 	def users = UserRole.findAllByRole(role)*.user
	// 	return users.findAll { user ->
	// 		department == user.department
	// 	}
	// }

	// List<User> fetchAllAdmins() {
	// 	def role = Role.findByAuthority(RoleAuthority.ROLE_ADMIN.name())
	// 	def users = UserRole.findAllByRole(role)*.user
	// 	return users
	// }
}