<title>首页 &lsaquo; ${_configMap.APP_NAME!}</title>

<div class="row">
    <div class="col-xs-12">

        <div class="page-header">
            <h1>系统信息</h1>
        </div>

        <div>
            <p>登陆部门：${loginInfo.dept.name}</p>
            <p>部门角色：${loginInfo.role.name}</p>
            <p></p>
            <p></p>
        </div>
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', [], function() {
        $(function() {

        });
    });
</script>
