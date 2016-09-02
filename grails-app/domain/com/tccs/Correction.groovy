package com.tccs

class Correction {

	Date dateCreated
	Date lastUpdated
	Date dateConcerned = new Date()
	String content

    static hasOne = [user: User]

    static constraints = {
    	dateCreated()
    	lastUpdated()
    	dateConcerned(blank: false, nullable: false)
    	content(maxSize: 1000, blank: false, nullable: false)
    	user(blank: false, nullable: false)
    }
}
