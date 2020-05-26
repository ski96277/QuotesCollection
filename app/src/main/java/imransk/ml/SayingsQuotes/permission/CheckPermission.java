package imransk.ml.SayingsQuotes.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class CheckPermission {

    public static boolean is_Camera_And_StroageWrite_PermissionGranted(Activity context, int CAMERA_REQUEST) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {
                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(
                        context, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }
    public static boolean isStorageWritePermissionGranted(Activity context, int STROAGE_REQUEST) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {
                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(
                        context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STROAGE_REQUEST);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }
    public static boolean isLocationPermissionGranted(Activity context, int STROAGE_REQUEST) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {
                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(
                        context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,}, STROAGE_REQUEST);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }
}