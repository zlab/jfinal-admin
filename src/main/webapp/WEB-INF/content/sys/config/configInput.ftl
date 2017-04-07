<div class="page-header">
<#if config?exists>
    <h1>修改配置</h1>
<#else>
    <h1>新增配置</h1>
</#if>
</div>

<form id="config-form" class="form-horizontal">
    <input type="hidden" name="config.configid" id="configid" value="${(config.id)!}"/>
    <input type="hidden" name="config.name" id="name" value="${(parent.value)!}"/>

    <input type="hidden" name="parentid" value="${(parent.id)!}"/>

    <div class="form-group">
        <label class="col-sm-2 control-label">配置名称</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" value="${(parent.memo)!}" readonly/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" for="value">配置值</label>

        <div class="col-sm-6">
            <input type="text" class="form-control required" id="value" name="config.value"
                   value="${(config.value)!}"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" for="memo">配置说明</label>

        <div class="col-sm-6">
            <input type="text" class="form-control required" id="memo" name="config.memo"
                   value="${(config.memo)!}"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" for="deptname">适用部门</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="deptname" value="${(config.deptname)!}" readonly/>

            <p class="help-block">不输入则适用所有部门</p>
        </div>
    </div>

    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button type="button" class="btn btn-primary width-30"
                    onclick="$('#config-form').submit()">
                <i class="ace-icon fa fa-check bigger-110"></i> 保 存
            </button>

        <#if config?exists>
            <button type="button" class="btn btn-success btn-white"
                    onclick="sysConfig.showInput('', ${(parent.id)!})">新 增
            </button>

            <button type="button" class="btn btn-white btn-danger"
                    onclick="sysConfig.deleteConfig(${(config.id)!})">删除
            </button>
        </#if>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        $('#config-form').validate({
            submitHandler: sysConfig.saveConfig
        });
    });
</script>