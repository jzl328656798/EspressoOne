package two.example.shen.yue.espressoprojectone.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import two.example.shen.yue.espressoprojectone.utils.EasyCallBack;
import two.example.shen.yue.espressoprojectone.utils.EasypermissionsUtils;
import two.example.shen.yue.espressoprojectone.utils.PermissionUtils;

/**
 * Created by queen on 2018/3/29.
 */

public class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private EasyCallBack callBack;

    public void startActivity(Class mClass) {
        Intent intent = new Intent(this, mClass);
        startActivity(intent);
    }


    /**
     * 权限回调接口
     */
    private PermissionUtils.CheckPermissionListener mPermissionListener;

    public void checkPermission(PermissionUtils.CheckPermissionListener listener, String...
            mPerms) {
        mPermissionListener = listener;
        if (PermissionUtils.hasPermissions(this, mPerms)) {
            if (mPermissionListener != null)
                mPermissionListener.permissionOnSuccessGrant();
        } else {
            PermissionUtils.requestPermissions(this,
                    PermissionUtils.PERMISSION_REQ_CODE, mPerms);
        }
    }

    public void Log(String log) {
        Log.i("Queen", "log:" + log);
    }


//******************************************************************************************

    public void setCallBack(EasyCallBack callBack){
        this.callBack = callBack;
    }

    public boolean checkPermission(String permission) {
        Log("checkPermission:" + permission);
        boolean check = EasypermissionsUtils.checkPermission(this, permission);
        Log("checkPermission check:" + check);
        return check;
    }

    public void requestPermission(String permission) {
        Log("requestPermission:" + permission);
        EasypermissionsUtils.requestPermission(this, "要照相用的", 5555, permission);
    }

    public void requestPermission(String permission,EasyCallBack callBack) {
        this.callBack = callBack;
        Log("requestPermission:" + permission);
        EasypermissionsUtils.requestPermission(this, "要照相用的", 5555, permission);
    }

    public void somePermissionPermanentlyDenied(String permission){
        Log("somePermissionPermanentlyDenied:" + permission);
        boolean check = EasypermissionsUtils.somePermissionPermanentlyDenied(this,permission);
        Log("somePermissionPermanentlyDenied check:" + check);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log("onPermissionsGranted 11111 requestCode：" + requestCode);
        //已经授权过
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log("onPermissionsDenied 22222 requestCode：" + requestCode);
        //提示弹窗被取消 可做被拒绝后的操作
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log("onPointerCaptureChanged 44444 hasCapture：" + hasCapture);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log("onRequestPermissionsResult 33333 requestCode：" + requestCode);
        EasypermissionsUtils.onRequestPermissionsResult(requestCode, permissions, grantResults,this);
    }
}
