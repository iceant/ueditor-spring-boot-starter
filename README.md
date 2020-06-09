# ueditor-spring-boot-starter
Baidu UEditor integration for Spring Boot 2.x

## Import Maven Dependency

```xml
<dependencies>
    <dependency>
        <groupId>com.github.iceant</groupId>
        <artifactId>ueditor-spring-boot-starter</artifactId>
        <version>-SNAPSHOT</version>
    </dependency>
</dependencies>

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

## setup spring boot
```properties
## application.properties
spring.servlet.multipart.max-file-size=50MB
spring.ueditor.file-vault=/var/upload/
```

## Sample
```html
<!DOCTYPE HTML>
<html lang="en-US">

<head>
    <meta charset="UTF-8">
    <title>ueditor demo</title>
</head>

<body>
<!-- 加载编辑器的容器 -->
<script id="container" name="content" type="text/plain">
        这里写你的初始化内容
</script>
<!-- 配置文件 -->
<script type="text/javascript" src='/webjars/ueditor/ueditor.config.js'></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src='/webjars/ueditor/ueditor.all.js'></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container', {
        serverUrl:'/ueditor/controller'
    });
</script>
</body>

</html>
```
