layui.use(['table', 'element', 'form', 'layer', 'laytpl'], function () {
    var element = layui.element, form = layui.form, $ = layui.jquery, layer = layui.layer
        , table = layui.table, laytpl = layui.laytpl;
    var vid = $('#videoId').attr('value');
    var commentId = '';
    var toUserId = '';
    var REPLAY_DOM = $('#replay_com_box');

    //监听下载按钮
    $('#downloadVideo').on('click',function () {
        if (!isLogin()) {
            singInWindow();
            return;
        }
        $.get('/videoWebFront/play/download.do?vid='+vid,function (res) {
            if (res.success){
                layer.msg('模拟下载成功', {icon:1});
            }
        });
    });
    //监听收藏按钮
    $('#collectVideo').on('click',function () {
        if (!isLogin()) {
            singInWindow();
            return;
        }
        $.get('/videoWebFront/play/collect.do?vid='+vid,function (res) {
            if (res.success){
                layer.msg(res.msg, {icon:1});
            }
        });
    });

    var fun = {
        getSimilarVideoTableData: function () {
            var attr = $('#videoId').attr('value');
            $.getJSON('/videoWebFront/recommend/getSimilarVideo.do?vid=' + vid, function (res) {
                if (res.success) {
                    var viewHtml = '';
                    $.each(res.data, function (i, d) {
                        viewHtml += fun.initRecommendTpl(d);
                    });
                    $('#similarVideo').html(viewHtml);
                } else {
                    return null;
                }
            });
        },
        getHotVideoTableData: function () {
            $.getJSON('/videoWebFront/recommend/getHotVideo.do', function (res) {
                if (res.success) {
                    var viewHtml = '';
                    $.each(res.data, function (i, d) {
                        viewHtml += fun.initRecommendTpl(d);
                    });
                    $('#weekHot').html(viewHtml);
                } else {
                    return null;
                }
            });
        },
        initRecommendTpl: function (video) {
            var tplHtml = '<dl>' +
                '<dt><a href="/videoWebFront/play/play.html?vid={{d.id}}" target="_blank"><img src="{{d.cover}}"/></a></dt>' +
                '<dd><a href="/videoWebFront/play/play.html?vid={{d.id}}" target="_blank"><h3>{{d.title}}</h3>' +
                '<p>播放量：{{d.playCount}}</p>' +
                '</a></dd></dl>';
            var string = laytpl(tplHtml).render({
                id: video.id, url: video.url, cover: video.cover, title: video.title, playCount: video.playCount
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
    };

    //获取视频的评论和回复信息
    function getNoteComment(num) {
        $.get('/videoWebFront/comment/getComments.page?pageNo=' + num + '&pageSize=5&vid=' + vid, function (res) {
            if (res.success && res.data.total !== 0) {
                $('#pagination1').jqPaginator('option', {
                    currentPage: num
                });
                var tplData = { //数据
                    "comments": res.data
                };
                var laytpl = layui.laytpl, view = $('#commentsView');
                var getTpl = $('#commentsArea').html();
                laytpl(getTpl).render(tplData, function (html) {
                    view.html(html);
                });
            }
        });
    }

    function getNoteCommentPage() {
        $.get('/videoWebFront/comment/getComments.page?pageNo=1&pageSize=5&vid=' + vid, function (res) {
            var totalPages = res.data.pages;
            if (res.data.total !== 0) {
                $.jqPaginator('#pagination1', {
                    totalPages: totalPages,
                    currentPage: 1,
                    prev: '<li class="prev"><a href="javascript:;">上页</a></li>',
                    next: '<li class="next"><a href="javascript:;">下页</a></li>',
                    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                    last: '<li class="last"><a href="javascript:;">尾页(' + totalPages + ')</a></li>',
                    first: '<li class="first"><a href="javascript:;">首页</a></li>',
                    onPageChange: function (num) {
                        getNoteComment(num);
                    }
                });
                getNoteComment(1);
            }else {
                $('#pagination1').append('还没有评论，快来抢沙发！');
            }
        });
    }

    //添加回复块
    var replyName1 = '';

    function _addReplayBox(obj, id, replyName, uid) {
        if (!isLogin()) {
            singInWindow();
            return;
        }
        toUserId = uid;
        commentId = id;
        replyName1 = replyName;
        REPLAY_DOM.find('.replay_com_tx span').text(replyName);
        $(obj).parents('.note_comm_state').append(REPLAY_DOM);
        REPLAY_DOM.show();
        REPLAY_DOM.find('textarea').val('').focus();
        var width = REPLAY_DOM.find('.replay_com_tx span').width() + 40;
        REPLAY_DOM.find('textarea').css({'text-indent': width + 'px'});
    }

    // 取消回复
    function _cancelReplay() {
        $('#replay_com_box').hide();
    }

    //回复评论
    function _replayComment(obj) {
        if (!isLogin()) {
            singInWindow();
            return;
        }
        var data = {};
        var url = '/videoWebFront/comment/addReply.do';
        var val = $('#replay_com_box').find('textarea').val().trim();
        if (val === '') {
            layer.msg('回复不能为空', {skin: 'winning-class', offset: '80px'});
            return;
        }
        data.content = val;
        data.commentId = commentId;
        data.toUserId = toUserId;
        $(".loadingStyle").show();
        $.ajax({
            type: "post",
            url: url,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $(".loadingStyle").hide();
                    layer.msg('回复成功', {skin: 'winning-class', offset: '80px'});
                    $('#replay_com_box').hide();
                    $('#replay_com_box').find('textarea').val('');
                    //重新加载分页的评论数据
                    $('#pagination1').jqPaginator('destroy');
                    getNoteCommentPage();
                }
            }
        });
    }

    //提交评论
    $('#addComm').on('click', function () {
        if (!isLogin()) {
            singInWindow();
            return;
        }
        var content = $('#c_content').val();
        var videoId = $('#videoId').val();
        var url = '/videoWebFront/comment/addComment.do';
        if (content === '') {
            layer.msg('内容不能为空', {icon: 2});
        }
        $.ajax({
            url: url,
            data: {
                content: content,
                videoId: videoId
            },
            type: 'POST',
            success: function (obj) {
                if (obj.success) {
                    //重新加载分页的评论数据
                    getNoteCommentPage();
                    //清空当前评论内容
                    $('#c_content').val('');
                } else {
                    layer.msg(obj.msg, {icon: 2});
                }
            },
            error: function () {//失败处理函数
                layer.msg('请求异常！', {icon: 2});
            }
        });
        return false;
    });

    //点赞评论
    function _likeNote(obj, id) {
        if (!isLogin()) {
            singInWindow();
            return;
        }
        var url = 'likeComments.do?id=' + id;
        var type = 'get';
        if ($(obj).attr('data-liked') - 0) {
            type = 'delete';
        }
        $.ajax({
            type: type,
            url: url,
            success: function (data) {
                $(obj).attr('data-liked', (type === 'delete' ? '0' : '1'));
                var likeCount = $(obj).attr('data-likeCount');
                if (type === 'delete') {
                    $(obj).removeClass('act');
                    likeCount -= 1;
                    $(obj).find('.topic_like_btn_icon').attr('src', 'https://neets.cc/assets/imgTopics/icon_like_white.png');
                } else {
                    $(obj).addClass('act');
                    $(obj).find('.topic_like_btn_icon').attr('src', 'https://neets.cc/assets/imgTopics/icon_like.png');
                    likeCount -= 0;
                    likeCount += 1;
                }
                $(obj).attr('data-likeCount', likeCount);
                $('.like_count').text(likeCount);
                if (likeCount <= 0) {
                    $('.like_count_box').addClass('hide');
                } else {
                    $('.like_count_box').removeClass('hide');
                }
            },
        });
    }

    // window._cancelComment = _cancelComment;
    // window._likeNoteComment = _likeNoteComment;
    // window._addComment = _addComment;
    window._addReplayBox = _addReplayBox;
    window._cancelReplay = _cancelReplay;
    window._replayComment = _replayComment;
    window._likeNote = _likeNote;

    fun.getSimilarVideoTableData();
    fun.getHotVideoTableData();
    fun.videoPlay();
    getNoteCommentPage();
});



