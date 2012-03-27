<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Control Panel</title>
	<!-- Framework CSS -->
	<link rel="stylesheet" href="${requestContext.contextPath}/css/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/plugins/buttons/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/main.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/print.css" type="text/css" media="print">
	<!--[if lt IE 8]><link rel="stylesheet" href="${requestContext.contextPath}/css/ie.css" type="text/css" media="screen, projection"><![endif]-->
    <script type="text/javascript" src="${requestContext.contextPath}/javascripts/jquery-1.5.2.min.js"></script>

</head>
<body>
    <#include "/includes/revision.ftl">
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
</html>

