package club.plus1.i2i.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MessageDao.class, UserDao.class}, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract MessageDao messageDao();
    public abstract UserDao userDao();
}
