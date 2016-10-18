package com.tccs

import org.springframework.security.access.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

class HomeController {

	def springSecurityService

	@Secured(['ROLE_USER'])
    def home() {
    	def user = springSecurityService.currentUser
    	Set<Role> roles = user.authorities

    	render(view: 'home', model:[user: user, roles: roles*.authority])
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def front() { }
}

