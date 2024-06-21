package com.snykta.starter.eventbus;

import org.springframework.context.ApplicationEvent;


public abstract class BaseEvent<T> extends ApplicationEvent {

    public BaseEvent(T source) {
        super(source);
    }

    public T getSourceModel() {
        return (T) this.source;
    }

}
