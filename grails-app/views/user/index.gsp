<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Users</title>
	</head>
	<body>
		<legend>Users</legend>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Username</th>
					<th>Name</th>
					<th>Department</th>
					<th>Controls</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.lastName}, ${user.firstName} ${user.middleName}</td>
						<td>${user.department}</td>
						<td><g:link controller="user" action="edit" params="[id: user.id]"><i class="glyphicon glyphicon-pencil"></i>&nbsp;Edit</g:link></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</body>
</html>
