package club.plus1.i2i.router;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import club.plus1.i2i.ui.UserListActivity;

public class LaunchRouter {

    public static final int DELAY = 2000;

    public static void onLoad(final Context context){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, UserListActivity.class);
                context.startActivity(intent);
            }
        }, DELAY);
    }
}
