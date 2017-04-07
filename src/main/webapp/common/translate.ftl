<#macro translate gcode mcode defaults="">
    <#assign match = false>
    <#if mcode != "">
        <#list _paramList! as param>
            <#if param.gcode == gcode>
                <#if param.mcode == mcode>
                    <#assign match = true>
                ${param.mname}
                    <#break>
                </#if>
            </#if>
        </#list>
    </#if>
    <#if !match>${defaults}</#if>
</#macro>
