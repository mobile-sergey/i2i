package club.plus1.i2i.ui;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import club.plus1.i2i.presenter.UserListAdapter;
import club.plus1.i2i.router.UserRouter;
import club.plus1.i2i.ui.swipe.SwipeButton;

public class UserListSwipeView {
    public static List<SwipeButton> getRightSwipeButtons(final Context context) {
        List<SwipeButton> buttons = new ArrayList<>();
        buttons.add(new SwipeButton(100, "", android.R.drawable.ic_menu_close_clear_cancel, Color.parseColor("#FF3C30"),
                new SwipeButton.ClickListener() {
                    @Override
                    public void onClick(int pos) {
                        UserRouter.onDelete(context, UserListAdapter.list.get(pos));
                    }
                }
        ));

        return  buttons;
    }

    public static List<SwipeButton> getLeftSwipeButtons(final Context context) {
        List<SwipeButton> buttons = new ArrayList<>();
        buttons.add(new SwipeButton(300, "Изменить", 0, Color.BLUE,
                new SwipeButton.ClickListener() {
                    @Override
                    public void onClick(int pos) {
                        UserRouter.onEdit(context, UserListAdapter.list.get(pos));
                    }
                }
        ));
        return  buttons;
    }}
