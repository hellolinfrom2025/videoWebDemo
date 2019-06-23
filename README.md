
#### 在线演示
- 演示地址：http://hellolin.me:8017/videoWebFront/index/index.html
- 账号密码：小林/123456，或自行注册登录



#### 项目说明
- videoWebDemot是一个基于SpringBoot2.0开发的，轻量级的Java视频网站案例项目
- 分为管理员后台和用户前台
![前台模块](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%89%8D%E5%8F%B0%E6%A8%A1%E5%9D%97.png)
![后台模块](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%90%8E%E5%8F%B0-%E6%A8%A1%E5%9D%97.png)


#### 项目特点
- 代码简洁，上手容易，提供基础模块(用户管理，角色管理，菜单管理，代码生成等8个模块)，视频管理模块（分类管理，视频详情，评论管理，数据统计）
- 完善的代码生成机制，可在线生成entity、dao、sql代码，减少60%以上的开发任务
- 基于Shiro实现细粒度权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 基于Druid对多数据源进行监控
- 基于Swagger对API接口进行管理


#### 技术选型
![采用技术](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E4%B8%BB%E8%A6%81%E9%87%87%E7%94%A8%E7%9A%84%E6%8A%80%E6%9C%AF.png)



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
- 访问路径：
  - 后台 http://localhost:8017/login.html
  - 前台 http://localhost:8017/videoWebFront/index/index.html
- 账号密码：root/root



#### 完成进度
功能名称 | 进度
----|----
用户管理 | 100%
角色管理 | 100%
菜单管理 | 100%
权限管理 | 100%
代码生成 | 100%
API管理 | 100%
系统日志 | 100%
视频分类 | 80%
视频详情 | 90%
评论管理 | 80%
数据统计 | 90%
分类搜索 | 80%
视频播放 | 80%
推荐管理 | 50%
用户统计 | 80%
个人信息 | 10%



#### 效果图
- 前台首页
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%89%8D%E5%8F%B0-%E9%A6%96%E9%A1%B5.png)
- 前台视频播放
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%89%8D%E5%8F%B0-%E6%92%AD%E6%94%BE.png)
- 前台分类搜索页面
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%89%8D%E5%8F%B0-%E5%88%86%E7%B1%BB%E6%90%9C%E7%B4%A2.png)
- 前台数据统计页面
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%89%8D%E5%8F%B0-%E6%95%B0%E6%8D%AE%E7%BB%9F%E8%AE%A1.png)
- 前台个人信息页面
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%89%8D%E5%8F%B0-%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF%E8%AE%BE%E7%BD%AEpng.png)
- 后台首页
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%90%8E%E5%8F%B0-%E9%A6%96%E9%A1%B5.png)
- 后台限评论管理
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%90%8E%E5%8F%B0-%E8%AF%84%E8%AE%BA%E7%AE%A1%E7%90%86.png)
- 后台数据统计页面
![](https://raw.githubusercontent.com/hellolinfrom2025/img/master/img/%E5%90%8E%E5%8F%B0-%E8%A7%86%E9%A2%91%E6%95%B0%E6%8D%AE%E7%BB%9F%E8%AE%A1.png)




#### 在线文档
- [SpringBoot2.0官网文档](https://spring.io/projects/spring-boot "spring-boot2.0官网文档")
- [Beetl2.9官网中文文档](http://ibeetl.com/guide/#beetl "Beetl2.9官网中文文档")
- [BeetlSQL 2.10官网中文文档](http://ibeetl.com/guide/#beetlsql "BeetlSQL 2.10官网中文文档")
- [Swagger官网文档](https://swagger.io/irc/ "Swagger官网文档")
- [Layui2.x官网文档](https://www.layui.com/doc/ "Layui2.x官网文档")
- [ECharts3.0官网文档](http://echarts.baidu.com/api.html#echarts "ECharts3.0官网文档")


#### 许可证
- [Apache License2.0](LICENSE "Apache License2.0")



