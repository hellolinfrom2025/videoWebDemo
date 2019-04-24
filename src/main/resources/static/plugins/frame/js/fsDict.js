/**
 * @Description: 字典配置
 * @Copyright: 2017 wueasy.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.8.2
 * @License：MIT
 */
layui.fsDict = {
    //城市
    city: {
        formatType: "local",
        labelField: "name",
        valueField: "code",
        //静态数据
        data: [{"code": 0, "name": "北京", "style": "color:#F00;"},
            {"code": 1, "name": "上海"},
            {"code": 2, "name": "广州"},
            {"code": 3, "name": "深圳"},
            {"code": 4, "name": "杭州"}
        ]
    },
    //类型
    type: {
        formatType: "local",
        labelField: "name",
        valueField: "code",
        spaceMode: " ",//展示多个数据分隔符，默认,
        data: [{"code": "write", "name": "写作", "css": "layui-badge layui-bg-orange"},
            {"code": "read", "name": "阅读", "css": "layui-badge layui-bg-green"},
            {"code": "dai", "name": "发呆", "css": "layui-badge layui-bg-cyan"}]
    },
    //性别
    sex: {
        formatType: "local",
        labelField: "name",
        valueField: "code",
        spaceMode: "",//展示多个数据分隔符，默认,
        data: [{"code": "男", "name": "男"},
            {"code": "女", "name": "女"}]
    }
    ,
    //省份
    area1: {
        formatType: "server",
        loadUrl: "/fsbus/DEMO1006",
        method: "post",
        inputs: "parentid:0",
        labelField: "name",
        valueField: "id"
    },
    //城市
    area2: {
        formatType: "server",
        loadUrl: "/fsbus/DEMO1006",
        inputs: "parentid:",
        labelField: "name",
        valueField: "id"
    },
    //区
    area3: {
        formatType: "server",
        loadUrl: "/fsbus/DEMO1006",
        inputs: "parentid:,area1:#area2222222",
        labelField: "name",
        valueField: "id"
    },
    //角色（本地数据）
    roleLocal: {
        formatType: "local",
        labelField: "name",
        valueField: "code",
        spaceMode: " ",//展示多个数据分隔符，默认,
        data: [{"code": "1", "name": "超级用户", "css": "layui-badge layui-bg-orange"},
            {"code": "2", "name": "普通用户", "css": "layui-badge layui-bg-green"}]
    },
    //角色
    role: {
        formatType: "server",
        loadUrl: "/coreRole/all.json",
        method: "post",
        inputs: "id:1",
        labelField: "name",
        valueField: "id"
    },
    //菜单父级
    menu: {
        formatType: "server",
        loadUrl: "/coreMenu/all.json",
        method: "post",
        inputs: "id:1",
        labelField: "name",
        valueField: "id"
    },
    //数据库类型（本地数据）
    dbType: {
        formatType: "local",
        labelField: "code",
        valueField: "code",
        spaceMode: " ",//展示多个数据分隔符，默认,
        data: [{"code": "mysql", "name": "mysql数据库"},
            {"code": "oracle", "name": "oracle数据库"}]
    },
    //数据源名称（本地数据）
    dataSource: {
        formatType: "local",
        labelField: "code",
        valueField: "code",
        spaceMode: " ",//展示多个数据分隔符，默认,
        data: [{"code": "core", "name": "core"},
            {"code": "video", "name": "video"},
            {"code": "zframe", "name": "zframe"}]
    },
    //表名称（本地数据）
    tableName: {
        formatType: "local",
        labelField: "code",
        valueField: "code",
        spaceMode: " ",//展示多个数据分隔符，默认,
        data: [{"code": "core_button", "name": "测试"},
            {"code": "core_menu", "name": "测试"}]
    },
    //视频标签-类目
    category: {
        formatType: "server", //server 动态数据字典
        loadUrl: "/videoType/getTag.json", //异步加载数据的url地址
        method: "post",
        inputs: "id:2", //异步请求携带的参数
        labelField: "name", //显示数据的属性
        valueField: "id" //value对应的属性
    },
    //视频标签-地区
    area: {
        formatType: "server",
        loadUrl: "/videoType/getTag.json",
        method: "post",
        inputs: "id:3",
        labelField: "name",
        valueField: "id"
    },
    //视频标签-年代
    year: {
        formatType: "server", //server 动态数据字典
        loadUrl: "/videoType/getTag.json", //异步加载数据的url地址
        method: "post",
        inputs: "id:1", //异步请求携带的参数
        labelField: "name", //显示数据的属性
        valueField: "id" //value对应的属性
    },
    //类型
    videoTagValue: {
        formatType: "local",
        labelField: "name",
        valueField: "code",
        spaceMode: " ",//展示多个数据分隔符，默认,
        data: [{"code": "音乐", "name": "音乐", "css": "layui-badge layui-bg-orange"},
            {"code": "2018", "name": "2018", "css": "layui-badge layui-bg-green"},
            {"code": "dai", "name": "发呆", "css": "layui-badge layui-bg-cyan"}]
    },

};
