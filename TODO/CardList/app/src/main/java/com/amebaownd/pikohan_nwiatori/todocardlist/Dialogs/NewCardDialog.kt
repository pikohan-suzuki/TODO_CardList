package com.amebaownd.pikohan_nwiatori.todocardlist.Dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.amebaownd.pikohan_nwiatori.todocardlist.Data.Event
import com.amebaownd.pikohan_nwiatori.todocardlist.R
import kotlinx.android.synthetic.main.new_card_layout.*
import java.text.SimpleDateFormat
import java.util.*

interface newCartDialogOkButton:EventListener{
    fun onOkButtonClick()
}
class NewCardDialog():DialogFragment(){
    private var event:Event? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog : View = activity!!.layoutInflater.inflate(R.layout.new_card_layout,null)
        val addButton = alertDialog.findViewById<Button>(R.id.add_button)
        val titleEditText= alertDialog.findViewById<EditText>(R.id.titleEditText)
        val dateEditText= alertDialog.findViewById<EditText>(R.id.dateEditText)
        val memoEditText= alertDialog.findViewById<EditText>(R.id.memoEditText)
        titleEditText.addTextChangedListener(editTextOnChangeListener)
        dateEditText.addTextChangedListener(editTextOnChangeListener)
        addButton.setOnClickListener{
            event = Event(titleEditText.text.toString(),dateEditText.text.toString().toLong(),memoEditText.text.toString())
            val intent= Intent()
            val pendingIntent =activity!!.createPendingResult(targetRequestCode,intent, PendingIntent.FLAG_ONE_SHOT)
            try{
                pendingIntent.send(Activity.RESULT_OK)
            }catch (ex: PendingIntent.CanceledException){
                ex.printStackTrace()
            }
            this.dismiss()
        }

        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setView(alertDialog)
        this.isCancelable=false
        return dialogBuilder.create()
    }

    fun getEvent():Event{
        return event!!
    }
    private  val editTextOnChangeListener = object: TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            add_button.isEnabled = titleEditText.text!=null && dateEditText!=null
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }
}