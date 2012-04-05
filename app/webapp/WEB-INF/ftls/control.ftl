<#include "main.ftl">

<@main title="Control Panel">
<body>
    <#include "includes/revision.ftl">
<h1 class="title">Control Panel</h1>
<br/>

<form method="POST" action="${requestContext.contextPath}/control.html">
    <span>You can send email notifications manually by click</span>
    <input type="submit" value="Send Emails"/>
    <#if info??>
        <span style="color:red">${info}</span>
    </#if>
</form>
</body>
</@main>

