<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Login to People soft.</title>
	<!-- Framework CSS -->
	<link rel="stylesheet" href="${requestContext.contextPath}/css/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/plugins/buttons/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/main.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/print.css" type="text/css" media="print">
	<!--[if lt IE 8]><link rel="stylesheet" href="${requestContext.contextPath}/css/ie.css" type="text/css" media="screen, projection"><![endif]-->
    <script type="text/javascript" src="${requestContext.contextPath}/javascripts/lib/jquery-1.7.2.min.js"></script>

</head>
<body>
    <#include "includes/revision.ftl">
<h1 class="title">Your peoplesoft username and password</h1>
<form method="POST" action="${requestContext.contextPath}/login.html">
    <#if error?? >
    <span style="color:red;">${error}</span>
    </#if>
    <br/>
    <input name="username">
    <input name="password">
    <input type="submit" value="Save"/>
</form>
</body>
</html>

