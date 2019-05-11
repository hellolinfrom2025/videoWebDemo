layui.use(['element', 'form', 'layer', 'carousel'], function () {
    var element = layui.element, form = layui.form, $ = layui.jquery,layer=layui.layer
        ,carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#test1'
        , width: '100%' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
    //视频相关选项卡图表
    element.on('tab(msg)', function (data) {
        switch (data.index) {
            case 0:
                getVideoTag();
                break;
            case 1:
                getVideoCount();
                break;
            case 2:
                getVideoTotalTop();
                break;
            case 3:
                getVideoCurrHot();
                break;
        }
    });
});