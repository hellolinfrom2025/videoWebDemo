layui.use(['table', 'element', 'form', 'layer'], function () {
    var element = layui.element, form = layui.form, $ = layui.jquery, layer = layui.layer
        , table = layui.table;

    var fun = {
        getSimilarVideoTableData: function () {
            var attr = $('#videoId').attr('value');
            $.getJSON('/videoWebFront/play/getSimilarVideo.do?vid='+attr, function (res) {
                if (res.success) {
                    fun.similarVideoTable(res.data);
                } else {
                    return null;
                }
            });
        },
        getHotVideoTableData: function () {
            $.getJSON('/videoWebFront/index/getHotVideo.do', function (res) {
                if (res.success) {
                    fun.hotVideoTable(res.data);
                } else {
                    return null;
                }
            });
        },
        similarVideoTable: function (data) {
            //执行渲染
            table.render({
                elem: '#similarvideo',
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
                            '<div><a href="/videoWebFront/play.html?vid={{ d.id}}">{{ d.title}}</a><p>播放量：{{ d.playCount}}</p></div>'
                    },
                    {field: 'playCount', hide: true}
                ]],
                page: false,
                skin: 'nob',
            });
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
                player.loadByUrl(vlist_3[curr]);
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
                        player.loadByUrl(vlist_3[0]);
                    }
                },
                error: function (e) {
                    layer.msg("视频解析失败:" + e, {icon: 5});
                }
            });
        }
    };

    fun.getSimilarVideoTableData();
    fun.getHotVideoTableData();
    fun.videoPlay();

});