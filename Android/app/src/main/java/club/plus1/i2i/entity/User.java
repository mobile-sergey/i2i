package club.plus1.i2i.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    public int id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String phone;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public String password;

    public User(){

    }

    @Ignore
    public User(int id, String name, String phone, String email){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
