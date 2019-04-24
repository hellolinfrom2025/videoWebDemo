![MintLeaf开发团队](http://39.106.153.65:8017/images/logo.png "MintLeaf开发团队")
# 薄荷叶快速开发平台



#### 项目初衷
- 个人兴趣爱好，整理自己所掌握的技术，并随着技术掌握程度，不定期更新优化
- 希望此实际项目，能给需要的朋友带来价值,有你们的支持和关注，我的动力将不会衰减，一步步的将项目完善开发下去，定期加入新技术与功能



#### 项目说明
- MintLeaf-Fast是一个基于SpringBoot2.0开发的，轻量级的，前后端分离的Java快速开发平台
- 开箱即用，节省开发时间，提升开发效率，能够快速开发项目并交付的接私活利器
- 支持MySQL、Oracle、SQL Server等主流数据库



#### 项目特点
- 代码简洁，注释丰富，上手容易，提供基础模块(用户管理，角色管理，菜单管理，代码生成等8个模块)，可以直接作为一个后台管理系统的脚手架
- 友好的代码结构及注释，便于阅读及二次开发
- 完善的代码生成机制，可在线生成entity、dao、sql代码，减少60%以上的开发任务
- 支持跨驱动多数据源,加强业务模块的扩展性
- 基于Shiro实现细粒度权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 基于Druid对多数据源进行监控
- 基于Redis缓存数据以及session共享（用于反向代理集群及SSO单点登录）
- 基于Swagger对API接口进行管理



#### 项目结构
```
MintLeaf-Fast
├─db  项目SQL语句
│
├─logs  输出日志
│
├─common 公共模块
│ 
├─config 配置信息类模块
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  ├─video 业务模块
│  └─core 核心模块
│ 
├─utils 工具类模块
│ 
├─vo 视图对象类模块
│ 
├─MintLeafApplication 项目启动类
│  
├──resources 
│  ├─sql SQL对应的md文件
│  ├─static 静态资源
│  ├─templates 系统页面
│  │    ├─modules 模块页面
│  │    ├─index.html 主页面
│  │    └─login.html 登陆页面
│  ├─application.properties 全局配置文件
│  ├─btsql-ext.properties BeetlSQL配置文件
│  └─logback.xml 日志配置文件
│

```



#### 技术选型
- 核心框架：Spring Boot 2.0.5
- 安全框架：Apache Shiro 1.4.0
- 视图框架：Beetl 2.8.6
- 持久层框架：BeetlSQL 2.10.31
- 缓存框架：Redis 2.0.2
- JSON框架：FastJson 1.2.7
- 验证码框架：Kaptcha 2.3.2
- 数据库连接池：Druid 1.0.18
- 日志管理：SLF4J、Logback
- API管理：Swagger 2.7.0
- 页面交互：Layui 2.4.3
- 数据可视化：ECharts 3.0



#### 软件需求
- JDK1.8
- MySQL5.5+
- Maven3.0+



#### 本地部署
- 通过git下载源码
- 创建数据库mintleaf_fast，数据库编码为UTF-8
- 执行db/mintleaf_fast.sql文件，初始化数据【按需导入表结构及数据】
- 修改application.properties文件，更新MySQL账号和密码


- IDEA、Eclipse运行Application.java，则可启动项目【mintleaf-fast】
- 访问路径：http://localhost:8017/index.html
- 账号密码：root/123456



#### 完成进度
功能名称 | 进度
----|----
用户管理 | 100%
角色管理 | 100%
菜单管理 | 100%
权限管理 | 100%
文件上传 | 100%
Druid监控 | 100%
代码生成 | 100%
API管理 | 100%
系统日志 | 100%
图片管理 | 100%
图标选择器 | 100%
树形数据表格 | 100%
树形下拉选择器 | 100%
组织管理 | 50%



#### 在线演示
- 演示地址：http://39.106.153.65:8017/index.html
- 账号密码：demo/demo
(因为前段时间总有人恶意删数据，所以演示目前只提供部分权限功能，想体验全部权限的请留言，私聊发送超级用户)



#### 效果图
- 登陆页面
![login.png](http://39.106.153.65:8088/demo/login.png "登陆页面")
- 主页面
![index.png](http://39.106.153.65:8088/demo/index.png "主页面")
- 用户管理页面
![user.png](http://39.106.153.65:8088/demo/user.png "用户管理页面")
- 角色管理页面
![role.png](http://39.106.153.65:8088/demo/role.png "角色管理页面")
- 权限管理页面
![permission.png](http://39.106.153.65:8088/demo/permission.png "权限管理页面")
- 菜单管理页面
![menu.png](http://39.106.153.65:8088/demo/menu.png "菜单管理页面")
- 子权限管理页面
![button.png](http://39.106.153.65:8088/demo/button.png "子权限管理页面")
- 图标选择器页面
![icon.png](http://39.106.153.65:8088/demo/icon.png "图标选择器页面")
- 文件上传页面
![upload.png](http://39.106.153.65:8088/demo/upload.png "文件上传页面")
- 数据库监控页面
![db.png](http://39.106.153.65:8088/demo/db.png "数据库监控页面")
- 代码生成页面
![gen.png](http://39.106.153.65:8088/demo/gen.png "代码生成页面")
- API管理页面
![api.png](http://39.106.153.65:8088/demo/api.png "API管理页面")
- 系统日志
![api.png](http://39.106.153.65:8088/demo/logger.png "API管理页面")



#### 在线文档
- [SpringBoot2.0官网文档](https://spring.io/projects/spring-boot "spring-boot2.0官网文档")
- [Beetl2.9官网中文文档](http://ibeetl.com/guide/#beetl "Beetl2.9官网中文文档")
- [BeetlSQL 2.10官网中文文档](http://ibeetl.com/guide/#beetlsql "BeetlSQL 2.10官网中文文档")
- [Swagger官网文档](https://swagger.io/irc/ "Swagger官网文档")
- [Layui2.x官网文档](https://www.layui.com/doc/ "Layui2.x官网文档")
- [ECharts3.0官网文档](http://echarts.baidu.com/api.html#echarts "ECharts3.0官网文档")



#### 许可证
- [Apache License2.0](LICENSE "Apache License2.0")



#### 捐赠作者
- 如果您觉得此项目对您有价值，请作者喝一杯咖啡吧

![pay.png](http://39.106.153.65:8017/images/demo/pay.png "支付")