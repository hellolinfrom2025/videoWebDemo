layui.use(['element'], function () {
    var element = layui.element;

    getVideoTag();
    //视频标签和用户洗好选项卡图表
    element.on('tab(demo)', function (data) {
        switch (data.index) {
            case 0:
                getVideoTag();
                break;
            case 1:
                getVideoTotalTop();
                break;
            case 2:
                getVideoTag();
                break;
            case 3:
                getVideoTag();
                break;
        }
    });

    function getVideoTag() {
        // 基于准备好的dom，初始化echarts实例
        var videoTag = echarts.init(document.getElementById('videoTag'), 'macarons');
        videoTag.showLoading();
        $.get('/graph/getVideoTag.echart').done(function (res) {
            if (res.success) {
                videoTag.hideLoading();
                videoTag.setOption(res.data);
            }
        });
    }

    function getVideoTotalTop() {
        var videoTotalTop = echarts.init(document.getElementById('videoTotalTop'), 'macarons');
        videoTotalTop.showLoading();
        var topOption = {
            toolbox: {
                feature: {
                    myTool1: {
                        show: true,
                        title: '切换为播放量',
                        icon: 'image://https://static.easyicon.net/preview/122/1222643.gif',
                        onclick: function () {
                            $.get('/graph/getVideoTotalTop.echart?type=play').done(function (res) {
                                if (res.success) {
                                    videoTotalTop.setOption({xAxis: res.data.xAxis, series: res.data.series});
                                }
                            });
                        }
                    },
                    myTool2: {
                        show: true,
                        title: '切换为下载量',
                        icon: 'image://https://static.easyicon.net/preview/122/1223053.gif',
                        onclick: function () {
                            $.get('/graph/getVideoTotalTop.echart?type=download').done(function (res) {
                                if (res.success) {
                                    videoTotalTop.setOption({xAxis: res.data.xAxis, series: res.data.series});
                                }
                            });
                        }
                    },
                    myTool3: {
                        show: true,
                        title: '切换为点赞量',
                        icon: 'image://https://static.easyicon.net/preview/122/1225468.gif',
                        onclick: function () {
                            $.get('/graph/getVideoTotalTop.echart?type=good').done(function (res) {
                                if (res.success) {
                                    videoTotalTop.setOption({xAxis: res.data.xAxis, series: res.data.series});
                                }
                            });
                        }
                    },
                    myTool4: {
                        show: true,
                        title: '切换为收藏量',
                        icon: 'image://https://static.easyicon.net/preview/117/1176498.gif',
                        onclick: function () {
                            $.get('/graph/getVideoTotalTop.echart?type=collect').done(function (res) {
                                if (res.success) {
                                    videoTotalTop.setOption({xAxis: res.data.xAxis, series: res.data.series});
                                }
                            });
                        }
                    }
                }
            },
            tooltip: {}
        };
        videoTotalTop.showLoading();
        $.get('/graph/getVideoTotalTop.echart?type=play').done(function (res) {
            if (res.success) {
                videoTotalTop.hideLoading();
                videoTotalTop.setOption({
                    tooltip: topOption.tooltip,
                    toolbox: topOption.toolbox,
                    xAxis: res.data.xAxis,
                    yAxis: res.data.yAxis,
                    series: res.data.series
                });
            }
        });
    }

});