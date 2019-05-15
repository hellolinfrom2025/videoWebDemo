layui.use(['table', 'element', 'form', 'layer', 'carousel','laytpl'], function () {
    var element = layui.element, form = layui.form, $ = layui.jquery, layer = layui.layer
        , table = layui.table, carousel = layui.carousel, laytpl = layui.laytpl;
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
                    var viewHtml ='';
                    $.each( res.data, function(i, d){
                        viewHtml += fun.initRecommendTpl(d);
                    });
                    $('#weekHot').html(viewHtml);
                } else {
                    return null;
                }
            });
        },
        getNewUploadTableData: function () {
            $.getJSON('/videoWebFront/index/getNewUploadVideo.do', function (res) {
                if (res.success) {
                    var viewHtml ='';
                    $.each( res.data, function(i, d){
                        viewHtml += fun.initRecommendTpl(d);
                    });
                    $('#newUpload').html(viewHtml);
                } else {
                    return null;
                }
            });
        },
        initRecommendTpl:function(video){
            var tplHtml = '<dl>' +
                '<dt><a href="/videoWebFront/play/play.html?vid={{d.id}}"><img src="{{d.cover}}"/></a></dt>' +
                '<dd><a href="/videoWebFront/play/play.html?vid={{d.id}}"><h3>{{d.title}}</h3>' +
                '<p>播放量：{{d.playCount}}</p>' +
                '</a></dd></dl>';
            var string =  laytpl(tplHtml).render({
                id: video.id,url:video.url,cover:video.cover,title:video.title,playCount:video.playCount
            });
            return string;
        },
    };
    fun.getHotVideoTableData();
    fun.getNewUploadTableData();

});