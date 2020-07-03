package club.plus1.i2i.view.messages;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import club.plus1.i2i.model.Message;
import club.plus1.i2i.model.User;

public class MessageListViewModel {
    ObservableField<User> user;
    ObservableArrayList<Message> messages;

    public MessageListViewModel(){
        user = new ObservableField<>();
        messages = new ObservableArrayList<>();
    }
}
