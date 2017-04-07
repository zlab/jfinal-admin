$.fn.modal.defaults = {
    backdrop: 'static',
    keyboard: false
};

$.extend($.validator.defaults, {
    errorElement: 'div',
    errorClass: 'help-block',
    highlight: function (e) {
        $(e).closest('.form-group').removeClass('has-success').addClass('has-error');
    },

    success: function (e) {
        $(e).closest('.form-group').removeClass('has-error');// .addClass('has-success');
        $(e).remove();
    },

    errorPlacement: function (error, element) {
        if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
            var controls = element.closest('div[class*="col-"]');
            if (controls.find(':checkbox,:radio').length > 1)
                controls.append(error);
            else
                error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
        } else if (element.is('.select2')) {
            error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
        } else if (element.is('.chosen-select')) {
            error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
        } else
            error.insertAfter(element.parent());
    }
});

$.extend($.validator.messages, {
    required: "必选字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
    range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: $.validator.format("请输入一个最大为 {0} 的值"),
    min: $.validator.format("请输入一个最小为 {0} 的值")
});

bootbox.setDefaults({
    locale: 'zh_CN',
    animate: false
});

$.alert = function (message, callback) {
    return bootbox.alert({
        title: '操作提示',
        message: message,
        callback: callback
    });
};

$.confirm = function (message, callback) {
    return bootbox.confirm({
        title: '操作提示',
        message: message,
        callback: callback
    });
};

$.prompt = bootbox.prompt;

/**
 * info
 */
$.info = function (msg, delay) {
    $.gritter.add({
        text: msg,
        time: delay || 2000,
        class_name: 'gritter-info'
    });
};

/**
 * success
 */
$.success = function (msg, delay) {
    $.gritter.add({
        text: msg,
        time: delay || 2000,
        class_name: 'gritter-success'
    });
};

/**
 * warn
 */
$.warn = function (msg, delay) {
    $.gritter.add({
        text: msg,
        time: delay || 3000,
        class_name: 'gritter-warning'
    });
};

/**
 * error
 */
$.error = function (msg, delay) {
    $.gritter.add({
        text: msg || new String(msg),
        time: delay || 3000,
        class_name: 'gritter-error'
    });
};

/**
 * Table
 */
$.fn.table = function (num) {
    var table = $(this);

    table.addClass('table-striped table-bordered table-hover');

    num = Number(num || 8);
    var gap = num - table.find("tbody tr").length;
    if (gap > 0) {
        var tr = [];
        tr.push('<tr class="blank">');
        table.find("thead tr th").each(function () {
            tr.push('<td>&nbsp;</td>');
        });
        tr.push('</tr>');
        var row = $(tr.join(''));
        for (var i = 0; i < gap; i++) {
            table.find('tbody').append(row.clone());
        }
    }
}

/**
 * 分页
 */
$.fn.paging = function (func, btn) {
    var paging = $(this);

    var query = function (pageNo) {
        pageNo = pageNo || 1;
        paging.find('.page-no').val(pageNo);
        // func();
        eval(func + '()');
    };

    btn = btn || '.btn.btn-query';

    if ($(btn).length == 1) {
        $(btn).off('click.paging').on('click.paging', function (e) {
            query(1);
        });
    }

    paging.find('.pagination li a').prop('href', 'javascript:void(0);');

    paging.find('.pagination li:not(.disabled, .active)').on('click', function () {
        query($(this).data('page-no'));
    });
}

jQuery(function ($) {

    // 控件初始化
    // $.initControl();

    // 置空隐藏域
    $('input.hidden-name').on('change', function () {
        if (!$(this).val()) {
            $('#' + $(this).attr('hiddenid')).val('');
        }
    });

    // 键盘确定，提交表单
    $('form').keydown(function (e) {
        if (e.keyCode == 13) {
            var submit = $(this).find('.submit:first');
            if (submit.length) {
                submit.trigger('click');
            } else {
                $(this).find('.btn.cancel:first').trigger('click');
            }
            return false;
        }
    });
});