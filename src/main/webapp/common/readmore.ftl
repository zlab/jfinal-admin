<#macro readmore content url title>
    <#assign start = content?index_of("<!--more-->")>
    <#if start != -1>
    ${content?substring(0, start)}</a></p>
    <p>
        <a class="read-more" href="${url}">阅读全文 — ${title}</a>
        <a class="more-link" href="${url}">阅读全文</a>
    </p>
    <#else>
    ${content}
    </#if>
</#macro>