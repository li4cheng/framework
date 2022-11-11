# 文档/学习资料

optaplanner
8.9.0/Final [官方文档地址](https://docs.optaplanner.org/8.9.0.Final/optaplanner-docs/html_single/index.html#searchSpaceSize)

optaplanner [官方示例地址](https://github.com/kiegroup/optaplanner)

optaplanner [知乎文章](https://www.zhihu.com/people/kentzhang-aps/posts)

# 各分支内容

optaplanner-chained 时间链模式使用范例，实现了工厂生产排期规划

optaplanner-granule 时间粒模式使用范例，实现了会议调度规划

optaplanner-schedule-granule 排产项目

# 启动

所需配置：java11及以上, nodejs v16.5.0, npm v7.19.1, maven v3.8.1 启动方式

1. mvn
2. Maven.Framework.Lifecycle下选择clean，然后选择compile，完成后debug启动

# 目录结构

[src/main/java/com/my/framework](src/main/java/com/my/framework)

        |..aop.logging
        ||....LoggingAspect // log日志aop
        |..config
        ||....CacheConfiguration // 数据实体管理
        ||....SecurityConfiguration // 接口请求权限配置
        ||.... ...
        |..customConfig
        ||....baseEnum // 
        ||....currentUser // userModel及@currentUser配置
        ||....error // 错误信息
        ||....json // Long 传参序列化
        ||....log // 数据库表及对应的log切面
        ||....queryBuilder // OptionalBooleanBuilder
        ||....querydsl // 数据库查询用
        ||....restTemplate // 调用第三方接口
        ||....socket // websocket接口
        ||....timeFormat // 全局时间格式化
        ||....tree //
        |..domain // 数据库实体
        |..repository // 数据库连接仓库
        |..security // 框架登录验证及权限相关
        |..service // 数据库实体对应的services
        |..utils // 部分常用方法
        |..web // 各接口方法所在类

[spring配置文件](src/main/resources/config/application-dev.yml) 包括数据库连接配置，端口配置等

[spring配置文件](src/main/resources/config/application-dev.yml) [包括数据库连接配置，端口配置等]

[maven包管理](pom.xml)





