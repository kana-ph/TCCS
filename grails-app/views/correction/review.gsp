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
				<g:form controller="correction" action="update" id="${correction.id}" onsubmit="validate()">
					<g:hiddenField name="id" value="${correction.id}" />
					<table style="width: 30%; border-spacing: 5px; border-collapse: separate;">
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
									<label for="files">Relevant files: </label>
								</td>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
					<g:if test="${correction.proofs.size() > 0}">
						<div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
							<div class="carousel-inner" role="listbox">
								<g:each in="${correction.proofs}" var="proof" status="i">
									<g:if test="${i == 0}">
										<div class="item active">
									</g:if>
									<g:elseif test="${i != 0}">
										<div class="item">
									</g:elseif>
									<g:if test="${proof.fileName}">
										<img src="data:image/png;base64,${proof.fileData.encodeBase64()}"/>
									</g:if>
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
					</g:if>
					<g:else>
						&nbsp&nbsp&nbspNo Uploaded File
					</g:else>
					<br />
					<g:if test="${correction.status != StatusType.STATUS_RESOLVEDAPPROVED && correction.status != StatusType.STATUS_RESOLVEDDENIED}">
						%{-- if user is both head and admin --}%
						<sec:ifAllGranted roles='ROLE_HEAD,ROLE_ADMIN'>
							<h3><strong>
							<g:hiddenField name="statusChange" value="" />
							<div class="btn-group" role="group" aria-label="...">
								<p>
									<button type="button" class="btn btn-success btn-lg" onclick="setStatus('STATUS_RESOLVEDAPPROVED')"><i class="glyphicon glyphicon-ok"></i> Approve</button>
									<button type="button" class="btn btn-danger btn-lg" onclick="setStatus('STATUS_RESOLVEDDENIED')"><i class="glyphicon glyphicon-remove"></i> Deny</button>
								</p>
							</div>
							</h3></strong>
							<g:submitButton name="submit" value="Resolve" class="btn btn-default btn-lg" />
						</sec:ifAllGranted>
						%{-- if user is only head --}%
						<sec:ifAnyGranted roles='ROLE_HEAD'>
							<sec:ifNotGranted roles='ROLE_ADMIN'>
								<h3><strong>
								<g:hiddenField name="statusChange" value="" />
								<div class="btn-group" role="group" aria-label="...">
									<p>
										<button type="button" class="btn btn-success btn-lg" onclick="setStatus('STATUS_APPROVED')"><i class="glyphicon glyphicon-ok"></i> Approve</button>
										<button type="button" class="btn btn-danger btn-lg" onclick="setStatus('STATUS_DENIED')"><i class="glyphicon glyphicon-remove"></i> Deny</button>
									</p>
								</div>
								</h3></strong>
								<g:submitButton name="submit" value="Submit" class="btn btn-default btn-lg" />
							</sec:ifNotGranted>
						</sec:ifAnyGranted>
						%{-- if user is only admin --}%
						<sec:ifAnyGranted roles='ROLE_ADMIN'>
							<sec:ifNotGranted roles='ROLE_HEAD'>
								<g:if test="${correction.status != StatusType.STATUS_PENDING}">
									<h3><strong>
									<g:hiddenField name="statusChange" value="" />
									<div class="btn-group" role="group" aria-label="...">
										<p>
											<button type="button" class="btn btn-success btn-lg" onclick="setStatus('STATUS_RESOLVEDAPPROVED')"><i class="glyphicon glyphicon-ok"></i> Approve</button>
											<button type="button" class="btn btn-danger btn-lg" onclick="setStatus('STATUS_RESOLVEDDENIED')"><i class="glyphicon glyphicon-remove"></i> Deny</button>
										</p>
									</div>
									</h3></strong>
									<g:submitButton name="submit" value="Resolve" class="btn btn-default btn-lg" />
								</g:if>
								<g:else>
									<em>Requires review from department head.</em>
								</g:else>
							</sec:ifNotGranted>
						</sec:ifAnyGranted>
					</g:if>
					<g:if test="${flash.message}">
						<div class="row">
							<div class="message alert alert-danger alert-dismissible col-md-4" role="alert" style="display: block; margin-top: 5px;"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>${flash.message}</div>
						</div>
					</g:if>
				</g:form>
		</fieldset>
		<script type="text/javascript">
			function validate() {
				return false
			}
			function setStatus(status) {
				$('input[name="statusChange"]').val(status)
			}
		</script>
	</body>
</html>
