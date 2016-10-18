package com.tccs.type

enum RoleAuthority {
	ROLE_ADMIN("Administrator"),
	ROLE_HEAD("Department Head"),
	ROLE_USER("Staff")

	String name 
	RoleAuthority(String name) {
		this.name = name
	}
}