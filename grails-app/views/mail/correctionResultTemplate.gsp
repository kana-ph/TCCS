<%@ page import="com.tccs.type.StatusType" %>

Dear ${correction.user.firstName} ${correction.user.middleName} ${correction.user.lastName}:
<br /><br />
<g:if test="${correction.status == StatusType.STATUS_RESOLVEDAPPROVED}">
Your request for time card correction has been <u><em>approved</em></u>.
</g:if>
<g:else>
Your request for time card correction has been <u><em>denied</em></u>.
</g:else>
<br /><br />
You may view your details <a href="http://localhost:8080/TCCS/correction/review/${correction.id}"><strong>here</strong></a>.
<br /><br />
Thank you very much for your patience.