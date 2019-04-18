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
![3ygy7-6l9oj](https://user-images.githubusercontent.com/34730376/56339719-f061fc00-61e1-11e9-82f2-b82a808a7960.gif)
