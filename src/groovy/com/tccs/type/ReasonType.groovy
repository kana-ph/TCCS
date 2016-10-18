package com.tccs.type

enum ReasonType {
	REASON_MISSEDLOG("Missed Log"),
	REASON_SYSTEMERROR("System Error"),
	REASON_OFFSITEDUTY("Offsite Duty")

	String name 
	ReasonType (String name) {
		this.name = name
	}

	String getKey() {
		name()
	}
}