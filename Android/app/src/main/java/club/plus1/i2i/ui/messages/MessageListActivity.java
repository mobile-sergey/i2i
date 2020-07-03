package club.plus1.i2i.ui.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import club.plus1.i2i.R;
import club.plus1.i2i.databinding.MessageListBinding;
import club.plus1.i2i.entity.Message;

public class MessageListActivity extends AppCompatActivity {

    MessageListBinding binding;
    RecyclerView recyclerView;
    MessageAdapter adapter;
    List<Message> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.message_list);
        binding.setViewModel(new MessageListViewModel());

        list = new ArrayList<>();
        adapter = new MessageAdapter(this, list);
        recyclerView = findViewById(R.id.messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}