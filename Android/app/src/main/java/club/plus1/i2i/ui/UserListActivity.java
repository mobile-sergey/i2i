package club.plus1.i2i.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import club.plus1.i2i.R;
import club.plus1.i2i.presenter.UserListAdapter;
import club.plus1.i2i.router.UserRouter;
import club.plus1.i2i.ui.swipe.SwipeCallback;

public class UserListActivity extends AppCompatActivity {

    public UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.user_list);

        // List
        adapter = new UserListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Swipe
        SwipeCallback swipeCallback = new SwipeCallback(this, recyclerView);
        swipeCallback.rightButtons = UserListSwipeView.getRightSwipeButtons(this);
        swipeCallback.leftButtons = UserListSwipeView.getLeftSwipeButtons(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserRouter.onRead(this);
    }
}