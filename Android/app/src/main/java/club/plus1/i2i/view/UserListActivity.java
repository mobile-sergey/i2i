package club.plus1.i2i.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import club.plus1.i2i.R;
import club.plus1.i2i.presenter.UserListAdapter;

public class UserListActivity extends AppCompatActivity {

    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.user_list);
        adapter = new UserListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.readList();
    }
}