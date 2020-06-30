package club.plus1.i2i.presenter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import club.plus1.i2i.R;

public class UserItemModel extends RecyclerView.ViewHolder {

    TextView textName;
    LinearLayout line;

    public UserItemModel(View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.textName);
        line = itemView.findViewById(R.id.line);
    }
}
