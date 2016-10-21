package com.tccs

import com.tccs.type.EmailType

class EmailService {

	def groovyPageRenderer
	def mailService
	UserService userService

	static final String DEV_EMAIL = "kenichigouang@gmail.com"

	public void sendEmailCorrectionForReview(Correction correction) {

		User user = correction.user
		def head = userService.fetchHeadOfDepartment(user.department)

		def content = groovyPageRenderer.render(view: "/mail/correctionForReviewTemplate", model: [correction: correction])

		mailService.sendMail {
			to head.email
			from DEV_EMAIL
            subject "Request for time card correction review."
            html content
        }
	}

	public void sendEmailCorrectionForResolution(User user) {

		def admin = userService.fetchAdmin()

		mailService.sendMail {
			to admin.email
			from DEV_EMAIL
            subject "Request for time card correction resolution."
            html g.render(template: "mailCorrectionForResolutionTemplate", model: [firstName: user.firstName, lastName: user.lastName])
        }
	}

  //   public void sendEmailNotification(EmailType emailType, User user, Correction correction)

  //   	if(emailType == EmailType.CORRECTION_FORREVIEW) {

		//     def head = userService.fetchHeadOfDepartment(user.department)

		// 	mailService.sendMail {
		// 		to head.email
		// 		from DEV_EMAIL
  //               subject "Request for time card correction review."
  //               html g.render(template: "mailCorrectionForReviewTemplate", model: [firstName: user.firstName, lastName: user.lastName])
  //           }
		// }

		// else if(emailType == EmailType.CORRECTION_FORRESOLUTION) {

		//     def admin = userService.fetchAdmin()

		// 	mailService.sendMail {
		// 		to admin.email
		// 		from DEV_EMAIL
  //               subject "Request for reviewed time card resolution."
  //               html g.render(template: "mailCorrectionForResolutionTemplate", model: [firstName: user.firstName, lastName: user.lastName])
  //           }
		// }

		// else if(emailType = EmailType.CORRECTION_RESULT) {

		// 	def user = userService.fetchUserOfCorrection(correction)
		// }
}