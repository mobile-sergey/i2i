package club.plus1.i2i.ui.users;

import androidx.databinding.ObservableArrayList;

import club.plus1.i2i.entity.User;

public class UserListViewModel {
    ObservableArrayList<User> users;

    public UserListViewModel(){
        users = new ObservableArrayList<>();
    }
}
