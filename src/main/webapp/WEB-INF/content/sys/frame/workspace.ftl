<#assign title = "首页 &lsaquo; ${_configMap.APP_NAME!}">
<#include "/common/window.ftl">
<@window "no-skin">

<script type="text/javascript" src="${ctx}/js/sys/Frame.js"></script>

<div id="navbar" class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small><i class="fa fa-leaf"></i> ${_configMap.APP_NAME!}
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <span class="user-info"></span>
                    ${loginInfo.oper.username} <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li><a href="#">
                            <i class="ace-icon fa fa-user"></i> 个人中心
                        </a></li>

                        <li><a href="javascript:void(0);" onclick="frame.showChgPwd();">
                            <i class="ace-icon fa fa-key"></i> 修改密码
                        </a></li>

                        <li class="divider"></li>

                        <li><a href="#">
                            <i class="ace-icon fa fa-cog"></i> 设置
                        </a></li>

                        <li><a href="${ctx}/sys/frame/login">
                            <i class="ace-icon fa fa-power-off"></i> 退出
                        </a></li>
                    </ul>
                </li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <div id="sidebar" class="sidebar responsive sidebar-fixed">
        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <a href="${ctx}" class="btn btn-danger" target="_blank">
                    <i class="ace-icon fa fa-cogs"></i>
                </a>
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>
                <span class="btn btn-info"></span>
                <span class="btn btn-warning"></span>
                <span class="btn btn-danger"></span>
            </div>
        </div>

        <ul class="nav nav-list">
            <li><a data-url="/sys/frame/dashboard/" href="#/sys/frame/dashboard/">
                <i class="menu-icon fa fa-tachometer"></i>
                <span class="menu-text"> 首页 </span>
            </a> <b class="arrow"></b></li>

            <#list (loginInfo.menuList)! as menu>
                <#if (menu.children)?size == 0>
                    <li><a data-url="${menu.uri!}" href="#${menu.uri!}">
                        <i class="menu-icon fa fa-tachometer"></i>
                        <span class="menu-text"> ${menu.name} </span>
                    </a> <b class="arrow"></b></li>
                <#else>
                    <li><a href="javascript:void(0);" class="dropdown-toggle">
                        <i class="menu-icon fa fa-pencil-square-o"></i>
                        <span class="menu-text"> ${menu.name} </span>
                        <b class="arrow fa fa-angle-down"></b>
                    </a> <b class="arrow"></b>

                        <ul class="submenu">
                            <#list menu.children as child>
                                <li><a data-url="${child.uri!}" href="#${child.uri!}">
                                    <i class="menu-icon fa fa-caret-right"></i> ${child.name}
                                </a> <b class="arrow"></b></li>
                            </#list>
                        </ul>

                    </li>
                </#if>
            </#list>
        </ul>
        <!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <!-- #section:basics/content.breadcrumbs -->
        <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
            <ul class="breadcrumb">
                <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#/sys/frame/dashboard/">首页</a></li>
            </ul>
            <!-- /.breadcrumb -->

            <!-- /section:basics/content.searchbox -->
        </div>

        <!-- /section:basics/content.breadcrumbs -->
        <div class="page-content">
            <!-- /section:settings.box -->
            <div class="page-content-area">
                <!-- ajax content goes here -->
            </div>
            <!-- /.page-content-area -->
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
                <span class="bigger-120">
                    <span class="blue bolder"></span>
                    &copy; ${_configMap.COMPANY_NAME!} 2011-2015
                </span>
            </div>

            <!-- /section:basics/footer -->
        </div>
    </div>

    <!-- 回到顶部 -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<!-- 修改密码 -->
<div id="chg-pwd-modal" class="modal body-modal">
    <#--<#include "chgPwd.ftl"/>-->
</div>

<div id="tree-modal" class="modal body-modal">
    <#include "/common/tree.ftl"/>
</div>

<script type="text/javascript">
    $(function () {
        $('.page-content-area').ace_ajax({
            close_active: true,
            loading_overlay: 'hide_loading_overlay', // 隐藏，不显示loading
            max_load_wait: 10,
            default_url: '/sys/frame/dashboard/',
            content_url: function (hash) {
                return ctx + hash;
            }
        }).on('ajaxloadcomplete', function () {
            $('body>.modal').not('.body-modal').remove();
            $('.modal').appendTo('body');
        });
    });
</script>

</@window>
