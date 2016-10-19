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
				<tbody> <!-- TODO Make the service responsible for the filtering. Not the view -->
					<g:each in="${corrections}" var="correction">
					<tr>
						%{-- If current user is only user role --}%
						<sec:ifNotGranted roles='ROLE_ADMIN,ROLE_HEAD'>
							<g:if test="${correction.user == currentUser}">
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
								<td><g:link controller="correction" action="view" params="[id: correction.id]"><i class="glyphicon glyphicon-eye-open"></i>&nbsp;View</g:link></td>
							</g:if>
						</sec:ifNotGranted>
						%{-- If current user is administrator role --}%
						<sec:ifAnyGranted roles='ROLE_ADMIN'>
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
							<td><g:link controller="correction" action="review" params="[id: correction.id]"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;Review</g:link></td>
						</sec:ifAnyGranted>
						%{-- If current user is only head role --}%
						<sec:ifAnyGranted roles='ROLE_HEAD'>
							<sec:ifNotGranted roles='ROLE_ADMIN'>
								<g:if test="${correction.user.department == currentUser.department}">
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
									<td><g:link controller="correction" action="review" params="[id: correction.id]"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;Review</g:link></td>
								</g:if>
							</sec:ifNotGranted>
						</sec:ifAnyGranted>
					</tr>
					</g:each>
				</tbody>
			</table>
		</fieldset>
	</body>
</html>
