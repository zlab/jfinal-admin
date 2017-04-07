function SysDept() {

    var self = this;

    /**
     * 初始化菜单树
     */
    this.initTree = function (deptid) {
        var url = ctx + '/sys/dept/queryDeptNodeList/';
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
            var dept = deptid ? map[deptid] : root[0];
            do {
                dept.additionalParameters['item-selected'] = true;
                dept = dept.parent;
            } while (dept);

            $('#scroll-content').append($('#menu-tree').tree('destroy'));

            $('#dept-tree').ace_tree({
                'open-icon': 'ace-icon fa fa-folder-open',
                'close-icon': 'ace-icon fa fa-folder',
                'selectable': true,
                'selected-icon': null,
                'unselected-icon': null,
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

            $('#dept-tree').on('selected.fu.tree', function (e, data) {
                dept = data.target;

                self.showInput(dept.id, dept.parent ? dept.parent.id : '');
            });
        });
    };

    /**
     * 录入
     *
     * @param id
     */
    this.showInput = function (id, parentid) {
        var url = ctx + '/sys/dept/input/?parentid=' + parentid;
        if (id) {
            url += '&id=' + id;
        }

        $('#dept-input').load(url);
    };

    /**
     * 保存部门
     */
    this.saveDept = function () {
        var url = ctx + '/sys/dept/saveDept/';
        var data = $('#dept-form').collectData();
        $.postJSON(url, data, function (json) {
            $.success('保存成功');
        });
    };

    /**
     * 删除部门
     */
    this.deleteDept = function (id, name) {
        $.confirm('删除部门【' + name + '】，是否继续？', function (result) {
            if (result) {
                var url = ctx + '/sys/dept/deleteDept/?id=' + id;
                $.postJSON(url, {}, function (json) {
                    $.success('删除成功');
                });

            }
        });
    };


}

var sysDept = new SysDept();