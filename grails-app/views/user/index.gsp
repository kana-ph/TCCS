<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Users</title>
	</head>
	<body>
		<fieldset>
			<legend>Users</legend>
			<g:each in="${users}" var="eachUser" status="i">
				<h4>
					${eachUser.id}. ${eachUser.username} ${eachUser.firstName} ${eachUser.middleName} ${eachUser.lastName}
					<g:link controller="user" action="edit" params="[id: user.id]">edit</g:link>
				</h4>
       	 	</g:each>
		</fieldset>        
	</body>
</html>