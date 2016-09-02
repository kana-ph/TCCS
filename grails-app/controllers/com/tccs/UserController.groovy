package com.tccs

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class UserController {
    UserService userService
    def save(){
        String firstName = params.firstName
        String lastName = params.lastName
        String username = params.username
        String password = params.password
        String email = params.email
        def roles = params.roles
        def corrections = params.corrections
        User user = userService.saveUser()
    }
}
