package two.example.shen.yue.espressoprojectone.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by queen on 2018/11/7.
 * Author: Queen
 * Date: 2018/11/7
 * Time: 下午3:20
 * Email: zhuolei.jiang@softlinker.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class ScreenUtils {

    private static float sNoncompatDensity;
    private static float sNoncompatScaleDensity;

    public static void setCustomDensity(Activity activity, Application application) {

        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaleDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaleDensity = application.getResources().getDisplayMetrics()
                                .scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });

        }

        final float targetDensity = appDisplayMetrics.widthPixels / 400;
        final float targetScaledDensity = targetDensity * (sNoncompatScaleDensity /
                sNoncompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
