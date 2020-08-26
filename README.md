# Azure Search Client

It's just a tiny, simple client for Azure Search.

## Gradle
```groovy
    allprojects {
        repositories {
			maven { url 'https://jitpack.io' }
        }
    }

    dependencies {
        implementation 'com.github.rzcastilho:azure-search-client:Tag'
    }
```

## Maven
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependency>
        <groupId>com.github.rzcastilho</groupId>
        <artifactId>azure-search-client</artifactId>
        <version>Tag</version>
    </dependency>
```

