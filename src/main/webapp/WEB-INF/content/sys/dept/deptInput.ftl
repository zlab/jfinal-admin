<div class="page-header">
<#if dept?exists>
    <h1>修改部门</h1>
<#else>
    <h1>新增部门</h1>
</#if>
</div>

<form id="dept-form" class="form-horizontal">
    <input type="hidden" name="dept.deptid" id="deptid" value="${(dept.id)!}"/>
    <input type="hidden" name="dept.parentid" id="parentid" value="${(parent.id)!}"/>

    <div class="form-group">
        <label class="col-sm-2 control-label">上级部门</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" value="${(parent.name)!}" readonly/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" for="name">部门名称</label>

        <div class="col-sm-6">
            <input type="text" class="form-control required" name="dept.name" id="name" value="${(dept.name)!}"/>
        </div>
    </div>

    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">

            <button type="button" class="btn btn-primary width-30" onclick="$('#dept-form').submit()">
                <i class="ace-icon fa fa-check bigger-110"></i> 保 存
            </button>

        <#if dept?exists>

            <button type="button" class="btn btn-success btn-white"
                    onclick="sysDept.showInput('', ${(dept.id)!})">新 增
            </button>

            <button type="button" class="btn btn-white btn-danger"
                    onclick="sysDept.deleteDept(${(dept.id)!})">删除
            </button>
        </#if>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        $('#dept-form').validate({
            submitHandler: sysDept.saveDept
        });
    });
</script>
