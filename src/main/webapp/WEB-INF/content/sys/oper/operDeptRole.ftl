<#include "/common/option.ftl"/>

<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="blue bigger">角色授权</h4>
        </div>

        <div class="modal-body">

            <form id="oper-dept-role-form" method="post" class="form-inline">
                <input type="hidden" name="deptRole.operid" id="oper-id"/>

                <div class="form-group col-xs-5">

                    <label for="deptname">部门</label>

                    <input type="text" class="form-control" id="deptname" name="name"
                           onclick="sysOper.selectDept()" readonly>
                </div>

                <div class="form-group col-xs-4">
                    <label>角色</label>

                    <select class="form-control" name="deptRole.roleid">
                    <@option gcode="SYS_ROLE"/>
                    </select>
                </div>

                <button type="button" class="btn btn-info btn-sm" onclick="sysOper.saveOperDeptRole()">
                    <i class="ace-icon fa fa-plus"></i>添 加
                </button>

            </form>

            <div class="space-8"></div>

            <div id="oper-dept-role-list">
            <#include "operDeptRoleList.ftl" />
            </div>
        </div>

        <div class="modal-footer">
            <button class="btn btn-sm hidden" data-dismiss="modal" type="button">
                <i class="ace-icon fa fa-check"></i> 确 定
            </button>
        </div>
    </div>
</div>