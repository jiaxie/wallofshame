<#include "main.ftl">

<@main title="Loading" script="load.js" >
<body onload="fakeProgress(0, sb, '${requestContext.contextPath}/index.html')">
<center>
    <br><br>
    <font size="15" color="red">Loading data, please wait for a moment!</font>
    <br><br>
<span id=sb style="width: 302px">
<div style="filter: Alpha(Opacity=20);margin-left:400px; width: 0%;height: 40px; position: absolute; background: #0066cc"></div>
<div style="font-size: 30pt; width: 100%; margin: 40px 0px 0px 0px;  text-align: center"></DIV>
</span>
</center>
</body>
</@main>