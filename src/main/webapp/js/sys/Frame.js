function Frame() {

    var self = this;

    /**
     * 登录验证
     */
    this.login = function () {
        var url = ctx + '/sys/frame/login';
        var data = $('#login-form').collectData();
        $.postJSON(url, data, function (json) {
            location.href = ctx;
        });
    };

    /**
     * 选择部门
     */
    this.chgDept = function (deptid) {
        var url = ctx + '/sys/frame/chgDept?deptId=' + deptid;
        $.postJSON(url, {}, function (json) {
            location.href = ctx;
        });
    };

    /**
     * 修改密码
     */
    this.showChgPwd = function () {
        var url = ctx + '/sys/frame/chgPwd';
        $('#chg-pwd-modal').load(url, {}, function () {
            $(this).modal($.fn.modal.defaults);
        });
    };

    /**
     * 修改密码
     */
    this.chgPwd = function () {
        var url = ctx + '/sys/frame/chgPwd/service';
        var data = $('#chg-pwd-form').collectData();
        $.postJSON(url, data, function (json) {
            $.confirm('密码修改成功，是否重新登录？', function (result) {
                if (result) {
                    location.href = ctx + '/sys/frame/login';
                } else {
                    $('#chg-pwd-modal').modal('hide');
                }
            });
        });
    };
}

var frame = new Frame();
