package club.plus1.i2i.presenter;

import androidx.databinding.ObservableField;

public class MessageItemViewModel {
    public ObservableField<String> message;

    public MessageItemViewModel(){
        message = new ObservableField<>();
    }
}
