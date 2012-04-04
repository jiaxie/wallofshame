<#include "main.ftl">

<@main title="Control Panel"
script="index.js"
httpEquiv="refresh"
content="60;url=${requestContext.contextPath}/index.html?payroll=${selectedPayroll}&office=${selectedOffice}">
<body>
    <#include "includes/revision.ftl">
<h1 class="title">Please submit your timesheet</h1>

<p>

<form method="POST"
      action="${requestContext.contextPath}/index.html?payroll=${selectedPayroll}&office=${selectedOffice}">
    <label>Payroll:</label>
    <select id="payrollSelect">
        <#list payrolls as payroll>
            <#if payroll.code == selectedPayroll>
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
    <h2 style="padding-right:20px;">Last Updated: <span
            id="lastUpdateTime">${lastUpdateTime?string("yyyy/MM/dd HH:mm:ss")}</span></h2>
</div>
<div style="height:20px;"/>
<ul id="wallslider" name="wallslider">

    <#list peoples as people>
        <li class="missing">${people.name}</li>
    </#list>
</ul>
</body>
</@main>

