<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="refresh" content="60;url=${requestContext.contextPath}/${country}.html?office=${selectedOffice}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>People who did not commit timesheet on time.</title>
	<!-- Framework CSS -->
	<link rel="stylesheet" href="${requestContext.contextPath}/css/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/plugins/buttons/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/main.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="${requestContext.contextPath}/css/print.css" type="text/css" media="print">
	<!--[if lt IE 8]><link rel="stylesheet" href="${requestContext.contextPath}/css/ie.css" type="text/css" media="screen, projection"><![endif]-->
    <script type="text/javascript" src="${requestContext.contextPath}/javascripts/jquery-1.5.2.min.js"></script>
    <script type="text/javascript">
            jQuery(function() {
                setUpBrickSlider();
                setUpLastUpdateTimeCounter();
                jQuery('#companySelect').change(refreshPageForSelectedCompany);
                jQuery('#officeSelect').change(refreshPageForSelectedOffice);
            });

            function setUpLastUpdateTimeCounter(){
                var startDateStr = jQuery('#lastUpdateTime').text();
                var startDate = new Date(startDateStr);
                var startMills = startDate.getTime();
                setInterval(function(){
                    var now = new Date();
                    var nowMills = now.getTime();
                    var mins = Math.floor((nowMills-startMills)/(1000*60))%60;
                    var hours = Math.floor((nowMills-startMills)/(1000*60*60));
                    jQuery('#lastUpdateTime').text(hours+' hours '+ mins+' minutes ago');

                },500);
              }

            function interval(){
                return 3000;
            }

            function setUpBrickSlider(){
                setInterval(function() {

                    if(isAllBricksOnWall())
                        return;

                    slideBricks();

                },  interval());
            }

            function slideBricks(){

                $('#wallslider > li:first').fadeOut(1000,function(){
                     $('#wallslider > li:first').appendTo('#wallslider');
                     $('#wallslider > li:last').show();
                });
            }
            function isAllBricksOnWall(){
                 var viewportHeight = $(window).height();
                 if($('#wallslider >li:last').offset() == null)
                   return true;
                 var sliderHeight =  $('#wallslider > li:last').offset().top + $('#wallslider > li:last').outerHeight();
                 return viewportHeight >= sliderHeight;
            }



            function refreshPageForSelectedCompany(){
                var selectedCompany = jQuery('#companySelect').val();
                window.location.href = currentPath()+"/"+selectedCompany+".html?office=All";
            }

            function refreshPageForSelectedOffice(){
                var selectedCompany = jQuery('#companySelect').val();
                var selectedOffice = jQuery('#officeSelect').val();
                window.location.href = currentPath()+"/"+selectedCompany+".html?office="+selectedOffice;
            }

            function currentPath(){
                return "http://localhost:8080/timesheet"
            }
        </script>
</head>
<body>
    <#include "/includes/revision.ftl">
<h1 class="title">Please submit your timesheet</h1>
<p>
<form method="POST" action="${requestContext.contextPath}/${country}.html?office=${selectedOffice}">
        <label>Payroll:</label>
        <select id="companySelect">
        <#list payrolls as payroll>
            <#if payroll.code == country>
             <option value="${payroll.code}" selected="selected">${payroll.name}</option>
            <#else>
             <option value="${payroll.code}">${payroll.name}</option>
            </#if>
        </#list>
        </select>

        <label>Office:</label>
        <select id="officeSelect">
        <#if selectedOffice == 'All'>
            <option value="All" selected="selected">All</option>
        <#else>
            <option value="All">All</option>
        </#if>
        <#--
        <#list offices as office>
            <#if office == selectedOffice>
             <option value="${office}" selected="selected">${office}</option>
            <#else>
             <option value="${office}">${office}</option>
            </#if>
        </#list>
        -->
        </select>
    <input type="submit" value="Send Emails"/>
    <#if info??>
    <span style="color:red">${info}</span>
    </#if>
</form>
</p>
<div style="margin-top:-30px;width:100%;display:block;text-align:right;background-color:gray;">
<h2 style="padding-right:20px;">Last Updated: <span id="lastUpdateTime">${lastUpdateTime?string("yyyy/MM/dd HH:mm:ss")}</span></h2>
</div>
<div style="height:20px;" />
<ul id="wallslider" name="wallslider">
    
    <#list peoples as people>
        <li class="missing">${people.name}</li>
    </#list>
</ul>
</body>
</html>

