function SysRole() {

    var self = this;

    /**
     * 角色列表
     */
    this.queryRoleList = function () {
        var url = ctx + '/sys/role/list/';
        var data = $('#role-list, #query-form').collectData(true);
        $('#role-list').load(url, data);
    };

    /**
     * 新增/编辑
     */
    this.showInput = function (flag, id) {
        var url = ctx + '/sys/role/input/?flag=' + flag;

        if (id) {
            url += '&id=' + id;
        }

        $('#role-input-modal').load(url, {}, function () {
            $(this).modal($.fn.modal.defaults);
        });
    };

    /**
     * 保存角色
     */
    this.saveRole = function () {
        var url = ctx + '/sys/role/saveRole/';
        var data = $('#role-form').collectData();
        $.postJSON(url, data, function (json) {
            $.success('保存成功');
            $('#role-input-modal').modal('hide');
            self.queryRoleList();
        });
    };

    /**
     * 删除角色
     */
    this.deleteRole = function (id, name) {
        $.confirm('删除角色【' + name + '】，是否继续？', function (result) {
            if (result) {
                var url = ctx + '/sys/role/deleteRole/?id=' + id;
                $.postJSON(url, {}, function (json) {
                    $.success('删除成功');
                    self.queryRoleList();
                });
            }
        });
    };

    this.initTree = function () {
        var url = ctx + '/sys/role/queryMenuNodeList/?id=' + $('#role-id').val();
        $.postJSON(url, {}, function (json) {

            var map = {};
            $.each(json, function (i, v) {
                map[v.id] = v;
                v.type = 'item';
                v.children = [];
                v.additionalParameters = v.additionalParameters || {};
                v.additionalParameters['item-checked'] = v.owned;
            });

            var root = [];
            $.each(json, function (i, v) {
                if (v.parentid) {
                    var parent = map[v.parentid];
                    v.parent = parent;
                    parent.children.push(v);
                    parent.type = 'folder';
                } else {
                    v.type = 'folder';
                    root.push(v);
                }
            });

            var menu;

            $('#scroll-content').append($('#menu-tree').tree('destroy'));

            $('#menu-tree').ace_tree({
                'open-icon': 'ace-icon tree-minus',
                'close-icon': 'ace-icon tree-plus',
                'multiSelect': false,
                'selectable': true,
                'selected-icon': 'ace-icon fa fa-check',
                'unselected-icon': 'ace-icon fa fa-times',
                dataSource: function (parentData, callback) {
                    var data = [];

                    // root
                    if (!parentData.id) {
                        data = root;
                    } else {
                        data = map[parentData.id].children;
                    }

                    callback({
                        data: data
                    });
                }
            });

            $('#role-menu-modal').modal($.fn.modal.defaults);
        });
    };

    /**
     * 编辑角色菜单
     */
    this.showEditRoleMenu = function (id) {
        $('#role-id').val(id);

        self.initTree();
    };

    /**
     * 编辑角色菜单
     */
    this.saveRoleMenu = function () {
        var addIds = [], delIds = [];

        $('#menu-tree .tree-branch:not(.hide) .tree-item').each(function () {
            var menu = $(this).data();
            if ($(this).find('.icon-item').hasClass('fa-check')) {
                if (menu.owned) {
                    return;
                }
                addIds.push(menu.id);
            } else {
                if (!menu.owned) {
                    return;
                }
                delIds.push(menu.id);
            }
        });

        if (!addIds.length && !delIds.length) {
            $.info('无修改');
            return;
        }

        var data = {
            id: $('#role-id').val(),
            addIds: addIds,
            delIds: delIds
        };

        var url = ctx + '/sys/role/saveRoleMenu/';
        $.postJSON(url, data, function (json) {
            $.success('保存成功');
            $('#role-menu-modal').modal('hide');
        });
    };

}

var sysRole = new SysRole();