package com.tccs

class Proof {

	byte[] fileData
	String fileName

	static belongsTo = [correction: Correction]

    static constraints = {
    	fileData maxSize: 5242880 //5MB
    }
}
