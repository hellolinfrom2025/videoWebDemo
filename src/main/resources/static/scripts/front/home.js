layui.use(['table', 'element', 'form', 'layer', 'carousel'], function () {
    var element = layui.element, form = layui.form, $ = layui.jquery, layer = layui.layer
        , table = layui.table, carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#test1'
        , width: '100%' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
    //网站信息选项卡图
    element.on('tab(msg)', function (data) {
        switch (data.index) {
            case 1:
                getVideoCount();
                break;
        }
    });

    var fun = {
        getHotVideoTableData: function () {
            $.getJSON('/videoWebFront/index/getHotVideo.do', function (res) {
                if (res.success) {
                    fun.hotVideoTable(res.data);
                } else {
                    return null;
                }
            });
        },
        getNewUploadTableData: function () {
            $.getJSON('/videoWebFront/index/getNewUploadVideo.do', function (res) {
                if (res.success) {
                    fun.newUploadVideoTable(res.data);
                } else {
                    return null;
                }
            });
        },
        hotVideoTable: function (data) {
            //执行渲染
            table.render({
                elem: '#hotvideo',
                data: data,
                cols: [[
                    {type: 'numbers', width: 40},
                    {
                        field: 'cover', width: 120, templet:
                            '<div><img src="{{ d.cover}}" width="45px" height="65px"></div>'
                    },
                    {
                        field: 'title', width: 260,
                        templet:
                            '<div><a href="/videoWebFront/play/play.html?vid={{ d.id}}">{{ d.title}}</a><p>播放量：{{ d.playCount}}</p></div>'
                    },
                    {field: 'playCount', hide: true}
                ]],
                page: false,
                skin: 'nob',
            });
        },
        newUploadVideoTable: function (data) {
            //执行渲染
            table.render({
                elem: '#newvideo',
                data: data,
                cols: [[
                    {type: 'numbers', width: 40},
                    {
                        field: 'cover', width: 120, templet:
                            '<div><img src="{{ d.cover}}" width="45px" height="65px"></div>'
                    },
                    {
                        field: 'title', width: 260,
                        templet:
                            '<div><a href="/videoWebFront/play/play.html?vid={{ d.id}}">{{ d.title}}</a><p>播放量：{{ d.playCount}}</p></div>'
                    },
                    {field: 'playCount', hide: true}
                ]],
                page: false,
                skin: 'nob',
            });
        },
    };
    fun.getHotVideoTableData();
    fun.getNewUploadTableData();

});