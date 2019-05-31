layui.use(['element'], function () {
    var element = layui.element;

    //用户喜好视频标签统计图
    function getUserVideoType() {
        // 基于准备好的dom，初始化echarts实例
        var videoTag = echarts.init(document.getElementById('videoTag'), 'macarons');
        var option = {
            title: {
                text: '喜好视频标签'
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                top:'middle',
            },
        };
        videoTag.showLoading();
        $.get('/videoWebFront/dataGraph/getUserVideoType.echart?operationType=1').done(function (res) {
            if (res.success) {
                videoTag.hideLoading();
                videoTag.setOption({
                    title: option.title,
                    tooltip: option.tooltip,
                    toolbox: option.toolbox,
                    legend: option.legend,
                    series:
                        [
                            {
                                name: '视频分类标签',
                                type: 'pie',
                                selectedMode: 'single',
                                radius: [0, '30%'],
                                label: {
                                    normal: {
                                        position: 'inner'
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: res.data.series[0].data
                            },
                            {
                                name: '视频分类标签',
                                type: 'pie',
                                radius: ['40%', '55%'],
                                label: {
                                    normal: {
                                        formatter: '{a|{a}}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                                        backgroundColor: '#eee',
                                        borderColor: '#aaa',
                                        borderWidth: 1,
                                        borderRadius: 4,
                                        rich: {
                                            a: {
                                                color: '#999',
                                                lineHeight: 22,
                                                align: 'center'
                                            },
                                            hr: {
                                                borderColor: '#aaa',
                                                width: '100%',
                                                borderWidth: 0.5,
                                                height: 0
                                            },
                                            b: {
                                                fontSize: 16,
                                                lineHeight: 33
                                            },
                                            per: {
                                                color: '#eee',
                                                backgroundColor: '#334455',
                                                padding: [2, 4],
                                                borderRadius: 2
                                            }
                                        }
                                    }
                                },
                                data:res.data.series[1].data
                            }
                        ]
                });
            }
        });
    }


    //获得用户播放量最多的前六数量给图表
    function getUserPlayCount() {
        var videoCurrHot = echarts.init(document.getElementById('videoPlayCount'), 'macarons');
        var hotOption = {
            title: {
                text: '视频历史播放量前六'
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            tooltip: {}
        };
        videoCurrHot.showLoading();
        $.get('/videoWebFront/dataGraph/getUserPlayCount.echart').done(function (res) {
            if (res.success) {
                videoCurrHot.hideLoading();
                videoCurrHot.setOption({
                    title: hotOption.title,
                    tooltip: hotOption.tooltip,
                    toolbox: hotOption.toolbox,
                    xAxis: res.data.xAxis,
                    yAxis: res.data.yAxis,
                    series: res.data.series
                });
            }
        });
    }


    //当前用户最近10天的活跃趋势统计图
    function getUserActive() {
        // 基于准备好的dom，初始化echarts实例
        var videoCount = echarts.init(document.getElementById('userActive'), 'macarons');
        var option = {
            title: {
                text: '最近10天活跃图（播放、下载、点赞、收藏）'
            },
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
        $.get('/videoWebFront/dataGraph/getOneUserActiveToEchart.echart').done(function (res) {
            if (res.success) {
                videoCount.hideLoading();
                videoCount.setOption({
                    title: option.title,
                    tooltip: option.tooltip,
                    toolbox: option.toolbox,
                    grid: option.grid,
                    legend: option.legend,
                    xAxis: res.data.xAxis,
                    yAxis: res.data.yAxis,
                    series: res.data.series
                });
            }
        });
    }

    getUserActive();
    getUserPlayCount();
    getUserVideoType();
});