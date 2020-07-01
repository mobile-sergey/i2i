package club.plus1.i2i.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import club.plus1.i2i.entity.Message;

@Dao
public interface MessageDao {

    @Insert
    void create(Message message);

    @Query("SELECT * FROM messages")
    List<Message> readAll();

    @Update
    void update(Message message);

    @Delete
    void delete(Message message);
}
