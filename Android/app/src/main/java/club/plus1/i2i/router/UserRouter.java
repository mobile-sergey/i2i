package club.plus1.i2i.router;

import android.content.Context;
import android.content.Intent;

import club.plus1.i2i.entity.User;
import club.plus1.i2i.presenter.App;
import club.plus1.i2i.presenter.UserEditModel;
import club.plus1.i2i.presenter.UserListAdapter;
import club.plus1.i2i.ui.MessageListActivity;
import club.plus1.i2i.ui.UserEditActivity;
import club.plus1.i2i.ui.UserListActivity;

public class UserRouter {

    // UserListActivity actions

    public static void onChat(Context context){
        Intent intent = new Intent(context, MessageListActivity.class);
        context.startActivity(intent);
    }

    public static void onAdd(Context context) {
        Intent intent = new Intent(context, UserEditActivity.class);
        context.startActivity(intent);
    }

    public static void onEdit(Context context, User user) {
        Intent intent = new Intent(context, UserEditActivity.class);
        intent.putExtra("position", user.id);
        intent.putExtra("name", user.name);
        intent.putExtra("email", user.email);
        intent.putExtra("phone", user.phone);
        context.startActivity(intent);
    }

    public static void onDelete(Context context, final User user) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                App.db.userDao().delete(user);
                UserListAdapter.list.remove(user);
                UserListAdapter.handler.sendEmptyMessage(0);
            }
        });
        thread.start();
    }

    // UserEditActivity actions

    public static void onRead(Context context){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                UserListAdapter.list = App.db.userDao().readAll();
                UserListAdapter.handler.sendEmptyMessage(0);
            }
        });
        thread.start();
    }

    public static void onSave(final Context context, final UserEditModel viewModel){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int position = viewModel.id.get();
                String name = viewModel.name.get();
                String phone = viewModel.phone.get();
                String email = viewModel.email.get();
                if (position >= 0) {
                    User user = new User(position, name, phone, email);
                    App.db.userDao().update(user);
                    UserListAdapter.handler.sendEmptyMessage(0);
                } else {
                    User user = new User(App.db.userDao().count(), name, phone, email);
                    App.db.userDao().create(user);
                    UserListAdapter.handler.sendEmptyMessage(0);
                }
                ((UserEditActivity)context).finish();
            }
        });
        thread.start();
    }
}