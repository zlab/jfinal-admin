/**
 * Loading
 */
(function ($) {

    var loading_icon = 'fa fa-spin fa-spinner fa-3x orange';
    var loading_text = '';

    var overlay;

    $.mask = function () {
        overlay && overlay.remove();
        overlay = $('<div class="ajax-loading-overlay"><i class="ajax-loading-icon '
            + loading_icon + '"></i> ' + loading_text + '</div>');

        var contentArea = $('.page-content-area');
        if (contentArea.length) {
            contentArea.append(overlay);
        } else {
            $('body').append(overlay.addClass('ajax-overlay-body'));
        }

    };

    $.unmask = function () {
        overlay && overlay.remove();
    };

})(jQuery);

/**
 * Ajax
 */
(function ($) {

    // Ajax Event
    $(document).on('ajaxStart', function () {
        $.mask();
    });

    $(document).on('ajaxStop', function () {
        $.unmask();
    });

    $(document).on('ajaxError', function (e, jqXHR, s) {

        var msg = jqXHR.responseText || '网络故障，请稍候再试！';
        var text = msg.match(/<h1>(.*?)<\/h1>/);
        if (text && text.length == 2) {
            msg = text[1];
        }

        if (jqXHR.status == 500 || jqXHR.status == 0) {
            if (jqXHR.statusText == 'abort') {
                msg = '请求已被中断！'
            } else if (jqXHR.statusText == 'timeout') {
                msg = '请求超时，请稍后再试！';
            } else if (jqXHR.statusText == 'error') {
                msg = '无法连接服务器，请稍后再试！';
            } else if (jqXHR.statusText == 'Internal Server Error') {
                msg = '服务器内部错误！';
            } else {
                msg = '网络故障，请稍候再试！';
            }
        }

        $.error(msg, 3000);
    });

    /**
     * 全局设置
     */
    $.ajaxSetup({
        timeout: 10000,
        type: 'POST'
    });

    /**
     * ajax
     */
    function ajax(url, data, success, failure, complete) {
        return $.ajax({
            url: url,
            data: $.paramJSON(data) || {}
        }).done(function (json, statusText, jqXHR) {
            // $.fn.load
            if ($.isString(json)) {
                return;
            }

            success && success.call(this, json);
        }).fail(function (jqXHR, statusText) {
            failure && failure.apply(this, arguments);
        }).always(function (jqXHR, statusText) {
            complete && complete.apply(this, arguments);
        });
    }

    $.postJSON = function (url, data, success, failure, complete) {
        return ajax(url, data, success, failure, complete);
    };

    $.postHtml = function (url, data, success) {
        return ajax(url, data).done(function (html) {

            if ($.isString(html)) {
                success && success.apply(this, arguments);
            }
        });
    };

    $.fn.load = function (url, data, success) {
        var self = this;

        return ajax(url, data).done(function (result) {

            if ($.isString(result)) {
                self.html(result);

                success && success.apply(self);
            }
        });
    };
})(jQuery);