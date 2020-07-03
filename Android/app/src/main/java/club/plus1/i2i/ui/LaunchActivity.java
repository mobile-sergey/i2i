package club.plus1.i2i.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import club.plus1.i2i.R;
import club.plus1.i2i.databinding.LaunchBinding;
import club.plus1.i2i.presenter.LaunchModel;
import club.plus1.i2i.router.LaunchRouter;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LaunchBinding binding = DataBindingUtil.setContentView(this, R.layout.launch);
        binding.setViewModel(new LaunchModel(this));
        LaunchRouter.onLoad(this);
    }
}