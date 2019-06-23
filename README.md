
#### 在线演示
- 演示地址：http://http://hellolin.me:8017/videoWebFront/index/index.html
- 账号密码：小林/123456，或自行注册登录



#### 项目说明
- videoWebDemot是一个基于SpringBoot2.0开发的，轻量级的Java视频网站案例项目
- 分为管理员后台和用户前台
![前台模块](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%89%8D%E5%8F%B0%E6%A8%A1%E5%9D%97.png)



#### 项目特点
- 代码简洁，注释丰富，上手容易，提供基础模块(用户管理，角色管理，菜单管理，代码生成等8个模块)，可以直接作为一个后台管理系统的脚手架
- 友好的代码结构及注释，便于阅读及二次开发
- 完善的代码生成机制，可在线生成entity、dao、sql代码，减少60%以上的开发任务
- 支持跨驱动多数据源,加强业务模块的扩展性
- 基于Shiro实现细粒度权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 基于Druid对多数据源进行监控
- 基于Redis缓存数据以及session共享（用于反向代理集群及SSO单点登录）
- 基于Swagger对API接口进行管理




#### 技术选型
- 核心框架：Spring Boot 2.0.5
- 安全框架：Apache Shiro 1.4.0
- 视图框架：Beetl 2.8.6
- 持久层框架：BeetlSQL 2.10.31
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



