package by.fro.presentation.home

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import by.fro.presentation.R

class CityPickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!.applicationContext)
        val inflater = this.layoutInflater

        builder.setView(inflater.inflate(R.layout.add_city_dialog, null))
            .setNeutralButton(R.string.add) { dialogInterface: DialogInterface, id: Int ->
                val editText = this
            }

        return super.onCreateDialog(savedInstanceState)
    }
}