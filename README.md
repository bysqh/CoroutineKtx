# CoroutineKtx

### kotlin 协程扩展。

支持在activity、fragment、dialog 启动协程并自动取消。

同一协程作用域声明key自动取消上一次关联的协程。


```groovy
allprojects {
    repositories {
        ...
        maven {
            url "https://raw.githubusercontent.com/SJJ-dot/repo/master"
            //or url "https://gitee.com/SJJ-dot/repo/raw/master"
        }
    }
}

dependencies {
    //...
    implementation 'com.sjianjun:coroutine:0.0.2'

}

```
