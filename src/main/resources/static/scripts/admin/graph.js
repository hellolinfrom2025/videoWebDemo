layui.use(['element'], function () {
    var element = layui.element;

    getVideoTag();
    //视频相关选项卡图表
    element.on('tab(video)', function (data) {
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
    getUserCount();
    //用户相关选项卡图表
    element.on('tab(user)', function (data) {
        switch (data.index) {
            case 0:
                getUserCount();
                break;
            case 1:
                getUserActive();
                break;
            // case 2:
            //     getVideoTotalTop();
            //     break;
        }
    });



    //视频标签统计图
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

    //视频数量日增加趋势统计图
    function getVideoCount() {
        // 基于准备好的dom，初始化echarts实例
        var videoCount = echarts.init(document.getElementById('videoCount'), 'macarons');
        var option ={
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {}
        };
        videoCount.showLoading();
        $.get('/graph/getVideoCountToEchart.echart').done(function (res) {
            if (res.success) {
                videoCount.hideLoading();
                videoCount.setOption({
                    tooltip:option.tooltip,
                    toolbox:option.toolbox,
                    legend:option.legend,
                    xAxis: res.data.xAxis,
                    yAxis: res.data.yAxis,
                    series: res.data.series
                });
            }
        });
    }

    //视频播放、下载、点赞、收藏历史总量的前六排名
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
                            getVideoTop('play')
                        }
                    },
                    myTool2: {
                        show: true,
                        title: '切换为下载量',
                        icon: 'image://https://static.easyicon.net/preview/122/1223053.gif',
                        onclick: function () {
                            getVideoTop('download')
                        }
                    },
                    myTool3: {
                        show: true,
                        title: '切换为点赞量',
                        icon: 'image://https://static.easyicon.net/preview/122/1225468.gif',
                        onclick: function () {
                            getVideoTop('good')
                        }
                    },
                    myTool4: {
                        show: true,
                        title: '切换为收藏量',
                        icon: 'image://https://static.easyicon.net/preview/117/1176498.gif',
                        onclick: function () {
                            getVideoTop('collect')
                        }
                    }
                }
            },
            tooltip: {}
        };

        function getVideoTop(type) {
            $.get('/graph/getVideoTotalTop.echart?type=' + type).done(function (res) {
                if (res.success) {
                    videoTotalTop.setOption({xAxis: res.data.xAxis, series: res.data.series});
                }
            });
        }

        //默认第一个图表
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

    //视频播放、下载、点赞、收藏的最近一周数量的前六排名
    function getVideoCurrHot() {
        var videoCurrHot = echarts.init(document.getElementById('videoCurrHot'), 'macarons');
        videoCurrHot.showLoading();
        var hotOption = {
            toolbox: {
                feature: {
                    myTool1: {
                        show: true,
                        title: '切换为播放量',
                        icon: 'image://https://static.easyicon.net/preview/122/1222643.gif',
                        onclick: function () {
                            getVideoHot(1)
                        }
                    },
                    myTool2: {
                        show: true,
                        title: '切换为下载量',
                        icon: 'image://https://static.easyicon.net/preview/122/1223053.gif',
                        onclick: function () {
                            getVideoHot(2)
                        }
                    },
                    myTool3: {
                        show: true,
                        title: '切换为点赞量',
                        icon: 'image://https://static.easyicon.net/preview/122/1225468.gif',
                        onclick: function () {
                            getVideoHot(3)
                        }
                    },
                    myTool4: {
                        show: true,
                        title: '切换为收藏量',
                        icon: 'image://https://static.easyicon.net/preview/117/1176498.gif',
                        onclick: function () {
                            getVideoHot(4)
                        }
                    }
                }
            },
            tooltip: {}
        };

        function getVideoHot(type) {
            $.get('/graph/getVideoCurrHot.echart?type=' + type).done(function (res) {
                if (res.success) {
                    videoCurrHot.setOption({xAxis: res.data.xAxis, series: res.data.series});
                }
            });
        }

        //默认第一个图表
        videoCurrHot.showLoading();
        $.get('/graph/getVideoCurrHot.echart?type=1').done(function (res) {
            if (res.success) {
                videoCurrHot.hideLoading();
                videoCurrHot.setOption({
                    tooltip: hotOption.tooltip,
                    toolbox: hotOption.toolbox,
                    xAxis: res.data.xAxis,
                    yAxis: res.data.yAxis,
                    series: res.data.series
                });
            }
        });
    }

    //视频数量日增加趋势统计图
    function getUserCount() {
        // 基于准备好的dom，初始化echarts实例
        var userCount = echarts.init(document.getElementById('userCount'), 'macarons');
        var option={
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {}
        };

        userCount.showLoading();
        $.get('/graph/getUserCountToEchart.echart').done(function (res) {
            if (res.success) {
                userCount.hideLoading();
                userCount.setOption({
                    tooltip: option.tooltip,
                    legend: option.legend,
                    toolbox: option.toolbox,
                    xAxis: res.data.xAxis,
                    yAxis: res.data.yAxis,
                    series: res.data.series
                });
            }
        });
    }

    //最近5天用户活跃趋势统计图
    function getUserActive() {
        // 基于准备好的dom，初始化echarts实例
        var videoCount = echarts.init(document.getElementById('userActive'), 'macarons');
        var option ={
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {}
        };
        videoCount.showLoading();
        $.get('/graph/getUserActiveToEchart.echart').done(function (res) {
            if (res.success) {
                videoCount.hideLoading();
                videoCount.setOption({
                    tooltip:option.tooltip,
                    toolbox:option.toolbox,
                    grid:option.grid,
                    legend:option.legend,
                    xAxis: res.data.xAxis,
                    yAxis: res.data.yAxis,
                    series: res.data.series
                });
            }
        });
    }

});