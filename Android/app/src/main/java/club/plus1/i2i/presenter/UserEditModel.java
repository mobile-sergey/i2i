package club.plus1.i2i.presenter;

import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class UserEditModel {

    public ObservableInt id;
    public ObservableField<String> name;
    public ObservableField<String> phone;
    public ObservableField<String> email;

    public UserEditModel(Intent intent){
        this.id = new ObservableInt();
        this.name = new ObservableField<>();
        this.phone = new ObservableField<>();
        this.email = new ObservableField<>();
        this.id.set(intent.getIntExtra("position", -1));
        if (this.id.get() >= 0) {
            this.name.set(intent.getStringExtra("name"));
            this.phone.set(intent.getStringExtra("phone"));
            this.email.set(intent.getStringExtra("email"));
         }
    }
}
