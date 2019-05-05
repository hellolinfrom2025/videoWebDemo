layui.use(['element'], function () {
    var element = layui.element;

    getVideoTag();
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
                        icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                        onclick: function () {
                            $.get('/graph/getVideoPlayTop.echart').done(function (res) {
                                if (res.success) {
                                    videoTotalTop.setOption({xAxis: res.data.xAxis, series: res.data.series});
                                }
                            });
                        }
                    },
                    myTool2: {
                        show: true,
                        title: '切换为下载量',
                        icon: 'image://http://echarts.baidu.com/images/favicon.png',
                        onclick: function () {
                            $.get('/graph/getVideoDownloadTop.echart').done(function (res) {
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
        $.get('/graph/getVideoPlayTop.echart').done(function (res) {
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