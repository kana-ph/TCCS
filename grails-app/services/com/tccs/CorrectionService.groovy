package com.tccs

import grails.transaction.Transactional
import com.tccs.type.StatusType
import com.tccs.type.ReasonType
import com.tccs.type.EntryType

@Transactional
class CorrectionService {
	ProofService proofService

	Correction saveCorrection(Date dateTimeCorrection, ReasonType reason, EntryType entryRequired, String comment, User user, List files) {

		def correction = new Correction(
										dateTimeCorrection: dateTimeCorrection,
										reason: reason,
										entryRequired: entryRequired,
										comment: comment,
										status: StatusType.STATUS_PENDING,
										user: user)

		files.each { file->
            if(file.originalFilename.endsWith(".png") || file.originalFilename.endsWith(".jpg")) {
                proofService.createFile(correction, file.originalFilename, file.bytes)
                // flash.message = "Successfully Sent"
            }else {
                throw new IllegalArgumentException()
                // flash.message = "Only PNG or JPEG files please."
            }
        }

		correction.save(failOnError: true)

		return correction
	}

	Correction updateCorrection(Long id, StatusType status) {

		def correction = Correction.get(id)
		correction.status = status

		correction.save(failOnError: true, flush: true)
	}

	List<Correction> fetchCorrectionsVisibleToUser(User user) {
		List<Role> roles = user.authorities

	}
}
