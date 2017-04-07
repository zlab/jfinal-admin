<title>菜单管理 &lsaquo; ${_configMap.APP_NAME!}</title>

<div class="row">
    <div class="col-sm-5 col-md-4">
        <div class="space-4"></div>
        <div class="widget-box widget-color-blue2">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">菜单管理</h4>
            </div>

            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div id="scroll-content" data-size="330" style="height: 330px; overflow: auto;">
                        <ul id="menu-tree" class="tree"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="menu-input" class="col-sm-7 col-md-8" style="display: none;">
    <#include "menuInput.ftl" />
    </div>
</div>

<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', [ctx + '/js/sys/SysMenu.js'], function () {
        $(function () {
            sysMenu.initTree();
            // $('#scroll-content').ace_scroll();
        });
    });
</script>