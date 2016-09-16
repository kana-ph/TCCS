package com.tccs

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
		println "***"
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']
	
	static mapping = {
		password column: '`password`'
	}

    static constraints = {
    	firstName(nullable: true, blank: false)
    	middleName(nullable: true, blank: false)
    	lastName(nullable: true, blank: false)
    	username(nullable: false, blank: false, unique: true)
    	password(password: true, nullable: false, blank: false)
    	email(email: true, nullable: true, blank: false, unique: true)
    	position(nullable: true, blank: false)
    	department(nullable: true, blank: false)
    }
}
