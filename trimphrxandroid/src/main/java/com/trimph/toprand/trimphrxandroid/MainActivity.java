package com.trimph.toprand.trimphrxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureApiTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.TrimphApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;

/**
 * dagger简单实用
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    PictureApiTrimph pictureApiTrimph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        TrimphApplication.component().inject(this);

        TextView textView = (TextView) findViewById(R.id.tv);


        pictureApiTrimph.getPictureList(1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.newThread())
//            /*    .filter(new Func1<PictureBean, Boolean>() {
//                    @Override
//                    public Boolean call(PictureBean pictureBean) {
//                        Log.e("Trimph", "filter" + pictureBean.getTngou().size());
//                        return pictureBean.isStatus();
//                    }
//                })*/
              /*  .flatMap(new Func1<PictureBean, Observable<PictureBean>>() {
                    @Override
                    public Observable<PictureBean> call(PictureBean pictureBean) {
                        if (pictureBean.isStatus()) {
                            Log.e("Trimph", "flatMap" + pictureBean.getTngou().size());
                            return Observable.just(pictureBean);
                        } else {
                            return null;
                        }
                    }
                })*/
                .subscribe(new Subscriber<PictureBean>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Trimph", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Trimph", "onError");
                    }

                    @Override
                    public void onNext(PictureBean pictureBean) {
                        Log.e("Trimph", "filter onNext" + pictureBean.getTngou().size());
                        Log.e("Trimph", "onNext" + pictureBean.getTngou().get(0).getTitle());
                        textView.setText(pictureBean.getTngou().get(0).getTitle());
                    }
                });

    }
}
