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

## 功能说明

一、自定义控件，三种实现方式分包

1-1，组合原生控件

1-2，自己绘制控件

1-3，继承原生控件

二、控件集合

2-1，标签控件

2-2，星级评分控件

2-3，播放中控件

2-4，通用翻滚控件

2-5，空布局

2-6，底部栏控件

2-7，文本观察者（1，邮箱；2，密码；3，昵称；4，表情）

## 录屏
![3ygy7-6l9oj](https://user-images.githubusercontent.com/34730376/56339719-f061fc00-61e1-11e9-82f2-b82a808a7960.gif)
