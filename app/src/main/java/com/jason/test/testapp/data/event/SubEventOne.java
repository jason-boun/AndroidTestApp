package com.jason.test.testapp.data.event;

/**
 * Created by JiaBo on 2017/10/10.
 */

public class SubEventOne extends ParentEvent {

    String subName;

    public String getSubName() {
        return subName;
    }

    public SubEventOne setSubName(String subName) {
        this.subName = subName;
        return this;
    }
}
