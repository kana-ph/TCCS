package com.tccs.type

enum EntryType {
	ENTRY_CLOCKIN("Clock In"),
	ENTRY_CLOCKOUT("Clock Out"),
	ENTRY_BREAKIN("Break In"),
	ENTRY_BREAKOUT("Break Out")

	String name 
	EntryType (String name) {
		this.name = name
	}

	String getKey() {
		name()
	}
}