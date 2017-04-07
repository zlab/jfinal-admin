function SysMenu() {

    var self = this;

    /**
     * 初始化菜单树
     */
    this.initTree = function (menuid) {
        var url = ctx + '/sys/menu/queryMenuNodeList/';
        $.postJSON(url, {}, function (json) {

            var map = {};
            $.each(json, function (i, v) {
                map[v.id] = v;
                v.type = 'item';
                v.children = [];
                v.additionalParameters = v.additionalParameters || {};
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

            // 选中
            var menu = menuid ? map[menuid] : root[0];
            do {
                menu.additionalParameters['item-selected'] = true;
                menu = menu.parent;
            } while (menu);

            $('#scroll-content').append($('#menu-tree').tree('destroy'));

            $('#menu-tree').ace_tree({
                'open-icon': 'ace-icon fa fa-folder-open',
                'close-icon': 'ace-icon fa fa-folder',
                'selectable': true,
                'singleOpen': true,
                'selected-icon': null,
                'unselected-icon': null,
                // loadingHTML: '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>',
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

            $('#menu-tree').on('selected.fu.tree', function (e, data) {
                menu = data.target;

                $('#menuid').val(menu.menuid);
                $('#name').val(menu.name);
                $('#uri').val(menu.uri);
                $('#iconcls').val(menu.iconcls);
                $('#bizcode').val(menu.bizcode);
                $('#sort').val(menu.sort);

                $('#menu-input').show();
            });
        });
    };

    /**
     * 保存菜单
     */
    this.saveMenu = function () {
        var menuid = $('#menuid').val();
        if (!menuid) {
            $.warn('请选择一个菜单');
            return;
        }

        var url = ctx + '/sys/menu/saveMenu/';
        var data = $('#menu-form').collectData();
        $.postJSON(url, data, function () {
            $.success('保存成功');

            self.initTree(menuid);
        });
    };

}

var sysMenu = new SysMenu();