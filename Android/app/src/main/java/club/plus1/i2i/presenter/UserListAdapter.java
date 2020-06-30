package club.plus1.i2i.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import club.plus1.i2i.iterator.App;
import club.plus1.i2i.R;
import club.plus1.i2i.entity.User;
import club.plus1.i2i.router.UserRouter;

public class UserListAdapter extends RecyclerView.Adapter<UserItemModel> {

    Context context;
    List<User> list;
    Handler handler;
    UserListAdapter adapter;

    @SuppressLint("HandlerLeak")
    public UserListAdapter(Context context){
        this.context = context;
        this.list = new ArrayList<>();
        adapter = this;
        handler = new Handler(){
            public void handleMessage(@NotNull android.os.Message msg){
                adapter.list = list;
                adapter.notifyDataSetChanged();
            }
        };
    }

    public void readList(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list = App.app.db.userDao().readAll();
                handler.sendEmptyMessage(0);
            }
        });
        thread.start();
    }

    @NotNull
    @Override
    public UserItemModel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserItemModel(view);
    }

    @Override
    public void onBindViewHolder(@NotNull UserItemModel holder, final int position) {
        final User user = list.get(position);
        holder.textName.setText(user.name);
        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRouter.onEdit(v, user, position);
            }
        });
    }

    public void setList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
