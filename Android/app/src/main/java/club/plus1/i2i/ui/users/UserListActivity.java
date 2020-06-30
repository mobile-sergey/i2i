package club.plus1.i2i.ui.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import club.plus1.i2i.R;
import club.plus1.i2i.databinding.UserListBinding;
import club.plus1.i2i.entity.User;

public class UserListActivity extends AppCompatActivity {

    UserListBinding binding;
    RecyclerView recyclerView;
    UserAdapter adapter;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.user_list);
        binding.setViewModel(new UserListViewModel());

        list = new ArrayList<>();
        adapter = new UserAdapter(this, list);
        recyclerView = findViewById(R.id.users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}