<%@ page import="com.tccs.type.RoleAuthority" %>

<html>
	<head>
		<title>Edit User</title>
		<meta name="layout" content="theme"/>
	</head>
	<body>
		<div>
			<fieldset>
				<legend>Edit User</legend>
				<g:form controller="user" action="update" id="${user.id}">
					<table style="width: 20%; border-spacing: 5px; border-collapse: separate;">
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
							%{-- <tr>
								<td>
									<label for="password">Password: </label>
								</td>
								<td>
									<g:passwordField name="password" value="${user?.password}" disabled="true" />
								</td>
							</tr> --}%
							%{-- <tr>
								<td>
									<label for="passwordConfirm">Re-type password: </label>
								</td>
								<td>
									<g:passwordField name="passwordConfirm" value="${user?.passwordConfirm}" />
								</td>
							</tr> --}%
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
									%{-- <span>${roleAdmin} ${roleHead} ${auths}</span><br> --}%
									<g:checkBox name="roleAdmin" value="${roleAdmin}" />
									<label for="roleAdmin">${RoleAuthority.ROLE_ADMIN.name}</label>
									<br />
									<g:checkBox name="roleHead" value="${roleHead}" />
									<label for="roleHead">${RoleAuthority.ROLE_HEAD.name}</label>
								</td>
							</tr>
						</tbody>
					</table>
					<g:submitButton name="update" value="Update" />
					<div class="alert alert-error" style="display: block">
						%{-- <g:eachError bean="${user}">
							<li>${it}</li>
						</g:eachError> --}%
					</div>
						<g:if test="${flash.message}">
							<div class="message" style="display: block">${flash.message}</div>
						</g:if>
				</g:form>
			</fieldset>
		</div>
		%{-- <asset:javascript src="application.js" />  --}%
	</body>
</html>