package com.ez.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ez.event.EzFlow;
import com.ez.event.EzObservable;
import com.ez.event.internal.flow.Consumer;
import com.ez.event.internal.flow.Function;
import com.ez.event.internal.flow.Predicate;
import com.ez.event.internal.observable.ObserverEventEmitter;
import com.ez.event.internal.observable.ObservableSource;
import com.ez.event.internal.observable.Observer;
import com.ez.event.internal.thread.ThreadToken;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        EzObservable.create(new ObservableSource<String>() {
//            @Override
//            public void subscribe(ObserverEventEmitter<String> emitter) {
//                log("subscribe : " + Thread.currentThread().getName());
//                emitter.onNext("hello");
//                emitter.onError(null);
//                emitter.onComplete();
//            }
//        }).subscribeOn(ThreadToken.BACKGROUND).observeOn(ThreadToken.UI).subscribe(new Observer<String>() {
//            @Override
//            public void onNext(String value) {
//                log(value + " : " + Thread.currentThread().getName());
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                log("error : " + Thread.currentThread().getName());
//            }
//
//            @Override
//            public void onComplete() {
//                log("complete : " + Thread.currentThread().getName());
//            }
//        });

//        EzFlow.just("aaa", "bbb").map(new Function<String, String>() {
//            @Override
//            public String apply(String value) {
//                if (value.equals("aaa")) {
//                    return "ccc";
//                }
//                return value;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String value) {
//                log(value + " : " + Thread.currentThread().getName());
//            }
//        });

//        EzFlow.just("aaa,bbb").split(",").delay(2).filter(new Predicate<String>() {
//            @Override
//            public boolean apply(String value) {
//                if (value.equals("aaa")) {
//                    return true;
//                }
//                return false;
//            }
//        }).map(new Function<String, String>() {
//            @Override
//            public String apply(String value) {
//                if (value.equals("aaa")) {
//                    return "ccc";
//                }
//                return value;
//            }
//        }).consumeOn(ThreadToken.UI).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String value) {
//                log(value + " : " + Thread.currentThread().getName());
//            }
//        });

//        EzFlow.just("aaa", "bbb").filter(new Predicate<String>() {
//            @Override
//            public boolean apply(String value) {
//                if (value.equals("aaa")) {
//                    return true;
//                }
//                return false;
//            }
//        }).subscribe(new Consumer() {
//            @Override
//            public void accept(Object value) {
//                log(value + " : " + Thread.currentThread().getName());
//            }
//        });
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}
