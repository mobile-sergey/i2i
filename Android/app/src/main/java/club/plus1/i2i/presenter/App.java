package club.plus1.i2i.presenter;

import android.app.Application;

import androidx.room.Room;

import club.plus1.i2i.db.DB;
import club.plus1.i2i.entity.User;

public class App extends Application {

    public static App app;
    public static DB db;
    public User i = new User();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        db = Room.databaseBuilder(this, DB.class, "db")
                .fallbackToDestructiveMigration().build();
    }
}
