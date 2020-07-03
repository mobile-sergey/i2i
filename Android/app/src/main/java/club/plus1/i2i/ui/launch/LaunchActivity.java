package club.plus1.i2i.ui.launch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import club.plus1.i2i.R;
import club.plus1.i2i.databinding.LaunchBinding;
import club.plus1.i2i.ui.users.UserListActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LaunchBinding binding = DataBindingUtil.setContentView(this, R.layout.launch);
        binding.setViewModel(new LaunchViewModel());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
                startActivity(intent);
            }
        }, 2000);    }
}