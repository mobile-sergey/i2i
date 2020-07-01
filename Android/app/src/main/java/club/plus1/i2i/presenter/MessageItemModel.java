package club.plus1.i2i.presenter;

import androidx.databinding.ObservableField;

public class MessageItemModel {
    public ObservableField<String> message;

    public MessageItemModel(){
        message = new ObservableField<>();
    }
}
