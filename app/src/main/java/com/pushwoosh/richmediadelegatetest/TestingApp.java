package com.pushwoosh.richmediadelegatetest;

import android.app.Application;

import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.richmedia.RichMedia;
import com.pushwoosh.richmedia.RichMediaManager;
import com.pushwoosh.richmedia.RichMediaPresentingDelegate;

public class TestingApp extends Application {

    public static TestingApp getTestingApp() {
        return testingApp;
    }

    private static TestingApp testingApp;

    public static boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public RichMedia get_richMedia() {
        return _richMedia;
    }

    private RichMedia _richMedia;

    public static void setIsLoggedIn(boolean isLoggedIn) {
        TestingApp.isLoggedIn = isLoggedIn;
    }

    private static boolean isLoggedIn;

    @Override
    public void onCreate(){
        super.onCreate();
        testingApp = this;

        RichMediaManager.setDelegate(new RichMediaPresentingDelegate() {
            @Override
            public boolean shouldPresent(RichMedia richMedia) {
                if (!testingApp.getIsLoggedIn()) {
                    _richMedia = richMedia;
                    return false;
                }
                return true;
            }

            @Override
            public void onPresent(RichMedia richMedia) {

            }

            @Override
            public void onError(RichMedia richMedia, PushwooshException e) {

            }

            @Override
            public void onClose(RichMedia richMedia) {

            }
        });
    }
}
