<%@ page import="com.tccs.type.RoleAuthority" %>
<%@ page import="com.tccs.type.StatusType" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Corrections</title>
	</head>
	<body>
		<fieldset>
				<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_HEAD'>
					<legend>Review Correction</legend>
				</sec:ifAnyGranted>
				<sec:ifNotGranted roles='ROLE_ADMIN,ROLE_HEAD'>
					<legend>Corrections</legend>
				</sec:ifNotGranted>
				<g:form controller="correction" action="update" id="${correction.id}">
					<g:hiddenField name="id" value="${correction.id}" />
					<table style="width: 25%;">
						<tbody>
							<tr>
								<td>
									<label for="reason">Reason for correction request: </label>
								</td>
								<td>
									${correction?.reason.name}
								</td>
							</tr>
							<tr>
								<td>
									<label for="reason">Entry type for correction: </label>
								</td>
								<td>
									${correction?.entryRequired.name}
								</td>
							</tr>
							<tr>
								<td>
									<label for="dateTimeCorrection">Correct date and time: </label>
								</td>
								<td>
									${correction?.dateTimeCorrection.format('MMM. d yyyy, hh:mm a')}
								</td>
							</tr>
							<tr>
								<td>
									<label for="comment">Comments: </label>
								</td>
								<td>
									${correction?.comment}
								</td>
							</tr>
							<tr>
								<td>
									<label for="status">Status: </label>
								</td>
								<td>
								<font style="text-transform: uppercase;">${correction?.status.name}</font>
								</td>
							</tr>
							<tr>
								<td>
									<label for="status">Relevant files: </label>
								</td>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<g:each in="${correction.proofs}" var="proof" status="i">
								<g:if test="${i == 0}">
									<div class="item active">
								</g:if>
								<g:elseif test="${i != 0}">
									<div class="item">
								</g:elseif>
									<img src="data:image/png;base64,${proof.fileData.encodeBase64()}"/>
								</div>
							</g:each>
						</div>
						<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						</a>
					</div>
					<br />
					<br />
					<sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_HEAD'>
						<g:radioGroup name="statusChange" values="[StatusType.STATUS_APPROVED, StatusType.STATUS_DENIED]" labels="['Approve', 'Deny']">
							<g:message code="${it.label}" /> ${it.radio}
						</g:radioGroup>
						<br />
						<br />
							<g:submitButton name="submit" value="Submit" />
					</sec:ifAnyGranted>
					<g:if test="${flash.message}">
						<div class="message" style="display: block">${flash.message}</div>
					</g:if>
				</g:form>
		</fieldset>
	</body>
</html>
