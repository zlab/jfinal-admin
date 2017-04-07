<title>角色管理 &lsaquo; ${_configMap.APP_NAME!}</title>

<div class="row">
    <div class="col-xs-12">

        <div class="space-4"></div>

        <form id="query-form" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 col-md-1 control-label">角色名称</label>

                <div class="col-sm-3">
                    <input type="text" class="col-xs-10" name="name"/>
                </div>

                <button type="button" id="role-query" class="btn btn-primary btn-white">
                    <i class="ace-icon fa fa-search"></i> 查 询
                </button>

                <button type="button" class="btn btn-white btn-info reset"
                        onclick="$('#query-form').resetFields()">重 置
                </button>

                <button type="button" class="btn btn-white btn-success"
                        onclick="sysRole.showInput('add')">新 增
                </button>
            </div>

        </form>

        <!-- table -->
        <div id="role-list">
        <#include "roleList.ftl" />
        </div>

        <!-- modal -->
        <div id="role-input-modal" class="modal"></div>

        <div id="role-menu-modal" class="modal">
        <#include "roleMenu.ftl" />
        </div>
    </div>
</div>

<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', ['${ctx}/js/sys/SysRole.js'], function () {
        $(function () {
            $('#role-query').trigger('click');
        });
    });
</script>