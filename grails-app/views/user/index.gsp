<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Users</title>
	</head>
	<body>
		<fieldset>
			<legend>Users</legend>
			<g:each in="${users}" var="user" status="i">
           	 	<h3>${i+1}. "${user.firstName} ${user.middleName} ${user.lastName}" ${user.getAuthorities()}</h3>
          	  	<br/>
       	 	</g:each>
		</fieldset>        
	</body>
</html>