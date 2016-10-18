package com.tccs

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRole {
	User user
	Role role
}