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

import club.plus1.i2i.R;
import club.plus1.i2i.entity.User;
import club.plus1.i2i.router.UserRouter;
import club.plus1.i2i.ui.UserListItemView;

public class UserListAdapter extends RecyclerView.Adapter<UserListItemView> {

    public static List<User> list;
    public static Handler handler;
    private Context context;

    @SuppressLint("HandlerLeak")
    public UserListAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
        handler = new Handler(){
            public void handleMessage(@NotNull android.os.Message msg){
                notifyDataSetChanged();
            }
        };
    }

    @NotNull
    @Override
    public UserListItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserListItemView(view);
    }

    @Override
    public void onBindViewHolder(@NotNull UserListItemView holder, final int position) {
        final User user = list.get(position);
        holder.textName.setText(user.name);
        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRouter.onChat(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
