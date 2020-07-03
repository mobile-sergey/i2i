package club.plus1.i2i.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import club.plus1.i2i.entity.User;

@Dao
public interface UserDao {

    @Insert
    void create(User user);

    @Query("SELECT MAX(id)+1 FROM users")
    int count();

    @Query("SELECT * FROM users")
    List<User> readAll();

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
