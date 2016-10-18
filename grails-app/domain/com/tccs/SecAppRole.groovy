package com.tccs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class SecAppRole implements Serializable {

	private static final long serialVersionUID = 1

	String authority

	SecAppRole(String authority) {
		this()
		this.authority = authority
	}

	static constraints = {
		authority blank: false, unique: true
	}

	static mapping = {
		cache true
	}
}
