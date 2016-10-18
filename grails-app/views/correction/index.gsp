<%@ page import="com.tccs.type.ReasonType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Corrections</title>
	</head>
	<body>
		<fieldset>
			<legend>Corrections</legend>
			<h4>
				<g:each in="${corrections}" var="correction" status="i">
					%{-- If current user is only user role --}%
					<sec:ifNotGranted roles='ROLE_ADMIN,ROLE_HEAD'>
						<g:if test="${correction.user == currentUser}">
							${correction.id}. ${correction.user.firstName} ${correction.user.middleName} ${correction.user.lastName} <font style="text-transform: uppercase;"><strong>${correction.status.name}</strong></font>
							<g:link controller="correction" action="review" params="[id: correction.id]">view</g:link>
						</g:if>
					</sec:ifNotGranted>
					%{-- If current user is administrator role --}%
					<sec:ifAnyGranted roles='ROLE_ADMIN'>
						${correction.id}. ${correction.user.firstName} ${correction.user.middleName} ${correction.user.lastName} <font style="text-transform: uppercase;"><strong>${correction.status.name}</strong></font>
						<g:link controller="correction" action="review" params="[id: correction.id]">review</g:link>
					</sec:ifAnyGranted>
					%{-- If current user is only head role --}%
					<sec:ifAnyGranted roles='ROLE_HEAD'>
						<sec:ifNotGranted roles='ROLE_ADMIN'>
							<g:if test="${correction.user.department == currentUser.department}">
								${correction.id}. ${correction.user.firstName} ${correction.user.middleName} ${correction.user.lastName} <font style="text-transform: uppercase;"><strong>${correction.status.name}</strong></font>
								<g:link controller="correction" action="review" params="[id: correction.id]">review</g:link>
							</g:if>
						</sec:ifNotGranted>
					</sec:ifAnyGranted>
					<br />
					%{-- <g:link controller="correction" action="review" params="[id: correction.id]">
					<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_HEAD'>review</sec:ifAnyGranted>
					<sec:ifNotGranted roles='ROLE_ADMIN,ROLE_HEAD'>view</sec:ifNotGranted>
					</g:link> --}%
				</g:each>
			</h4>
		</fieldset>
	</body>
</html>