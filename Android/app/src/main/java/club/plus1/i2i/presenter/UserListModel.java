package club.plus1.i2i.presenter;

import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class UserListModel {

    public ObservableInt id;
    public ObservableField<String> name;
    public ObservableField<String> phone;
    public ObservableField<String> email;

    public UserListModel(Intent intent){
        prepare();
        this.id.set(intent.getIntExtra("position", -1));
        if (this.id.get() >= 0) {
            this.name.set(intent.getStringExtra("name"));
            this.phone.set(intent.getStringExtra("phone"));
            this.email.set(intent.getStringExtra("email"));
         }
    }

    private void prepare(){
        this.id = new ObservableInt();
        this.name = new ObservableField<>();
        this.phone = new ObservableField<>();
        this.email = new ObservableField<>();
    }
}
