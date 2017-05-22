package com.xiaoxin.fashionthing.utils.http;

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}