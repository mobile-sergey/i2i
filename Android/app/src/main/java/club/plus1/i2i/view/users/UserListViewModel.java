package club.plus1.i2i.view.users;

import androidx.databinding.ObservableArrayList;

import club.plus1.i2i.model.User;

public class UserListViewModel {
    ObservableArrayList<User> users;

    public UserListViewModel(){
        users = new ObservableArrayList<>();
    }
}
