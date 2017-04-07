function SysParam() {

    var self = this;

    /**
     * 初始化树
     */
    this.initTree = function () {
        var url = ctx + '/sys/param/queryParamNodeList/';
        $.postJSON(url, {}, function (json) {

            var map = {};
            $.each(json, function (i, v) {
                map[v.id] = v;
                v.type = 'item';
                v.text = v.mname + ' - ' + v.mcode;
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
                    parent.text = parent.mname;
                } else {
                    v.type = 'folder';
                    root.push(v);
                }
            });

            var param = $('#param-tree').tree('selectedItems');
            param = param.length ? param[0] : null;

            $('#scroll-content').append($('#param-tree').tree('destroy'));

            $('#param-tree').ace_tree({
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

            $('#param-tree').on('selected.fu.tree', function (e, data) {
                param = data.target;

            });
        });
    };
}

var sysParam = new SysParam();