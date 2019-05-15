layui.use(['table', 'element', 'form', 'layer','laytpl'], function () {
    var element = layui.element, form = layui.form, $ = layui.jquery, layer = layui.layer
        , table = layui.table, laytpl = layui.laytpl;

    var fun = {
        getSimilarVideoTableData: function () {
            var attr = $('#videoId').attr('value');
            $.getJSON('/videoWebFront/play/getSimilarVideo.do?vid='+attr, function (res) {
                if (res.success) {
                    var viewHtml ='';
                    $.each( res.data, function(i, d){
                        viewHtml += fun.initRecommendTpl(d);
                    });
                    $('#similarVideo').html(viewHtml);
                } else {
                    return null;
                }
            });
        },
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
        videoPlay: function () {
            var player, curr = 0, vlist_1, vlist_2, vlist_3, vlist_4;
            player = new Aliplayer({
                    "id": "player-con",
                    "source": '',
                    "width": "100%",
                    "height": "500px",
                    "autoplay": true,
                    "isLive": false,
                    "rePlay": false,
                    "playsinline": true,
                    "preload": true,
                    "controlBarVisibility": "always",
                    "useH5Prism": true
                }, function (player) {
                    player._switchLevel = 0;
                }
            );

            function endedHandle() {
                var newUrl = "";
                curr++;
                player.loadByUrl(vlist_2[curr]);
            }
            player.on("ended", endedHandle);
            $.ajax({
                url: '/parseVideoUrl/getPptvUrl', //访问的链接
                contentType: "application/json;charset=utf-8",
                data: {url: $('#videoUrl').attr('value')},
                success: function (res) {  //成功的回调函数
                    if (res.success) {
                        $.each(res.data.urls, function (i, d) {
                            if (i === 0) {
                                vlist_1 = d.urlSegms;
                            } else if (i === 1) {
                                vlist_2 = d.urlSegms;
                            } else if (i === 2) {
                                vlist_3 = d.urlSegms;
                            } else if (i === 3) {
                                vlist_4 = d.urlSegms;
                            }
                        });
                        player.loadByUrl(vlist_2[0]);
                    }
                },
                error: function (e) {
                    layer.msg("视频解析失败:" + e, {icon: 5});
                }
            });
        },
        //获取视频的评论和回复信息
        noteComment:function () {
            $.get('/play/getComments',function (res) {
                if (res.success){
                    var tplData = { //数据
                        "comments":res.data,
                        "refItemId": "refItem" + index
                        , "refValTypeAddId": "refValTypeAdd" + index
                        , "inputFromAddId": "inputFromAdd" + index
                        , "inputToAddId": "inputToAdd" + index
                        , "delRefClickId": "delRefClick" + index
                        , "seqItemId": "seqItem" + index
                        , "delSeqClickId": "delSeqClick" + index
                    };
                    var laytpl = layui.laytpl, view = $('#commentsView');
                    var getTpl = $('#commentsArea').html();
                    laytpl(getTpl).render(tplData, function (html) {
                        view.append(html);
                        formRefresh();
                    });
                }

            });

        }
    };

    fun.getSimilarVideoTableData();
    fun.getHotVideoTableData();
    fun.videoPlay();
    fun.noteComment();

});