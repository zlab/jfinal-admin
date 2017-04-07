<#macro option gcode mcode='' please=true>
    <#if please>
    <option value="">--请选择--</option>
    </#if>
    <#list _paramList! as param>
        <#if param.gcode == gcode>
            <#assign selected = "">
            <#if param.mcode?string == mcode?string>
                <#assign selected = " selected=\"selected\"">
            </#if>
        <option value="${param.mcode}" ${selected!}>${param.mname}</option>
        </#if>
    </#list>
</#macro>
