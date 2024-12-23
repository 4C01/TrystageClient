package cn.trystage.event.impl.misc;

import cn.trystage.event.impl.Event;

public class EventKeyboard implements Event {

    private int k;

    public EventKeyboard(int k) {
        this.k = k;
    }

    public int getKey() {
        return this.k;
    }

}
