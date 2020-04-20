package two.example.shen.yue.espressoprojectone.test7

import android.app.DatePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_test7_2.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.utils.Sop1
import java.util.*

/**
 * Author: Queen
 * Date: 2020/4/10 3:56 PM
 * Describe: Test7Activity1
 */
class Test7Activity2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test7_2)

        btn_1.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(startDatePicker: DatePicker?, startYear: Int, startMonthOfYear: Int, startDayOfMonth: Int) {

                }

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

            val datePicker = dialog.datePicker
            hideDay(datePicker)
            dialog.show()
        }



        btn_2.setOnClickListener { sop2() }
        btn_3.setOnClickListener { Sop1.sop1(this) }

    }

    private fun hideDay(mDatePicker: DatePicker) {
        try {
            val daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android")
            if (daySpinnerId != 0) {
                val daySpinner = mDatePicker.findViewById<View>(daySpinnerId)
                if (daySpinner != null) {
                    daySpinner.visibility = View.GONE
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun sop2(){
        val calendar = Calendar.getInstance()
        val dialog = two.example.shen.yue.espressoprojectone.view.DatePickerDialog(this, object : two.example.shen.yue.espressoprojectone.view.DatePickerDialog.OnDateSetListener {
            override fun onDateSet(startDatePicker: DatePicker?, startYear: Int, startMonthOfYear: Int, startDayOfMonth: Int) {

            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        dialog.show()
    }


}