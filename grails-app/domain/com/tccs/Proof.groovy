package com.tccs

class Proof {

	byte[] fileData
	String fileName

	static belongsTo = [correction: Correction]

    static constraints = {
		fileData maxSize: 5242880, contentType: ['image/jpg', 'image/png'], nullable: true
		fileName nullable: true
    }
}
