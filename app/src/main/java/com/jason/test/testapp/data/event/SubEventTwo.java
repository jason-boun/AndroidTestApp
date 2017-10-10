package com.jason.test.testapp.data.event;

/**
 * Created by JiaBo on 2017/10/10.
 */

public class SubEventTwo extends ParentEvent {

    String subName;

    public String getSubName() {
        return subName;
    }

    public SubEventTwo setSubName(String subName) {
        this.subName = subName;
        return this;
    }
}
