package com.tccs

import grails.transaction.Transactional
import com.tccs.type.RoleAuthority

@Transactional
class UserRoleService {
	RoleService roleService
	public void saveUserRole(User user, Set<RoleAuthority> userRoleName) {
		userRoleName.each { roleAuthority ->
			UserRole userRole = new UserRole()
			userRole.user = user
			userRole.role = roleService.fetchRoleByAuthority(roleAuthority)
			userRole.save()
		}
	}

	public void updateUserRole(User user, Set<RoleAuthority> newEntries) {
		Set<Role> existingRoles = user.authorities
		Set<Role> incomingRoles = roleService.fetchRolesByAuthorities(newEntries)

		Set<Role> toDelete = existingRoles - incomingRoles
		Set<Role> toAdd	= incomingRoles - existingRoles

		toAdd.each { role -> 
			UserRole userRole = new UserRole(user: user, role: role)
			userRole.save()
		}

		Set<UserRole> oldUserRoles = UserRole.findAllByUserAndRoleInList(user, toDelete)
		oldUserRoles*.delete()
	}
}