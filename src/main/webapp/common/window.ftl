<#macro window bodyClass="">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "/common/source.ftl">
</head>
<body class="${bodyClass!}">
    <#nested>
</body>
</html>
</#macro>