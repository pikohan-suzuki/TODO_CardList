package com.amebaownd.pikohan_nwiatori.todocardlist.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.amebaownd.pikohan_nwiatori.todocardlist.Data.Event
import com.amebaownd.pikohan_nwiatori.todocardlist.R
import kotlinx.android.synthetic.main.new_card_layout.*
import java.util.*

interface NewCartDialogOkButton:EventListener{
    fun onOkButtonClick()
}

class NewCardDialog:DialogFragment(){
    private var event:Event? = null
    private var listener : NewCartDialogOkButton?=null
    private var addButton:Button? = null
    private var titleEditText:EditText?=null
    private var dateEditText:EditText?=null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog : View = activity!!.layoutInflater.inflate(R.layout.new_card_layout,null)
        addButton = alertDialog.findViewById<Button>(R.id.add_button)
       titleEditText= alertDialog.findViewById<EditText>(R.id.titleEditText)
        dateEditText= alertDialog.findViewById<EditText>(R.id.dateEditText)
        val memoEditText= alertDialog.findViewById<EditText>(R.id.memoEditText)
        titleEditText!!.addTextChangedListener(editTextOnChangeListener)
        dateEditText!!.addTextChangedListener(editTextOnChangeListener)
        addButton!!.setOnClickListener{
            event = Event(titleEditText!!.text.toString(),dateEditText!!.text.toString().toLong(),memoEditText.text.toString())
            listener!!.onOkButtonClick()
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
    fun setOnOkButtonClickListener(listener : NewCartDialogOkButton){
        this.listener=listener
    }
    private  val editTextOnChangeListener = object: TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            addButton!!.isEnabled = titleEditText!!.text!=null && dateEditText!=null
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }
}