<%@ page import="com.tccs.type.EntryType" %>
<%@ page import="com.tccs.type.ReasonType" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Create Correction</title>
		<meta name="layout" content="theme"/>
	</head>
	<body>
		<div>
			<fieldset>
				<legend>Create Correction</legend>
				<g:form controller="correction" action="save">
					<table style="width: 20%;">
						<tbody>
							<tr>
								<td>
									<label for="reason">Reason for correction request: </label>
								</td>
								<td>
									<div class="container">
										<div class="row">
											<div class='col-sm-4'>
												<g:select name="reason" from="${ReasonType.values()}" value="${correction?.reason}" noSelection="['':'-select reason-']" optionKey="key" optionValue="name" />
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label for="reason">Entry type for correction: </label>
								</td>
								<td>
									<div class="container">
										<div class="row">
											<div class='col-sm-4'>
												<g:select name="entryRequired" from="${EntryType.values()}" value="${correction?.entryRequired}" noSelection="['':'-select entry-']" optionKey="key" optionValue="name" />
											</div>
										</div>
							        </div>
								</td>
							</tr>
							<tr>
								<td>
									<label for="dateTimeCorrection">Correct date and time: </label>
								</td>
								<td>
									<div class="container">
									    <div class="row">
									        <div class='col-sm-4'>
									            <g:textField name="dateTimeCorrection" class="form-control" id='datetimepicker' value="${correction?.dateTimeCorrection}"/>
									        </div>
									    </div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label for="comment">Comments: </label>
								</td>
								<td>
									<div class="col-sm-4">
										<g:textArea name="comment" rows="4" cols="42" value="${correction?.comment}" />
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<g:actionSubmit value="Upload Proof" action="uploadProof"/>
								</td>
							</tr>
						</tbody>
					</table>
					<br />
					<br />
					<br />
					<g:submitButton name="save" value="Submit"/>
					%{-- <div class="alert alert-error" style="display: block">
						<g:eachError bean="${user}">
							<li>${it}</li>
						</g:eachError>
					</div> --}%
					<g:if test="${flash.message}">
						<div class="message" style="display: block">${flash.message}</div>
					</g:if>
				</g:form>
			</fieldset>
		</div>
		 <script type="text/javascript">
			$(function () {
                $('#datetimepicker').datetimepicker({
					sideBySide: true
                });
            });
        </script>
	</body>
</html>