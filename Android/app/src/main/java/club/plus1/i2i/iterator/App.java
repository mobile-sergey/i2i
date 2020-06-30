package club.plus1.i2i.iterator;

import android.app.Application;

import androidx.room.Room;

import club.plus1.i2i.entity.User;

public class App extends Application {

    public static App app;
    public User i = new User();
    public DB db;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        db = Room.databaseBuilder(this, DB.class, "db")
                .fallbackToDestructiveMigration().build();
    }
}
