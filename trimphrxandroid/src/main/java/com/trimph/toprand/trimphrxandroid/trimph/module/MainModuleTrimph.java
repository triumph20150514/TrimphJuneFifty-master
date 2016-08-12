package com.trimph.toprand.trimphrxandroid.trimph.module;

import android.content.res.Resources;

import com.trimph.toprand.trimphrxandroid.trimph.TrimphApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tao on 2016/8/4.
 */

@Module
public class MainModuleTrimph {

    public TrimphApplication trimphApplication;

    public MainModuleTrimph(TrimphApplication trimphApplication) {
        this.trimphApplication = trimphApplication;
    }

    @Singleton
    @Provides
    public TrimphApplication getTrimphApplication(){
        return trimphApplication;
    }

    @Singleton
    @Provides
    public Resources getResource(){
        return trimphApplication.getResources();
    }


}
