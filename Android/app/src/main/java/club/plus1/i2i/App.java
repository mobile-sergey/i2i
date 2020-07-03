package club.plus1.i2i;

import android.app.Application;

import club.plus1.i2i.model.User;

public class App extends Application {

    public static App app;
    public User i = new User();

    public App(){
        app = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
