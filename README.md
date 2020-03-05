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

1.2.3之前

dependencies {
    
    implementation 'com.github.Lvluffy:GeneralView:1.0.0'
          
}

1.2.4之后

dependencies {
    
    implementation 'com.github.Lvluffy.GeneralView:generalamounteditlib:1.2.4'
       
    implementation 'com.github.Lvluffy.GeneralView:generalbottombarlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generalbuttonlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generalemptylib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generalflipper:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generalratingbarlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generalspinnerlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generaltaglib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generaltextindicatorlib:1.2.4'
       
    implementation 'com.github.Lvluffy.GeneralView:generaltextspanlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generaltextwatcherlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:generalwheellib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:playingiconlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:radarscanlib:1.2.4'
    
    implementation 'com.github.Lvluffy.GeneralView:randomtextlib:1.2.4'
          
}

## 功能说明

# 自定义view，三种实现方式

1-1，组合原生控件

1-1-1，通用按钮

1-1-2，通用空布局

1-1-3，通用翻滚控件（消息垂直翻滚）

1-1-4，文本框指示器控件

1-2，自己绘制控件

1-2-1，底部栏控件

1-2-2，播放中控件

1-2-3，星级评分控件

1-2-4，标签控件

1-3，继承原生控件

1-3-1，金额输入框

1-3-2，文本跨度

1-3-3，文本观察者（1，邮箱；2，密码；3，昵称；4，表情）

1-3-4，转动控件

1-3-5，Spinner

## 录屏
![3ygy7-6l9oj](https://user-images.githubusercontent.com/34730376/56339719-f061fc00-61e1-11e9-82f2-b82a808a7960.gif)
