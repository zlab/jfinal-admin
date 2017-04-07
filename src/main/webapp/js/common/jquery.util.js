/**
 * Utils
 */
(function ($) {
    /**
     * isObject
     */
    $.isObject = function (obj) {
        return $.type(obj) === 'object';
    };

    /**
     * isString
     */
    $.isString = function (obj) {
        return $.type(obj) === 'string';
    };

    /**
     * isNumber
     */
    $.isNumber = function (obj) {
        return !isNaN(obj) && $.type(obj) === 'number';
    };

    /**
     * isFunction
     */
    $.isFunction = function (obj) {
        return $.type(obj) === 'function';
    };

    /**
     * StringUtils.isBlank
     *
     * @param String
     */
    $.isBlank = function (value) {
        return value == undefined || $.trim(value) == '';
    };

    $.isFalse = function (value) {
        return value == undefined || value == false || value == 'false';
    };

    $.parseCssUnit = function (value) {
        // parseInt();
        return value && Number(value.replace(/px|pt|em/, ''));
    };

    /**
     * paramJSON
     */
    $.paramJSON = function (obj) {
        if (!obj || !$.isObject(obj)) {
            return obj;
        }
        var params = [];

        var add = function (key, value) {
            if (value != undefined) {
                params[params.length] = encodeURIComponent(key) + '=' + encodeURIComponent(value);
            }
        };

        for (var key in obj) {
            buildParams(key, obj[key], add);
        }

        return params.join('&').replace(/%20/g, '+');
    };

    /**
     * private recycle
     */
    function buildParams(key, value, add) {
        if ($.isObject(value)) {
            for (var name in value) {
                buildParams(key + '.' + name, value[name], add);
            }
        } else if ($.isArray(value)) {
            $(value).each(function () {
                add(key, this);
            });
        } else {
            add(key, value);
        }
    }

    /**
     * deSerialize
     */
    $.deSerialize = function (str) {
        var ret = {};
        if (!str) {
            return ret;
        }

        str = str.replace(/^\?/, '');

        var parts = [];
        parts = str.split('?');
        if (parts.length > 1 && /^http/.test(parts[0])) {
            parts.shift();
            str = parts.join('?');
        }

        $.each(str.split('&'), function (i, str) {
            parts = str.split('=');
            if (parts.length == 2) {
                ret[parts[0]] = parts[1];
            }

            // if (parts[i]) {
            // param = parts[i].split(/=/);
            // name = param[0];
            // value = param[1];
            // property = params[name];
            // if (typeof property === 'undefined') {
            // params[name] = param[1];
            // } else if (!Object.prototype.toString.apply(property) ===
            // '[object Array]') {
            // params[name] = [ value, property ];
            // } else {
            // params[name] = [ value, property ];
            // }
            // }
        });

        return ret;
    };

    /**
     * 拼接URL
     */
    $.addUrlParam = function (uri, name, value) {

        value = value || '';

        if (uri.indexOf('?') > 0) {
            uri += '&';
        } else {
            uri += '?';
        }

        var json = {};

        if ($.isObject(name)) {
            json = name;
        } else {
            json[name] = value;
        }

        return uri + $.paramJSON(json);
    };

    $.replaceParam = function (name, value) {

        var search = location.search;

        if (search.indexOf(name) != -1) {
            search = search.replace(new RegExp(name + '=[\w_]+'), name + '=' + value);
        } else {
            search = search + (location.search ? '&' : '?') + name + '=' + value;
        }

        location.href = location.origin + location.pathname + search + location.hash;
    };

})(jQuery);
