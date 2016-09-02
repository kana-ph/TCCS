package com.tccs

class User {

	String firstName
	String lastName
	String username
	String password
	String email

    String toString(){
        return username
    }

	static hasMany = [roles: Role, corrections: Correction]

    static constraints = {
    	firstName(nullable: false, blank: false, unique: true)
    	lastName(nullable: false, blank: false, unique: true)
    	username(nullable: false, blank: false, unique: true)
    	password(password: true, nullable: false, blank: false)
    	email(email: true, nullable: false, blank: false, unique: true)
    	roles(blank: false)
        corrections(display: false, blank: true, nullable: true)
    }
}
