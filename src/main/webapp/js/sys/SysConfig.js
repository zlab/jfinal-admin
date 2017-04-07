function SysConfig() {

    var self = this;

    /**
     * 初始化配置树
     */
    this.initTree = function () {
        var url = ctx + '/sys/config/queryConfigNodeList/';
        $.postJSON(url, {}, function (json) {

            var config = $('#config-tree').tree('selectedItems');
            config = config.length ? config[0] : null;

            var map = {};
            $.each(json, function (i, v) {
                map[v.id] = v;
                v.type = 'item';
                v.text = v.deptname + ' - ' + v.value;
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
                    parent.text = parent.memo;
                } else {
                    v.type = 'folder';
                    root.push(v);
                }
            });

            //config = config || v;
           // config.additionalParameters['item-selected'] = true;


            $('#scroll-content').append($('#config-tree').tree('destroy'));

            $('#config-tree').ace_tree({
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

            $('#config-tree').on('selected.fu.tree', function (e, data) {
                config = data.target;

                if (config.type == 'item') {
                    self.showInput(config.id, config.parent.id);
                }

            });
        });
    };

    /**
     * 录入
     */
    this.showInput = function (id, parentid) {
        var url = ctx + '/sys/config/input?parentid=' + parentid;
        if (id) {
            url += '&id=' + id;
        }
        $('#config-input').load(url);
    };

    /**
     * 保存配置
     */
    this.saveConfig = function () {
        var url = ctx + '/sys/config/saveConfig/';
        var data = $('#config-form').collectData();
        $.postJSON(url, data, function (config) {
            $.success('保存成功');

            if (!$('#configid').val()) {
                self.showInput(config.configid, config.parentid);
            }

            self.initTree();
        });
    };

    /**
     * 删除配置
     */
    this.deleteConfig = function (id) {
        var url = ctx + '/sys/config/deleteConfig/?id=' + id;
        $.confirm('删除配置，是否继续？', function (result) {
            if (result) {
                $.postJSON(url, {}, function () {
                    $.success('删除成功');

                    self.initTree();
                });
            }
        });
    };

}

var sysConfig = new SysConfig();