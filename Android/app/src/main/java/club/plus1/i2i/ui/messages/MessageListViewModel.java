package club.plus1.i2i.ui.messages;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import club.plus1.i2i.entity.Message;
import club.plus1.i2i.entity.User;

public class MessageListViewModel {
    ObservableField<User> user;
    ObservableArrayList<Message> messages;

    public MessageListViewModel(){
        user = new ObservableField<>();
        messages = new ObservableArrayList<>();
    }
}
