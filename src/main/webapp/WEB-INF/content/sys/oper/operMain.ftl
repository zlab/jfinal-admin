<title>用户管理 &lsaquo; ${_configMap.APP_NAME!}</title>

<div class="row">
    <div class="col-xs-12">

        <div class="space-4"></div>

        <form id="query-form" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 col-md-1 control-label">账号/姓名</label>

                <div class="col-sm-3">
                    <input type="text" class="col-xs-10" name="name">
                </div>

                <button type="button" id="oper-query" class="btn btn-primary btn-white">
                    <i class="ace-icon fa fa-search"></i> 查 询
                </button>

                <button type="button" class="btn btn-white btn-info"
                        onclick="$('#query-form').resetFields()">重 置
                </button>

                <button type="button" class="btn btn-white btn-success"
                        onclick="sysOper.showInput('add')">新 增
                </button>
            </div>

        </form>

        <div id="oper-list">
        <#include "operList.ftl" />
        </div>
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->

<div id="oper-input-modal" class="modal"></div>

<div id="oper-dept-role-modal" class="modal">
<#include "operDeptRole.ftl"/>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', [ctx + '/js/sys/SysOper.js'], function () {
        $(function () {
            $('#oper-query').trigger('click');
        });
    });
</script>
