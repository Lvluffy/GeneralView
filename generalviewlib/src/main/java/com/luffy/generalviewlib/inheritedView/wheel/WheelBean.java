package com.luffy.generalviewlib.inheritedView.wheel;

import java.io.Serializable;

/**
 * Created by lvlufei on 2019/6/27
 *
 * @name 转动控件-实体类
 * @desc
 */
public class WheelBean implements Serializable {

    /**
     * name : 张三
     * id : 1
     */

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
