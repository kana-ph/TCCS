<%@ page import="com.tccs.type.ReasonType" %>
<%@ page import="com.tccs.type.StatusType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Corrections</title>
	</head>
	<body>
		<fieldset>
			<legend>Corrections</legend>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Staff Name</th>
						<th>Status</th>
						<th>Controls</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${corrections}" var="correction">
						<tr>
							<td>${correction.id}</td>
							<td>${correction.user.lastName}, ${correction.user.firstName} ${correction.user.middleName}</td>
							<g:if test="${StatusType.STATUS_APPROVED == correction.status}">
								<td class="success">${correction.status.name}</td>
							</g:if>
							<g:elseif test="${StatusType.STATUS_DENIED == correction.status}">
								<td class="danger">${correction.status.name}</td>
							</g:elseif>
							<g:else>
								<td>${correction.status.name}</td>
							</g:else>
							<td>
								<g:link controller="correction" action="review" params="[id: correction.id]">
									<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_HEAD"><i class="glyphicon glyphicon-check"></i>&nbsp;Review</sec:ifAnyGranted>
									<sec:ifNotGranted roles="ROLE_ADMIN,ROLE_HEAD"><i class="glyphicon glyphicon-eye-open"></i>&nbsp;View</sec:ifNotGranted>
								</g:link>
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</fieldset>
	</body>
</html>
