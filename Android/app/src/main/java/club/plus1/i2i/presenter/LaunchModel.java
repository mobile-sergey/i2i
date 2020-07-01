package club.plus1.i2i.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import club.plus1.i2i.R;

public class LaunchModel {

    public int versionCode;
    public String versionName;

    public LaunchModel(Context context){
        PackageInfo info = getPackageInfo(context);
        this.versionName = info.versionName;
        this.versionCode = info.versionCode;
    }

    private PackageInfo getPackageInfo(Context context){
        PackageInfo info = null;
        try {
            PackageManager pm = context.getPackageManager();
            if (pm != null){
                info = pm.getPackageInfo(context.getPackageName(), 0);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, R.string.error_package_not_found, Toast.LENGTH_LONG).show();
        }
        if (info == null) {
            info = new PackageInfo();
            info.versionName = "0.0";
            info.versionCode = 0;
        }
        return info;
    }
}
