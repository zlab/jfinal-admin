<#assign title = "登陆 &lsaquo; ${_configMap.APP_NAME!}">
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
                            <span class="red hidden">Ace</span>
                            <span class="white">${_configMap.APP_NAME!}</span>
                        </h1>
                        <h4 class="light-blue hidden">&copy; ${_configMap.COMPANY_NAME!}</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i> Please Enter Your Information
                                    </h4>

                                    <div class="space-6"></div>

                                    <form id="login-form" action="" method="POST">
                                        <fieldset>
                                            <label class="block clearfix"> <span
                                                    class="block input-icon input-icon-right">
                                                    <input type="text" id="username" name="username"
                                                           class="form-control required"
                                                           placeholder="帐 号" data-msg-required="帐号不能为空">
                                                    <i class="ace-icon fa fa-user"></i>
                                                </span>
                                            </label> <label class="block clearfix"> <span
                                                class="block input-icon input-icon-right">
                                                    <input type="password" id="password" name="password"
                                                           class="form-control required"
                                                           placeholder="密 码" data-msg-required="密码不能为空">
                                                    <i class="ace-icon fa fa-lock"></i>
                                                </span>
                                        </label>

                                            <div class="space"></div>

                                            <div class="clearfix align-right">
                                                <div class="center msg pull-left"
                                                     style="height: 20px; overflow: hidden;"></div>

                                                <button class="width-35 btn btn-sm btn-primary submit"
                                                        type="button" onclick="$('#login-form').submit()">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110"> 登 陆 </span>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>

                                    <div class="space-6"></div>

                                    <div class="social-or-login center">
                                        <span class="bigger-110"> &copy; ${_configMap.COMPANY_NAME!} </span>
                                    </div>
                                </div>
                                <!-- /.widget-main -->
                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.login-box -->
                    </div>
                    <!-- /.position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.main-content -->
</div>


<script type="text/javascript">
    $(function () {
        $.extend($.validator.defaults, {
            errorElement: 'span',
            errorClass: 'help-block red'
        });

        $('#login-form').validate({
            errorLabelContainer: '.msg',
            submitHandler: function () {
                ace.cookie.set('login.username', $('#username').val());
                frame.login();
            }
        });

        $('#username').val(ace.cookie.get('login.username'));

    });
</script>

</@window>