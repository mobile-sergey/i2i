package club.plus1.i2i.ui.users;

import androidx.databinding.ObservableField;

import club.plus1.i2i.entity.User;

public class UserItemViewModel {
    public ObservableField<User> user;

    public UserItemViewModel(){
        user = new ObservableField<>();
    }
}
