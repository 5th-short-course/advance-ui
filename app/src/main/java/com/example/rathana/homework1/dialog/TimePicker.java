package com.example.rathana.homework1.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;

public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private int requestCode;
    public void setRequestCode(int requestCode){
        this.requestCode=requestCode;
    }

    private OnSetTimeListener listener;

   /* public TimePicker(){
        listener= (OnSetTimeListener) getActivity();
    }
*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= (OnSetTimeListener) context;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR);
        int mn=c.get(Calendar.MINUTE);
        int s=c.get(Calendar.SECOND);

        TimePickerDialog dialog=new TimePickerDialog(getActivity(),
                this,hour,mn, DateFormat.is24HourFormat(getActivity()));

        return dialog;
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        String time ="";
        String hour="";
        String mn="";
        if(hourOfDay<10){
            hour="0"+hourOfDay;
        }else
            hour=hourOfDay+"";

        if(minute<10)
            mn="0"+minute;
        else
            mn=minute+"";

        time= hour+":"+mn;

        listener.onSetTime(time,requestCode);
        Log.e("Time:)",time);
    }

    public interface OnSetTimeListener{
        void onSetTime(String time,int requestCode);
    }
}


