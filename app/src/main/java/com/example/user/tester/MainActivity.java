package com.example.user.tester;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.bouncycastle.crypto.Mac;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);



        Observable
                .fromArray(1, 2, 3)
                .concatMap(integer -> Observable.just(integer).delay(5, TimeUnit.SECONDS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(integer -> setBadge(integer))
                .subscribe();

        Consumer<Boolean> consumer = new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d("tag", "bla 2 " + aBoolean);
            }
        };



        Observer<Boolean> observer = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean integer) {
                Log.d("TAG", "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG", "Complete ");
            }
        };





    }

    private Badge setBadge(Integer integer) {
        QBadgeView qBadgeView = new QBadgeView(this);
        qBadgeView.bindTarget(tv);
        qBadgeView.setBadgeGravity(Gravity.TOP|Gravity.END);
        qBadgeView.setGravityOffset(-5, true);
        qBadgeView.setBadgeNumber(integer);


        return qBadgeView;
    }
}

