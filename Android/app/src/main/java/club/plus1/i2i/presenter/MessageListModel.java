package club.plus1.i2i.presenter;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import club.plus1.i2i.entity.Message;
import club.plus1.i2i.entity.User;

public class MessageListModel {
    ObservableField<User> user;
    ObservableArrayList<Message> messages;

    public MessageListModel(){
        user = new ObservableField<>();
        messages = new ObservableArrayList<>();
    }
}
