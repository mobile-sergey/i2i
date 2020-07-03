package club.plus1.i2i.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages")
public class Message {

    @PrimaryKey
    public int id;

    @ColumnInfo
    public int from;

    @ColumnInfo
    public int to;

    @ColumnInfo
    public String message;
}
