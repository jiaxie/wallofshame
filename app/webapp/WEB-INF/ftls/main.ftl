<#macro main title="" scripts=[] httpEquiv="" content="" css="">
<!DOCTYPE html>
<html>
<head>
    <#if httpEquiv != "" && content != "">
        <meta http-equiv="${httpEquiv}" content="${content}">
    </#if>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <title>${title}</title>

    <!-- Framework CSS -->
    <link rel="stylesheet" href="${requestContext.contextPath}/css/screen.css" type="text/css"
          media="screen, projection">
    <link rel="stylesheet" href="${requestContext.contextPath}/css/plugins/buttons/screen.css" type="text/css"
          media="screen, projection">
    <link rel="stylesheet" href="${requestContext.contextPath}/css/main.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="${requestContext.contextPath}/css/print.css" type="text/css" media="print">
    <link rel="stylesheet" href="${requestContext.contextPath}/css/global.css" type="text/css">
    <!--[if lt IE 8]>
    <link rel="stylesheet" href="${requestContext.contextPath}/css/ie.css" type="text/css" media="screen, projection">
    <![endif]-->
    <#if css !="">
        <link rel="stylesheet" href="${requestContext.contextPath}/css/${css}" type="text/css">
    </#if>

    <script type="text/javascript" src="${requestContext.contextPath}/javascripts/lib/jquery-1.7.2.min.js"></script>
    <#list scripts as script>
        <script type="text/javascript" src="${requestContext.contextPath}/javascripts/${script}"></script>
    </#list>
</head>
    <#nested/>
</html>
</#macro>