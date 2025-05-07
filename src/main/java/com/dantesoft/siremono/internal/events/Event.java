package com.dantesoft.siremono.internal.events;

public interface Event<T> {
 T getSource();
 long getTimestamp();
}
