package com.tccs

import grails.transaction.Transactional
import com.tccs.type.StatusType
import com.tccs.type.ReasonType
import com.tccs.type.EntryType
import com.tccs.type.RoleAuthority

@Transactional
class CorrectionService {
	ProofService proofService
	EmailService emailService

	Correction saveCorrection(Date dateTimeCorrection, ReasonType reason, EntryType entryRequired, String comment, User user, List files) {

		def correction = new Correction(
										dateTimeCorrection: dateTimeCorrection,
										reason: reason,
										entryRequired: entryRequired,
										comment: comment,
										status: StatusType.STATUS_PENDING,
										user: user)

		files.each { file ->
			if(file.originalFilename.endsWith(".png") || file.originalFilename.endsWith(".jpg")) {
				proofService.createFile(correction, file.originalFilename, file.bytes)
			}else {
                throw new IllegalArgumentException()
            }
		}

		correction.save(failOnError: true)

		emailService.sendEmailCorrectionForReview(correction)

		return correction
	}

	Correction updateCorrection(Long id, StatusType status) {

		def correction = Correction.get(id)
		correction.status = status

		correction.save(failOnError: true, flush: true)

		return correction
	}

	List<Correction> fetchCorrectionsVisibleToUser(User user) {
		Set<Role> roles = user.authorities
		Set<RoleAuthority> roleAuthorities = roles*.authority.collect{RoleAuthority.valueOf(it)}

		if(roleAuthorities.contains(RoleAuthority.ROLE_ADMIN)) {
			return Correction.list()
		}
		else if(roleAuthorities.contains(RoleAuthority.ROLE_HEAD)) {
			def users = User.findAllByDepartment(user.department)
			return Correction.findAllByUserInList(users)
		}
		else if(roleAuthorities.contains(RoleAuthority.ROLE_USER)) {
			return Correction.findAllByUser(user)
		}
	}
}
