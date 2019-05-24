layui.use(['layer', 'laytpl'], function () {
    var $ = layui.jquery;

    /**
     * 获取URL参数
     * @return {string}
     */
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return decodeURI(r[2]);
        return null;
    }

    //selectBox
    var pageNo = GetQueryString('pageNo') ? GetQueryString('pageNo') : '';
    var type = GetQueryString('type') ? GetQueryString('type') : '';
    var country = GetQueryString('country') ? GetQueryString('country') : '';
    var year = GetQueryString('year') ? GetQueryString('year') : '';
    var order = GetQueryString('order') ? GetQueryString('order') : '';//最新，最热
    var searchParams = {
        pageNo: pageNo,
        type: type,
        country: country,
        year: year,
        order: order
    };
    //设置已点击筛选的颜色
    var list = $('.cate_row_r li');
    $.each(list, function () {
        var _params = $.extend({}, searchParams);
        if ($(this).text() === _params.type) {
            $(this).addClass("active");
        }
        if ($(this).text() === _params.country) {
            $(this).addClass("active");
        }
        if ($(this).text() === _params.year) {
            $(this).addClass("active");
        }
        if (_params.type===''){
            $('#all1').addClass("active");
        }
        if (_params.country===''){
            $('#all2').addClass("active");
        }
        if (_params.year===''){
            $('#all3').addClass("active");
        }
    });
    if (searchParams.order === '0') {
        $('.fornew').addClass("act");
    } else {
        $('.forhot').addClass("act");
    }

    //获取视频的分类信息
    function _search(params) {
        params = $.extend({}, searchParams, params);
        location.href = '/videoWebFront/index/category.html?pageNo=' + params.pageNo + '&type=' + params.type
            + '&country=' + params.country + '&year=' + params.year + '&order=' + params.order;
    }

    var total = parseInt($('#total').val());
    var pages = parseInt($('#pages').val());
    if (total !== 0) {
        $.jqPaginator('#pagination1', {
            totalPages: pages,
            currentPage: 1,
            prev: '<li class="prev"><a href="javascript:;">上页</a></li>',
            next: '<li class="next"><a href="javascript:;">下页</a></li>',
            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
            last: '<li class="last"><a href="javascript:;">尾页(' + pages + ')</a></li>',
            first: '<li class="first"><a href="javascript:;">首页</a></li>',
            onPageChange: function (num, type) {
                params = $.extend({}, searchParams, {pageNo: num});
                // location.href = '/videoWebFront/index/category.html?pageNo=' + params.pageNo + '&type=' + params.type
                //     + '&country=' + params.country + '&year=' + params.year;
                // +  '&order=' + params.order;
            }
        });
    } else {
        $('#pagination1').append('当前分类，未找到视频！');
    }
    window._search = _search;

});