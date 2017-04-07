<#include "/common/pagination.ftl">

<table class="table">
    <thead>
    <tr>
        <th>用户</th>
        <th>部门</th>
        <th>角色</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list (page.list)! as deptRole>
    <tr>
        <td>${deptRole.opername!}</td>
        <td>${deptRole.deptname!}</td>
        <td>${deptRole.rolename!}</td>
        <td class="padding-5">
            <button type="button" class="btn btn-xs btn-white btn-danger"
                    onclick="sysOper.deleteOperDeptRole(${deptRole.id})">删除
            </button>
        </td>
    </tr>
    </#list>
    </tbody>
</table>

<!-- pagination -->
<@pagination page=page! pageSize=6/>

<script type="text/javascript">$(function () {
    $('#oper-dept-role-list .table').table(6);
    $('#oper-dept-role-list .paging').paging('sysOper.queryOperDeptRoleList');
});
</script>