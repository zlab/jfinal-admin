function SysOper() {

    var self = this;

    /**
     * 用户列表
     */
    this.queryOperList = function () {
        var url = ctx + '/sys/oper/list/';
        var data = $('#oper-list, #query-form').collectData(true);
        $('#oper-list').load(url, data);
    };

    /**
     * 新增/修改/查看
     */
    this.showInput = function (flag, id) {
        var url = ctx + '/sys/oper/input/?flag=' + flag;

        if (flag != 'add') {
            if (!id) {
                $.warn('请至少选择一条记录');
                return;
            }

            url += '&id=' + id;
        }

        $('#oper-input-modal').load(url, {}, function () {
            $(this).modal($.fn.modal.defaults);
        });
    };

    /**
     * 保存用户
     */
    this.saveOper = function () {
        var url = ctx + '/sys/oper/saveOper/';
        var data = $('#oper-form').collectData();
        $.postJSON(url, data, function (json) {
            $.success('保存成功');
            $('#oper-input-modal').modal('hide');
            self.queryOperList();
        });
    };

    /**
     * 删除用户
     */
    this.deleteOper = function (id, name) {
        $.confirm('删除用户【' + name + '】，是否继续？', function (result) {
            if (result) {
                var url = ctx + '/sys/oper/deleteOper/?id=' + id;
                $.postJSON(url, {}, function (json) {
                    $.success('删除成功');
                    self.queryOperList();
                });
            }
        });
    };

    /**
     * 重置密码
     */
    this.resetPassword = function (id, name) {
        $.confirm('重置用户【' + name + '】密码，是否继续？', function (result) {
            if (result) {
                var url = ctx + '/sys/oper/resetPassword/?id=' + id;
                $.postJSON(url, {}, function (json) {
                    $.success('密码已重置为：' + json.password);
                });
            }
        });
    };

    this.showOperDeptRole = function (id) {
        $('#oper-id').val(id);
        self.queryOperDeptRoleList(function () {
            $('#oper-dept-role-modal').modal($.fn.modal.defaults);
        });
    };

    this.selectDept = function () {

        tree.initTree();
    };

    this.saveOperDeptRole = function () {
        var url = ctx + '/sys/oper/saveOperDeptRole/';
        var data = $('#dept-role-form').collectData();
        $.postJSON(url, data, function (json) {
            $.success('添加成功');
            self.queryOperDeptRoleList();
        });
    };

    this.queryOperDeptRoleList = function (callback) {
        var url = ctx + '/sys/oper/queryOperDeptRoleList/?id=' + $('#oper-id').val();
        var data = $('#oper-dept-role-list').collectData(true);
        $('#oper-dept-role-list').load(url, data, callback);
    };

    this.deleteOperDeptRole = function (id) {
        $.confirm('删除部门角色，是否继续？', function (result) {
            if (result) {
                var url = ctx + '/sys/oper/deleteOperDeptRole/?id=' + id;
                $.postJSON(url, {}, function (json) {
                    $.success('删除成功');
                    self.queryOperDeptRoleList();
                });
            }
        });
    };
}

var sysOper = new SysOper();
