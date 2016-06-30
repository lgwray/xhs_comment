#评论后台管理系统
###这是一个基于spring-boot1.3.1.RELEASE和mybatis3的评论后台管理系统。

- **IDE:** IntelliJ IDEA 15.0.1
- **Server:** Apache Tomcat 9.0.0.1M1
- **DATABASE:** Mysql 5.6.7
- **Author:** Steven Guo

##注意事项
###下载项目后用Gradle构建项目

```
cd spring-boot-base
gradle build
```

###解决.gitignore无效问题
```
git rm --cached filename
```

##Git开发规范
+ branch 分支规范：
> master ：主分支，线上部署代码
> 
> test   : 进入测试代码，只有开发完成了才允许合并到此分支
> 
> dev    : 开发分支，处于开发阶段分支
> 
> XXXXXX : 正处于开发的分支，此分支不需提交git
  
+ Tags 标签规范
> 不允许提交本地标签
> 
> 线上版本标签 格式 vN.n.n.yyyyMMddHHmm 例如：v1.0.0.201601201501

+ 代码编写提交规范
> 更新所有分支
> 
> 从dev 分支创建一个新的开发分支
> 
> 在新的分支进行开发，开发完成，更新dev分支，合并当前分支到dev分支，测试无误项目组长将dev合并到test
> 
> 测试拉取test进行测试