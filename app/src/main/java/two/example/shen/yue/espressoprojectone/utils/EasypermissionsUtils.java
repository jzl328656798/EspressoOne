package two.example.shen.yue.espressoprojectone.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by queen on 2018/7/6.
 * Author: Queen
 * Date: 2018/7/6
 * Time: 上午9:49
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: 权限回调
 */
public class EasypermissionsUtils {


    /**
     * 检查权限
     *
     * @param context 上下文
     * @param perms   要检查的权限
     * @return true:已经获取权限  false: 未获取权限，主动请求权限
     */
    public static boolean checkPermission(Activity context, String... perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    /**
     * 申请权限
     *
     * @param context     上下文
     * @param tip         提示权限用途
     * @param requestCode 请求权限唯一标识
     * @param perms       要申请的权限
     */
    public static void requestPermission(Activity context, String tip, int requestCode, String...
            perms) {
        EasyPermissions.requestPermissions(context, tip, requestCode, perms);
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults,Activity context) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults,context);
    }

    public static boolean somePermissionPermanentlyDenied(Activity context, String... perms) {
        List<String> list = null;
        if (perms != null) {
            list = new ArrayList<>();
            for (String s : perms) {
                list.add(s);
            }
        }
        return EasyPermissions.somePermissionPermanentlyDenied(context, list);
    }
}
