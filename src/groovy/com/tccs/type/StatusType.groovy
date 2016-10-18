package com.tccs.type

enum StatusType {
	STATUS_APPROVED("Approved"),
	STATUS_PENDING("Pending"),
	STATUS_DENIED("Denied")

	String name 
	
	StatusType (String name) {
		this.name = name
	}

	String getKey() {
		name()
	}
}