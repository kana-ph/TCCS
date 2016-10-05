<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Corrections</title>
	</head>
	<body>
		<fieldset>
			<legend>Users</legend>
			<g:each in="${corrections}" var="correction" status="i">
				<h4>
					${correction.id}. ${correction.user.firstName} ${correction.user.middleName} ${correction.user.lastName}
					<g:link controller="correction" action="review" params="[id: correction.id]">review</g:link>
				</h4>
			</g:each>
		</fieldset>
	</body>
</html>