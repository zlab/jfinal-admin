<title>${title!}</title>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<link rel="icon" type="image/x-icon" href="${ctx}/favicon.ico"/>

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${ctx}/ace/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx}/ace/css/font-awesome.min.css"/>

<!-- text fonts -->
<link rel="stylesheet" href="${ctx}/ace/css/ace-fonts.min.css"/>

<!-- third styles -->
<link rel="stylesheet" href="${ctx}/ace/css/jquery.gritter.min.css"/>
<link rel="stylesheet" href="${ctx}/ace/css/bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" href="${ctx}/ace/css/chosen.min.css"/>
<link rel="stylesheet" href="${ctx}/ace/css/bootstrap-editable.min.css"/>

<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/ace/css/ace.min.css"/>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/ace/js/jquery.min.js"></script>

<script type="text/javascript">
    if ('ontouchstart' in document.documentElement)
        document.write("<script src='${ctx}/ace/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>

<!-- basic scripts -->
<script type="text/javascript" src="${ctx}/ace/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/ace.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/ace-extra.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/ace-elements.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/fuelux/fuelux.tree.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/bootbox.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/date-time/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/date-time/moment-zh-cn.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/date-time/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/ace/js/additional-methods.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript" src="${ctx}/js/common/form.js"></script>
<script type="text/javascript" src="${ctx}/js/common/ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/common/jquery.tag.js"></script>
<script type="text/javascript" src="${ctx}/js/common/jquery.util.js"></script>

<script type="text/javascript">
    var ctx = '${ctx}';
</script>

<script type="text/javascript" src="${ctx}/js/component/Tree.js"></script>

<style type="text/css">
    .ajax-loading-overlay {
        background-color: transparent;
    }

    .tree .tree-selected > .tree-item-name > .ace-icon.fa-times {
        background-color: #FAFAFA;
        color: #F9E8CE;
        border: 1px solid #CCC;
    }

    .tree .tree-item > .tree-item-name > .ace-icon.fa-check {
        background-color: #F9A021;
        border-color: #F9A021;
        color: #FFF;
    }

    .table td.padding-5 {
        padding: 5px 8px;
    }

    .table tr {
        height: 37px;
    }

    .page-info {
        height: 30px;
        line-height: 30px;
    }

    .pagination {
        float: right;
        margin: 0;
    }

    .popover {
        max-width: 100%;
    }

    .popover .arrow {
        display: none;
    }

    .popover .popover-content .list-item {
        margin-right: 5px;
        width: 100px
    }


</style>