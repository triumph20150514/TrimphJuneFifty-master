package com.trimph.toprand.trimphrxandroid.trimph.component;

import com.trimph.toprand.trimphrxandroid.trimph.TrimphApplication;
import com.trimph.toprand.trimphrxandroid.trimph.module.AipModuleTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.module.MainModuleTrimph;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * 链接 inject 与 module 的桥梁
 * 容器
 * Created by tao on 2016/8/4.
 */
@Singleton
@Component(modules = {MainModuleTrimph.class, AipModuleTrimph.class})
public interface PictureComponentTrimph extends ComponentTrimph {

    class Instantis {

        public Instantis() {
        }

        public static PictureComponentTrimph getConponent(TrimphApplication trimphApplication) {
            return DaggerPictureComponentTrimph.builder()
                    .mainModuleTrimph(new MainModuleTrimph(trimphApplication))
                    .aipModuleTrimph(new AipModuleTrimph())
                    .build();
        }
    }


}
