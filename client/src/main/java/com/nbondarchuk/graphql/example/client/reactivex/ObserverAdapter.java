package com.nbondarchuk.graphql.example.client.reactivex;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

public class ObserverAdapter<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NotNull Disposable disposable) {
    }

    @Override
    public void onNext(@NotNull T t) {
    }

    @Override
    public void onError(@NotNull Throwable throwable) {
    }

    @Override
    public void onComplete() {
    }
}
