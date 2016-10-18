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
				<g:uploadForm controller="correction" action="save" >
					<table style="width: 80%; border-spacing: 5px; border-collapse: separate;">
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
									<label for="comment">Add Proof: </label>
								</td>
								<td>
									<div class="container">
									    <div class="row">
									        <div class='col-sm-4'>
												<input type="file" name="proof" multiple>
											</div>
										</div>
									</div>			
								</td>
							</tr>
							%{-- <tr>
								<td>
									<label for="comment">Add Proof: </label>
								</td>
								<td>
									<div class="container">
									    <div class="row">
									        <div class='col-sm-4'>
												<input type="file" name="file">
											</div>
										</div>
									</div>			
								</td>
							</tr>
							<tr>
								<td>
									<label for="comment">Add Proof: </label>
								</td>
								<td>
									<div class="container">
									    <div class="row">
									        <div class='col-sm-4'>
												<input type="file" name="file">
											</div>
										</div>
									</div>							
								</td>
							</tr> --}%
						</tbody>
					</table>
					%{-- <uploadr:add name="myUploadrName" path="/my/upload/path" direction="up" maxVisible="8" unsupported="/my/controller/action" rating="true" voting="true" colorPicker="true" maxSize="204800" /> --}%
					<g:submitButton name="save" value="Save"/>
					<g:if test="${flash.message}">
						<div class="message" style="display: block">${flash.message}</div>
					</g:if>
				</g:uploadForm>
				%{-- <label style="font-size: 16px;">Upload proof/s:</label>
				<g:form action="upload" controller="proof" class="dropzone" id="proof" style="width: 35%" params:"${correction.id}"></g:form>
				<br />
				<br /> --}%
			</fieldset>
		</div>
		 <script type="text/javascript">
			$(function () {
                $('#datetimepicker').datetimepicker({
					sideBySide: true
                });
            });
   //          Dropzone.options.dzForm = {
			// 	autoProcessQueue: false,
			// 	uploadMultiple: true,

			// 	parallelUploads: 2,
			// 	maxFiles: 3,
			// 	init: function() {
			// 		var dz = this;
					
			// 		this.element.querySelector("button[type=submit]")
			// 		.addEventListener("click", function(e) {
			// 			e.preventDefault();
			// 			e.stopPropagation();
			// 			dz.processQueue();
			// 		});
			// 	}
			// }
        </script>
	</body>
</html>