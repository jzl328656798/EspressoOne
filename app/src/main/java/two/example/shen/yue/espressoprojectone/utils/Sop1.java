package two.example.shen.yue.espressoprojectone.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.util.Calendar;

/**
 * Author: Queen
 * Date: 2020/4/13 2:02 PM
 * Describe: TODO
 */
public class Sop1 {

    public static void sop1(Context context) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        Log.i("queen", "yy" + yy);
        Log.i("queen", "mm" + mm);
        Log.i("queen", "dd" + dd);

        final Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.YEAR,2015);
        endCalendar.set(Calendar.MONTH,10);

        DatePickerDialog dlg = new DatePickerDialog(new ContextThemeWrapper(context,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar), null, yy, mm, 0) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                LinearLayout mSpinners = (LinearLayout) findViewById(context.getResources().getIdentifier("android:id/pickers", null, null));
                if (mSpinners != null) {
                    NumberPicker mYearSpinner = (NumberPicker) findViewById(context.getResources().getIdentifier("android:id/year", null, null));
                    NumberPicker mMonthSpinner = (NumberPicker) findViewById(context.getResources().getIdentifier("android:id/month", null, null));
                    mSpinners.removeAllViews();
                    if (mYearSpinner != null) {
                        mSpinners.addView(mYearSpinner);
                    }
                    if (mMonthSpinner != null) {
                        mSpinners.addView(mMonthSpinner);
                    }
                }
                View dayPickerView = findViewById(context.getResources().getIdentifier("android:id/day", null, null));
                if (dayPickerView != null) {
                    dayPickerView.setVisibility(View.GONE);
                }
            }

        };
        DatePicker datePicker = dlg.getDatePicker();
        datePicker.setMaxDate(calendar.getTimeInMillis());
        datePicker.setMinDate(endCalendar.getTimeInMillis());
        dlg.show();
    }
}
