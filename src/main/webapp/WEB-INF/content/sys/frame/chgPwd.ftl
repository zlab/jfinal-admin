<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="blue bigger">修改密码</h4>
        </div>

        <div class="modal-body">
            <form id="chg-pwd-form" method="POST" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="username">帐号</label>

                    <div class="col-sm-8">
                        <input type="text" class="col-xs-12" value="${loginInfo.oper.username}" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="oldPwd">原密码</label>

                    <div class="col-sm-8">
                        <input type="password" class="col-xs-12 required" name="oldPwd" id="oldPwd"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="newPwd">新密码</label>

                    <div class="col-sm-8">
                        <input type="password" class="col-xs-12 required" name="newPwd" id="newPwd"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="confirmPwd">确认密码</label>

                    <div class="col-sm-8">
                        <input type="password" class="col-xs-12 required" name="confirmPwd" id="confirmPwd"/>
                    </div>
                </div>
            </form>
        </div>

        <div class="modal-footer">
            <button class="btn btn-sm" data-dismiss="modal" type="button">
                <i class="ace-icon fa fa-times"></i> 取 消
            </button>

            <button class="btn btn-sm btn-primary" type="button"
                    onclick="$('#chg-pwd-form').submit()">
                <i class="ace-icon fa fa-check"></i> 保 存
            </button>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#chg-pwd-form').validate({
            submitHandler: frame.chgPwd
        });
    });
</script>