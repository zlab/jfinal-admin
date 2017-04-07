<#assign title = "选择部门 &lsaquo; ${_configMap.APP_NAME!}">
<#include "/common/window.ftl">
<@window "login-layout blur-login">

<script type="text/javascript" src="${ctx}/js/sys/Frame.js"></script>

<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="red"></span>
                            <span class="white" id="id-text2">开发测试系统</span>
                        </h1>
                        <h4 class="light-blue" id="id-company-text">&copy; zhanqi.net</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i> Please Enter Your
                                        Information
                                    </h4>

                                    <div class="space-6"></div>

                                    <div class="clearfix">
                                        <#list (loginInfo.deptList)! as dept>
                                            <a href="javascript:void(0);" class="btn btn-primary btn-block btn-white"
                                               onclick="frame.chgDept(${dept.id})">${dept.name}</a>
                                        </#list>
                                    </div>

                                    <div class="space-4"></div>

                                    <div class="space-4"></div>

                                    <div class="social-or-login center">
                                        <span class="bigger-110">Login Info</span>
                                    </div>

                                    <div class="space-6"></div>

                                    <div class="social-login center">
                                        <h5 class="green">${loginInfo.oper.name}（${loginInfo.oper.username}）</h5>
                                    </div>
                                </div>
                                <!-- /.widget-main -->

                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.login-box -->
                    </div>
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.main-content -->
</div>

</@window>