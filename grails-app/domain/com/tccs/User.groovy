package com.tccs

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class User implements Serializable {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	String firstName
	String middleName
	String lastName
	String email
	String position
	String department
	String confirmPassword

	Set<Role> getAuthorities() {
		List roles = UserRole.findAllByUser(this)
		return roles*.role
	}

	static hasMany = [corrections: Correction]

    String toString(){
        return username
    }

    def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
		// confirmPassword = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(confirmPassword) : confirmPassword
	}

	static transients = ['springSecurityService', 'confirmPassword']
	
	static mapping = {
		password column: '`password`'
	}

    static constraints = {
		firstName nullable: true, blank: false
		middleName nullable: true, blank: false
		lastName nullable: true, blank: false
		username nullable: false, blank: false, unique: true
		password password: true, nullable: false, blank: false, minSize: 8, validator: {password, obj ->
																							def confirmPassword = obj.properties['confirmPassword']
																							if(confirmPassword == null) return true // skip matching password validation (only important when setting/resetting pass)
																							confirmPassword == password ? true : ['invalid.matchingpasswords']
																						}
		email email: true, nullable: true, blank: false, unique: true
		position nullable: true, blank: false
		department nullable: true, blank: false
    }
}