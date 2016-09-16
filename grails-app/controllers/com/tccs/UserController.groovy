package com.tccs

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured
import com.tccs.type.RoleAuthority
import grails.validation.ValidationException

class UserController {
    UserService userService

    @Secured(['ROLE_ADMIN'])
    def save(){
        println params
        String firstName = params.firstName
        String middleName = params.middleName
        String lastName = params.lastName
        String username = params.username
        String password = params.password
        String email = params.email
        String position = params.position
        String department = params.department
        List roles = fetchSelectedRoles(params.roleAdmin, params.roleHead)
        
        User user = new User(
                                firstName: firstName,
                                middleName: middleName,
                                lastName: lastName,
                                username: username,
                                password: password,
                                email: email,
                                position: position,
                                department: department)

        // User user = userService.saveUser(firstName, middleName, lastName, username, password, email, position, department, roles)
        userService.saveUser(user, roles)
        
        if(user.validate()) {
            flash.message = "default.created.message"
        }    
        // println user
        redirect(action: "create", params: [user: user])
    }

    @Secured(['ROLE_ADMIN'])
    def create(){ }

    @Secured(['ROLE_ADMIN'])
    def index(){ 
        def users = User.list()
        [users: users]
    }

    private List fetchSelectedRoles(String roleAdmin, String roleHead) {
        List roles = [RoleAuthority.ROLE_USER]
        if(roleAdmin){
            roles<<RoleAuthority.ROLE_ADMIN
        }
        if(roleHead){
            roles<<RoleAuthority.ROLE_HEAD
        }
        return roles
    }
}
