package com.tccs

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Role {

    String authority

    String toString(){
    	return authority
    }

    static constraints = {
    	authority(blank: false, unique: true, nullable: false)
    }
}
