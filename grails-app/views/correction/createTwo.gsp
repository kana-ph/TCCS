<%@ page import="com.tccs.type.EntryType" %>
<%@ page import="com.tccs.type.ReasonType" %>

<html>
	<head>
		<title>Upload Proof</title>
		<meta name="layout" content="theme"/>
	</head>
 	<body>
 		<div>
			<fieldset>
				<legend>Create Correction</legend>
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
												<span>${ReasonType.valueOf(reason).name}</span>
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
												<span>${EntryType.valueOf(entryRequired).name}</span>
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
									        	<span>${dateTimeCorrection}</span>
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
										<span>${comment}</span>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<br />
					<label style="font-size: 16px;">&nbspUpload proof/s:</label>
					<g:uploadForm method="POST" action="save" controller="correction" id="dz-form" style="width: 35%">
						<g:hiddenField name="reason" value="${reason}" />
						<g:hiddenField name="entryRequired" value="${entryRequired}" />
						<g:hiddenField name="dateTimeCorrection" value="${dateTimeCorrection}" />
						<g:hiddenField name="comment" value="${comment}" />
						<g:hiddenField name="user" value="${user}" />
						<g:submitButton name="save" value="Save Correction"/>
					</g:uploadForm>
					<g:if test="${flash.message}">
							<div class="message" style="display: block">${flash.message}</div>
					</g:if>
				<br />
				<br />
			</fieldset>
		</div>
		<script type="text/javascript">
			$(function () {
                $('#datetimepicker').datetimepicker({
					sideBySide: true
                });
                $('#dz-form').dropzone({
                	url: '/correction/save',
					autoProcessQueue: false,
					uploadMultiple: true,

					parallelUploads: 2,
					maxFiles: 3,
					init: function() {
						var dz = this;
						
						this.element.querySelector("button[type=submit]")
						.addEventListener("click", function(e) {
							e.preventDefault();
							e.stopPropagation();
							dz.processQueue();
						});
					}
				});
            });
            
		</script>
 	</body>
</html>