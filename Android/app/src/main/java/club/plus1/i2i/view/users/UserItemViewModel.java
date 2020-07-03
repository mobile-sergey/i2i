package club.plus1.i2i.view.users;

import androidx.databinding.ObservableField;

import club.plus1.i2i.model.User;

public class UserItemViewModel {
    public ObservableField<User> user;

    public UserItemViewModel(){
        user = new ObservableField<>();
    }
}
