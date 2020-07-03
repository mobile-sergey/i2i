package club.plus1.i2i.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import club.plus1.i2i.R;

public class UserListItemView extends RecyclerView.ViewHolder {

    public TextView textName;
    public LinearLayout line;

    public UserListItemView(View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.textName);
        line = itemView.findViewById(R.id.line);
    }
}
