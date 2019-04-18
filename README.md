# GeneralView
通用视图

## gradle使用：

一、Project下的build.gradle文件下添加

allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}

二、Module下的build.gradle文件下添加

dependencies {
          compile 'com.github.Lvluffy:GeneralView:1.0.0'
}

或者

dependencies {
          implementation 'com.github.Lvluffy:GeneralView:1.0.0'
}

## 录屏
![rozsv-t4ab0](https://user-images.githubusercontent.com/34730376/56335047-76c01300-61cd-11e9-9c91-0a3976cb8509.gif)
