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

    <label for="username">
        Username:
    </label>
    <input type="text" name="username" id="username">

    <label for="password">
        Password:
    </label>
    <input type="password" name="password" id="password">
    <input type="submit" value="Login"/>
</form>
</body>
</@main>