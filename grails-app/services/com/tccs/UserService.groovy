package com.tccs

import grails.transaction.Transactional

@Transactional 
class UserService {
	UserRoleService userRoleService
	User saveUser(String firstName, String middleName, String lastName, String username, String password, String email, String position, String department, List roles) {
		
		// User user = new User(
  //                               firstName: firstName,
  //                               middleName: middleName,
  //                               lastName: lastName,
  //                               username: username,
  //                               password: password,
  //                               email: email,
  //                               position: position,
  //                               department: department)
		user.save()
		if(user.validate()) {
			userRoleService.save(user, roles as Set)
		}
		println user
		return user
	}

	User getUsers() {
		User users = User.list()
		return users
	}
}