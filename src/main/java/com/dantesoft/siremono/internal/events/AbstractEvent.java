package com.dantesoft.siremono.internal.events;

public abstract class AbstractEvent<T> implements Event<T> {
    private final T source;
    private final long timestamp;

    public AbstractEvent(T source) {
        this.source = source;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public T getSource() {
        return source;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
}