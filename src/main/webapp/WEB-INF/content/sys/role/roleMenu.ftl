<div class="modal-dialog modal-sm">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="blue bigger">角色授权</h4>
        </div>

        <div class="modal-body">
            <input type="hidden" id="role-id" name="roleId"/>

            <div id="scroll-content" data-size="333" style="height: 333px; overflow: auto;">
                <ul id="menu-tree" class="tree"></ul>
            </div>
        </div>

        <div class="modal-footer">
            <button class="btn btn-sm" data-dismiss="modal" type="button">
                <i class="ace-icon fa fa-times"></i> 取 消
            </button>

            <button class="btn btn-sm btn-primary" type="button" onclick="sysRole.saveRoleMenu()">
                <i class="ace-icon fa fa-check"></i> 保 存
            </button>
        </div>
    </div>
</div>