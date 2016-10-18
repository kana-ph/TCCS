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
			<g:each in="${corrections}" var="correction">
				<h4>
					${correction.id}. ${correction.user.firstName} ${correction.user.middleName} ${correction.user.lastName} <font style="text-transform: uppercase;"><strong>${correction.status.name}</strong></font>
					<g:link controller="correction" action="review" params="[id: correction.id]">
						<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_HEAD'>review</sec:ifAnyGranted>
						<sec:ifNotGranted roles='ROLE_ADMIN,ROLE_HEAD'>view</sec:ifNotGranted>
					</g:link>
				</h4>
			</g:each>
		</fieldset>
	</body>
</html>