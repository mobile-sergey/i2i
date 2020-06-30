package club.plus1.i2i.router;

import android.content.Intent;
import android.view.View;

import club.plus1.i2i.iterator.App;
import club.plus1.i2i.entity.User;
import club.plus1.i2i.view.MessageListActivity;
import club.plus1.i2i.view.UserEditActivity;
import club.plus1.i2i.presenter.UserListModel;

public class UserRouter {

    public static void onAdd(View view) {
        Intent intent = new Intent(view.getContext(), UserEditActivity.class);
        view.getContext().startActivity(intent);
    }

    public static void onEdit(View view, User user, int position) {
        Intent intent = new Intent(view.getContext(), UserEditActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("name", user.name);
        intent.putExtra("email", user.email);
        intent.putExtra("phone", user.phone);
        view.getContext().startActivity(intent);
    }

    public static void onSave(final UserListModel viewModel){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int position = viewModel.id.get();
                String name = viewModel.name.get();
                String phone = viewModel.phone.get();
                String email = viewModel.email.get();
                if (position >= 0) {
                    User user = new User(position, name, phone, email);
                    App.app.db.userDao().update(user);
                } else {
                    User user = new User(App.app.db.userDao().count(), name, phone, email);
                    App.app.db.userDao().create(user);
                }
            }
        });
        thread.start();
    }

    public static void onChat(View view){
        Intent intent = new Intent(view.getContext(), MessageListActivity.class);
        view.getContext().startActivity(intent);
    }
}
