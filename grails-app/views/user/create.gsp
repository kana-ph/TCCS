<%@ page import="com.tccs.type.RoleAuthority" %>

<html>
	<head>
		<title>Create User</title>
		<meta name="layout" content="theme"/>
	</head>
 	<body>
 		<div>
 			<fieldset>
 				<legend>Create User</legend>
				<g:form controller="user" action="save" onsubmit="return !!(validate() & checkPassword());">
					<table style="width: 25%; border-spacing: 5px; border-collapse: separate;">
 						<tbody>
 							<tr>
 								<td>
 									<label for="firstName">First Name: </label>
 								</td>
 								<td>
 									<g:textField name="firstName" value="${user?.firstName}" />
 								</td>
 							</tr>
 							<tr>
 								<td>
 									<label for="middleName">Middle Name: </label>
 								</td>
 								<td>
 									<g:textField name="middleName" value="${user?.middleName}" />
 								</td>
 							</tr>
 							<tr>
 								<td>
 									<label for="lastName">Last Name: </label>
 								</td>
 								<td>
 									<g:textField name="lastName" value="${user?.lastName}" />
 								</td>
 							</tr>
 							<tr>
 								<td>
 									<label for="username">Username: </label>
 								</td>
 								<td>
 									<g:textField name="username" value="${user?.username}" />
 								</td>
 							</tr>
 							<tr>
 								<td>
 									<label for="password">Password: </label>
 								</td>
 								<td>
									<g:passwordField id="password" name="password" value="${user?.password}" />
								</td>
 							</tr>
							<tr>
								<td>
									<label for="confirmPassword">Confirm password: </label>
								</td>
								<td>
									<g:passwordField id="confirm" name="confirmPassword" value="${user?.confirmPassword}" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="email">Email: </label>
								</td>
								<td>
									<g:textField name="email" value="${user?.email}" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="position">Position: </label>
								</td>
								<td>
									<g:textField name="position" value="${user?.position}" />
								</td>
							</tr>
							<tr>
								<td>
 									<label for="department">Department: </label>
 								</td>
 								<td>
 									<g:textField name="department" value="${user?.department}" />
 								</td>
 							</tr>
 							<tr>
 								<td>
 									<label>Role:<label /> 
 								</td>
 								<td>
 									<g:checkBox name="roleAdmin" value="${RoleAuthority.ROLE_ADMIN}" checked="false" />
 									<label for="roleAdmin">${RoleAuthority.ROLE_ADMIN.name}</label>
 									<br />
 									<g:checkBox name="roleHead" value="${RoleAuthority.ROLE_HEAD}" checked="false" />
 									<label for="roleHead">${RoleAuthority.ROLE_HEAD.name}</label>
 								</td>
 							</tr>
 						</tbody>
 					</table>				
 					<g:submitButton name="save" value="Save"/>
					%{-- <div class="alert alert-error" style="display: block">
	 					<g:eachError bean="${user}">
	  						<li>${it}</li>
						</g:eachError>
					</div> --}%
 					<g:if test="${flash.message}">
						<div class="row">
							<div class="message alert alert-danger alert-dismissible col-md-4" role="alert" style="display: block; margin-top: 5px;"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>${flash.message}</div>
						</div>
					</g:if>
					<div class="row">
						<div id="password-mismatch-message" class="message alert alert-danger alert-dismissible col-md-4" role="alert" style="display: none; margin-top: 5px;"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>Passwords don't match</div>
					</div>
 				</g:form>
 			</fieldset>	
 		</div>
		<script type="text/javascript">
			function validate() {
				var validPassword = checkPassword()
				return validPassword
			}

			function checkPassword() {
				var password = $("#password").val()
				var confirm = $("#confirm").val()
				if(password.value == confirm.value){
					return true
				} else {
					$("#password").val("")
					$("#confirm").val("")
					$("#password-mismatch-message").css({"display":"block"})
					return false
				}
			}
        </script>
 	</body>
</html>