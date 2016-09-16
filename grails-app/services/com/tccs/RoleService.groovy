package com.tccs

import grails.transaction.Transactional
import com.tccs.type.RoleAuthority

@Transactional
class RoleService {
	Role fetchRoleByAuthority(RoleAuthority authority) {
		return Role.findByAuthority("$authority")
	}
}
