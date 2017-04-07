function Tree() {

    var self = this;

    /**
     * 初始化菜单树
     */
    this.initTree = function (deptid) {
        var url = ctx + '/component/tree/dept';
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

            $('#scroll-tree').append($('#modal-tree').tree('destroy'));

            $('#modal-tree').ace_tree({
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

            $('#modal-tree').on('selected.fu.tree', function (e, data) {
                dept = data.target;

                //self.showInput(dept.id, dept.parent ? dept.parent.id : '');
            });

            //$('#scroll-tree').ace_scroll();

            $('#tree-modal').modal();
        });
    };

}

var tree = new Tree();
