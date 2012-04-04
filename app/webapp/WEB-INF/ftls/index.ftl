<#include "main.ftl">

<@main title="Control Panel"
scripts=["lib/jquery.isotope.min.js", "lib/jquery.marquee.js", "index.js"]
css="index.css"
httpEquiv="refresh"
content="60;url=${requestContext.contextPath}/index.html?payroll=${selectedPayroll}&office=${selectedOffice}">
<body>

<header>
    <#include "includes/revision.ftl">
    <h1 class="title">Please submit your timesheet</h1>

    <form class="operation" method="POST"
          action="${requestContext.contextPath}/index.html?payroll=${selectedPayroll}&office=${selectedOffice}">
        <label for="payrollSelect">Payroll:</label>
        <select id="payrollSelect">
            <#list payrolls as payroll>
                <#if payroll.code == selectedPayroll>
                    <option value="${payroll.code}" selected="selected">${payroll.name}</option>
                <#else>
                    <option value="${payroll.code}">${payroll.name}</option>
                </#if>
            </#list>
        </select>

        <label for="officeSelect">Office:</label>
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
            <span class="info">${info}</span>
        </#if>

        <div class="update-info">
            <h2>Last Updated: ${lastUpdateTime?string("yyyy/MM/dd HH:mm:ss")}</h2>
        </div>
    </form>
</header>

<marquee behavior="scroll" scrollamount="3">
    <ul id="wallslider">
        <#list peoples as people>
            <li class="missing">${people.name}</li>
        </#list>
    </ul>
</marquee>

</body>
</@main>

