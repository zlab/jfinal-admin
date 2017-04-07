<#include "/common/pagination.ftl">
<#include "/common/translate.ftl">

<table class="table">
    <thead>
    <tr>
        <th>帐号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>手机</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list (page.list)! as oper>
    <tr>
        <td>${oper.username}</td>
        <td>${oper.name!}</td>
        <td><@translate gcode="SYS_GENDER" mcode=oper.gender! /></td>
        <td>${oper.age!}</td>
        <td>${oper.phone!}</td>
        <td class="padding-5">
            <button type="button" class="btn btn-success btn-xs btn-white"
                    onclick="sysOper.showInput('view', ${oper.id})">查看
            </button>
            <button type="button" class="btn btn-primary btn-xs btn-white"
                    onclick="sysOper.showInput('edit', ${oper.id})">修改
            </button>
            <button type="button" class="btn btn-xs btn-white btn-info"
                    onclick="sysOper.showOperDeptRole(${oper.id})">授权
            </button>
            <button type="button" class="btn btn-xs btn-white btn-warning"
                    onclick="sysOper.resetPassword(${oper.id},'${oper.name}')">重置密码
            </button>
            <button type="button" class="btn btn-xs btn-white btn-danger"
                    onclick="sysOper.deleteOper(${oper.id},'${oper.name}')">删除
            </button>
        </td>
    </tr>
    </#list>
    </tbody>
</table>

<!-- pagination -->
<@pagination page! />

<script type="text/javascript">
    $(function () {
        $('#oper-list .table').table();
        $('#oper-list .paging').paging('sysOper.queryOperList', '#oper-query');
    });
</script>
