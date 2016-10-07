package com.tccs

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured
import com.tccs.type.RoleAuthority
import grails.validation.ValidationException
import com.tccs.exception.InvalidInputException
import grails.plugin.springsecurity.SpringSecurityService

class UserController {
    UserService userService
    def springSecurityService

    @Secured(['ROLE_ADMIN'])
    def save(){
        String firstName = params.firstName
        String middleName = params.middleName
        String lastName = params.lastName
        String username = params.username
        String password = params.password
        // String passwordConfirm = params.passwordConfirm
        String email = params.email
        String position = params.position
        String department = params.department
        List roles = fetchSelectedRoles(params.roleAdmin, params.roleHead)

        try {
            userService.saveUser(firstName, middleName, lastName, username, password, email, position, department, roles)
            flash.message = "Successfully Saved"
            redirect(action: "create")
        } catch (InvalidInputException e) {
            flash.message = "Invalid Input"
            redirect(action: "create")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def create(){ }

    @Secured(['ROLE_ADMIN', 'ROLE_HEAD'])
    def index(){ 
        def user = springSecurityService.currentUser
        Set<Role> roles = user.authorities
        def users = User.list()

        [users: users, user: user, roles: roles*.authority]
    }

    @Secured(['ROLE_ADMIN'])
    def edit(){
        def id = params.id
        User user = User.get(id)
        def roles = user.authorities

        boolean roleAdmin = roles.find { it.authority == RoleAuthority.ROLE_ADMIN.name()}
        boolean roleHead = roles.find { it.authority == RoleAuthority.ROLE_HEAD.name()}
        render(view:'edit', model:[user: user, roleAdmin: roleAdmin, roleHead: roleHead, auths: roles])
    }

    @Secured(['ROLE_ADMIN'])
    def update(){
        def id = params.id
        def user = userService.fetchById(id as Long)
        String firstName = params.firstName
        String middleName = params.middleName
        String lastName = params.lastName
        String username = params.username
        String email = params.email
        String position = params.position
        String department = params.department
        List roles = fetchSelectedRoles(params.roleAdmin, params.roleHead)

        try {
            userService.updateUser(user, firstName, middleName, lastName, username, email, position, department, roles)
            flash.message = "Successfully Updated"
            redirect(action: "edit", params: [id: id])
        } catch (InvalidInputException e) {
            flash.message = "Invalid Input"
            redirect(action: "edit", params: [id: id])
        }
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
