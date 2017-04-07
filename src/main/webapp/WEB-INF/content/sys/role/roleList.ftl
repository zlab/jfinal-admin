<#include "/common/pagination.ftl">

<table class="table">
    <thead>
        <tr>
            <th>名称</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <#list (page.list)! as role>
            <tr>
                <td>${role.name}</td>
                <td>${role.memo!}</td>
                <td class="padding-5">
                    <button type="button" class="btn btn-success btn-white btn-xs"
                            onclick="sysRole.showInput('view', ${role.id})">查看</button>
                    <button type="button" class="btn btn-primary btn-white btn-xs"
                        onclick="sysRole.showInput('edit', ${role.id})">修改</button>
                    <button type="button" class="btn btn-info btn-white btn-xs"
                        onclick="sysRole.showEditRoleMenu(${role.id})">授权</button>
                    <button class="btn btn-danger btn-xs btn-white" type="button"
                        onclick="sysRole.deleteRole(${role.id},'${role.name}')">删除</button>
                </td>
            </tr>
        </#list>
    </tbody>
</table>

<!-- pagination -->
<@pagination page! />

<script type="text/javascript">
    $(function() {
        $('#role-list .table').table();
        $('#role-list .paging').paging('sysRole.queryRoleList', '#role-query');
    });
</script>