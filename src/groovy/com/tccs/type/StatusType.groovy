package com.tccs.type

enum StatusType {
	STATUS_APPROVED("APPROVED"),
	STATUS_PENDING("PENDING"),
	STATUS_DENIED("DENIED"),
	STATUS_RESOLVEDAPPROVED("RESOLVED: APPROVED"),
	STATUS_RESOLVEDDENIED("RESOLVED: DENIED"),


	String name 
	
	StatusType (String name) {
		this.name = name
	}

	String getKey() {
		name()
	}
}