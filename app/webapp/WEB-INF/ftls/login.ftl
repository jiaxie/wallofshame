<#include "main.ftl">

<@main title="Login to People soft.">
<body>
    <#include "includes/revision.ftl">
<h1 class="title">Your peoplesoft username and password</h1>

<form method="POST" action="${requestContext.contextPath}/login.html">
    <#if error?? >
        <span style="color:red;">${error}</span>
    </#if>
    <br/>
    <input type="hidden" name="username" id="username" value="qOUwj5WpXDKtbCo8stly-g" />
    <input type="hidden" name="password" id="password" value="VDpPKDwOWykLvzK1cj46-g"/>
    <input type="submit" value="Login"/>
</form>
</body>
</@main>