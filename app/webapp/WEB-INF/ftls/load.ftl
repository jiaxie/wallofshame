<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Loading</title>
	<!-- Framework CSS -->
	<link rel="stylesheet" href="${requestContext.contextPath}/css/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/plugins/buttons/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/main.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/print.css" type="text/css" media="print">
	<!--[if lt IE 8]><link rel="stylesheet" href="${requestContext.contextPath}/css/ie.css" type="text/css" media="screen, projection"><![endif]-->
    <script type="text/javascript" src="${requestContext.contextPath}/javascripts/lib/jquery-1.7.2.min.js"></script>


<script language="javascript">
  function setSB(v, el) {
    var ie5 = (document.all  &&  document.getElementsByTagName);
    if (ie5 || document.readyState == "complete")     {
      filterEl = el.children[0];
      valueEl = el.children[1];
      filterEl.style.width = v*5 + "px";
      valueEl.innerText = v + "%";
    }
  }
  function fakeProgress(v, el) {
    if (v > 100)
      location.href = '${requestContext.contextPath}/index.html';
    else     {
      setSB(v, el);
      window.setTimeout("fakeProgress(" + (++v) + ", document.all['" + el.id + "'])", 50);
    }
  }
</script>
</head>

<body onload="fakeProgress(0, sb)">
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
</html>