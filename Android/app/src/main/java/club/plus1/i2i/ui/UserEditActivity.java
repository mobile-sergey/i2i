package club.plus1.i2i.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import club.plus1.i2i.R;
import club.plus1.i2i.databinding.UserEditBinding;
import club.plus1.i2i.presenter.UserEditModel;

public class UserEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserEditBinding binding = DataBindingUtil.setContentView(this, R.layout.user_edit);
        binding.setViewModel(new UserEditModel(getIntent()));
    }
}