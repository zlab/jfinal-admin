<#include "/common/option.ftl"/>

<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="blue bigger">用户信息</h4>
        </div>

        <div class="modal-body">
            <form id="oper-form" method="POST" class="form-horizontal">
                <input type="hidden" name="oper.operid" value="${(oper.id)!}"/>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="username">帐号</label>

                    <div class="col-sm-8">
                        <input type="text" class="col-xs-12 required" name="oper.username" id="username"
                               value="${(oper.username)!}" ${readonly!}/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="name">姓名</label>

                    <div class="col-sm-8">
                        <input type="text" class="col-xs-12 required" name="oper.name" id="name"
                               value="${(oper.name)!}"  ${readonly!}/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="gender">性别</label>

                    <div class="col-sm-8">
                        <select class="form-control" name="oper.gender" id="gender"  ${disabled!}>
                        <@option gcode="SYS_GENDER" mcode=(oper.gender)!/>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="age">年龄</label>

                    <div class="col-sm-8">
                        <input type="text" class="col-xs-12" name="oper.age" id="age"
                               value="${(oper.age)!}"  ${readonly!}/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="phone">手机</label>

                    <div class="col-sm-8">
                        <input type="text" class="col-xs-12" name="oper.phone" id="phone"
                               value="${(oper.phone)!}"  ${readonly!}/>
                    </div>
                </div>
            </form>
        </div>

        <div class="modal-footer">
            <button class="btn btn-sm" data-dismiss="modal" type="button">
                <i class="ace-icon fa fa-times"></i> 取 消
            </button>

            <button class="btn btn-sm btn-primary" type="button"
                    onclick="$('#oper-form').submit()"  ${disabled!}>
                <i class="ace-icon fa fa-check"></i> 保 存
            </button>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#oper-form').validate({
            submitHandler: sysOper.saveOper
        });
    });
</script>