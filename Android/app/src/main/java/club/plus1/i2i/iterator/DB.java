package club.plus1.i2i.iterator;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import club.plus1.i2i.entity.Message;
import club.plus1.i2i.entity.User;

@Database(entities = {Message.class, User.class}, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract MessageDao messageDao();
    public abstract UserDao userDao();
}
