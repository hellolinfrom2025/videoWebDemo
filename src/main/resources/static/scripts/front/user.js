layui.use(['element', 'layer', 'laytpl'], function () {
    var element = layui.element ,layer = layui.layer, laytpl = layui.laytpl;

    //视频相关选项卡图表
    element.on('tab(video)', function (data) {
        switch (data.index) {
            case 0:
                getVideoTag();
                break;
            case 1:
                getVideoCount();
                break;
        }
    });

    function _getCollectVideoData() {
        $.ajax({
            type: "post",
            url: "/videoWebFront/user/getCollect.do",
            contentType: "application/json; charset=utf-8",
            // data: {},
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    $('#collect span').text(res.data.length);
                    var viewHtml = '';
                    $.each(res.data, function (i, d) {
                        viewHtml += _initCollectVideoTpl(d);
                    });
                    $('#collectVideo').html(viewHtml);
                } else {
                    return null;
                }
            }
        });
    }
     function _initCollectVideoTpl(video) {
     var tplHtml = '<li><a class="jie-title" href="/videoWebFront/play/play.html?vid={{d.id}}"' +
         ' target="_blank">{{d.title}}</a> <i>{{#var cDate = layui.util.timeAgo(new Date(d.time||""), true); }}{{cDate}}</i></li>';
        var string = laytpl(tplHtml).render({
            id: video.id,  title: video.videoTitle, time: video.time
        });
        return string;
    }

    _getCollectVideoData();

});