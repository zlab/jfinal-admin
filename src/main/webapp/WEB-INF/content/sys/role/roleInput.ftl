<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="blue bigger">角色信息</h4>
        </div>

        <div class="modal-body">
            <form id="role-form" method="POST" class="form-horizontal">
                <input type="hidden" name="role.roleid" id="id" value="${(role.id)!}"/>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="name">名称</label>

                    <div class="col-sm-8">
                        <input type="text" class="col-xs-12 required" name="role.name" id="name"
                               value="${(role.name)!}" ${readonly!}/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="memo">备注</label>

                    <div class="col-sm-8">
                        <input type="text" class="col-xs-12" name="role.memo" id="memo"
                               value="${(role.memo)!}" ${readonly!}/>
                    </div>
                </div>

            </form>
        </div>

        <div class="modal-footer">
            <button class="btn btn-sm" data-dismiss="modal" type="button">
                <i class="ace-icon fa fa-times"></i> 取 消
            </button>

            <button class="btn btn-sm btn-primary" type="button"
                    onclick="$('#role-form').submit()" ${disabled!}>
                <i class="ace-icon fa fa-check"></i> 保 存
            </button>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#role-form').validate({
            submitHandler: sysRole.saveRole
        });
    });
</script>