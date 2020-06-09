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


