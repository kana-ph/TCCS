package com.tccs

import grails.transaction.Transactional

@Transactional 
class UserService {
	User saveUser(String firstName, String lastName, String username, String password, String email, Set<Role> roles) {
		User user = new User(firstName: firstName, lastName: lastName, username: username, password: password, email: email, roles: roles)
		user.save()
		return user
	}

	User getUsers() {
		User users = User.list()
		return users
	}
}