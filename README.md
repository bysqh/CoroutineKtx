# CoroutineKtx [![](https://jitpack.io/v/SJJ-dot/CoroutineKtx.svg)](https://jitpack.io/#SJJ-dot/CoroutineKtx)

### kotlin 协程扩展。

支持在activity、fragment、dialog 启动协程并自动取消。

同一协程作用域声明key自动取消上一次关联的协程。


```groovy

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
	dependencies {
	        implementation 'com.github.SJJ-dot:CoroutineKtx:0.0.3'
	}
```
