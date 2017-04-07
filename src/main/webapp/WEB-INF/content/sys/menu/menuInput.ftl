<div class="page-header">
    <h1>修改菜单</h1>
</div>

<form id="menu-form" class="form-horizontal">
    <input type="hidden" name="menu.menuid" id="menuid" />

    <div class="form-group">
        <label class="col-sm-2 control-label" for="name">菜单名称</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="name" readonly/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" for="uri">菜单地址</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="uri" readonly/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" for="bizcode">菜单代码</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="bizcode" readonly/>
        </div>
    </div>

    <#--<div class="form-group">
        <label class="col-sm-2 control-label" for="perm">菜单权限</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="perm" readonly/>
        </div>
    </div>-->

    <div class="form-group">
        <label class="col-sm-2 control-label" for="iconcls">菜单图片</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" name="menu.iconcls" id="iconcls"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" for="sort">菜单排序</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" name="menu.sort" id="sort" />
        </div>
    </div>

    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-primary width-30" type="button" onclick="sysMenu.saveMenu()">
                <i class="ace-icon fa fa-check bigger-110"></i> 保 存
            </button>
        </div>
    </div>
</form>