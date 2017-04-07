(function($) {

    /**
     * collectData
     */
    var rsubmittable = /^(?:input|select|textarea)/i;
    var rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i;
    var rcheckableType = /^(?:checkbox|radio)$/i;

    /**
     * 收集字段
     */
    $.fn.collectFields = function() {
        var els = this.map(function() {
            var elements = $.prop(this, 'elements');
            return elements ? $.makeArray(elements) : $(this).find('input, select, textarea').add(this).get();
        });

        els = els.filter(function() {
            var type = this.type;
            return this.name && rsubmittable.test(this.nodeName) && !rsubmitterTypes.test(type)
                    && (this.checked || !rcheckableType.test(type));
        });

        return els;
    };

    /**
     * 收集字段值
     */
    $.fn.collectData = function(filterEmpty) {
        var data = {};
        var els = $(this).collectFields();

        els.each(function() {
            var val = $(this).val();

            if (val == null) {
                return;
            }

            var datatype = $(this).attr('datatype');
            if (datatype == 'number') {
                if (($.trim(val) == '' || !$.isNumber(Number(val)))) {
                    return;
                }

                val = Number(val);
            }

            if (filterEmpty === true && $.trim(val) == '') {
                return;
            }

            // 没看懂
            val.replace && val.replace(/\r?\n/g, '\r\n');

            var names = this.name.split('.');
            var first = names.shift();

            // 非对象，则返回一个值，或数组
            if (names.length == 0) {
                var lastVal = data[first];

                if (lastVal) {
                    if (!$.isArray(lastVal)) {
                        data[first] = [lastVal];
                    }
                    data[first].push(val);
                } else {
                    data[first] = val;
                }

                return;
            }

            // 对象属性赋值
            data[first] = data[first] || {};
            var obj = data[first];

            var last = names.pop();

            for ( var i = 0, len = names.length; i < len; i++) {
                obj[names[i]] = $.isObject(obj[names[i]]) ? obj[names[i]] : {};
                obj = obj[names[i]];
            }

            var lastVal = obj[last];

            if (lastVal) {
                if (!$.isArray(lastVal)) {
                    obj[last] = [lastVal];
                }
                obj[last].push(val);
            } else {
                obj[last] = val;
            }

        });

        return data;
    };

    /**
     * 收集name=pks字段
     */
    $.fn.collectPks = function() {
        return $.makeArray($(this).collectData().pks);
    };

    /**
     * 重置查询字段
     */
    $.fn.resetFields = function() {
        var els = this.map(function() {
            var elements = $.prop(this, 'elements');
            return elements ? $.makeArray(elements) : $(this).find('input, select, textarea').add(this).get();
        });

        els = els.filter(function() {
            var type = this.type;
            return rsubmittable.test(this.nodeName) && !rsubmitterTypes.test(type)
                    && (this.checked || !rcheckableType.test(type)) && !$(this).is(':disabled');
        });

        els.val('');
    };

    /**
     * disabled
     */
    $.fn.disabled = function(flag) {
        flag = flag == undefined ? true : flag;

        $(this).each(function() {
            if ($(this).is('.ui-button')) {
                $(this).button('option', 'disabled', flag);
            } else {
                $(this).prop('disabled', flag);
            }
        });
    };

})(jQuery);