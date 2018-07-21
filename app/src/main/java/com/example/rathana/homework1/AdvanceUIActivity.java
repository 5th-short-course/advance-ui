package com.example.rathana.homework1;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rathana.homework1.dialog.TimePicker;

import java.util.Calendar;

public class AdvanceUIActivity extends AppCompatActivity implements
        TimePicker.OnSetTimeListener
{

    static final int startTimeRequestCode=1;
    static final int endTimeRequestCode=2;

    private RadioGroup rdGroup;
    private AutoCompleteTextView autoCompleteTextView;
    private Spinner spinner;
    private TextView timeTextView,endTimeTextView;
    private TextView dateTextView;
    private String[] countries={"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_ui);

        rdGroup=findViewById(R.id.rg);
        autoCompleteTextView =findViewById(R.id.autoCompleteTextView);
        spinner =findViewById(R.id.spinner);
        timeTextView=findViewById(R.id.timeTextView);
        dateTextView=findViewById(R.id.dateTextView);
        endTimeTextView=findViewById(R.id.endTimeTextView);
        /*
        * setup adapter view
        * */
        ArrayAdapter<String> adapter=new ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                countries
        );

        /*
        * autocomplete TextView*/
        autoCompleteTextView.setThreshold(3);
        autoCompleteTextView.setAdapter(adapter);

        /*
        * spinner
        * */
        /*ArrayAdapter<String> spinnerAdapter=new ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                countries
        );*/

        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter(
                this,
                R.layout.spinner_item_layout,
                R.id.text1,
                countries
        );

        spinner.setAdapter(spinnerAdapter);

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rd= findViewById(checkedId);
                if(rd.isChecked())
                    showMessage(rd.getText().toString()+" checked");
                else
                    showMessage(rd.getText().toString()+" unchecked");
            }
        });

        /*
        * add event on timeTextView
        * */
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker picker=new TimePicker();
                picker.setRequestCode(startTimeRequestCode);
                picker.show(getFragmentManager(),"timePicker");
            }
        });

        /*
        * add event on Date TextView
        * */
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatePicker();
            }
        });

        endTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker picker=new TimePicker();
                picker.setRequestCode(endTimeRequestCode);
                picker.show(getFragmentManager(),"endTime");
            }
        });
    }


    void createDatePicker(){
        Calendar c=Calendar.getInstance();
        int dayOfMonth=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);

        DatePickerDialog dialog=new DatePickerDialog(this,dateListener,
                year,month,dayOfMonth);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener dateListener =
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date= " "+ dayOfMonth+"-"+(month+1) +"-"+year;
                    dateTextView.setText(date);
                }
            };



    void showMessage(String smg){
        Toast.makeText(this, smg, Toast.LENGTH_SHORT).show();
    }

    /*
    * get time from TimePick and set to TimeTextView*/
    @Override
    public void onSetTime(String time,int requestCode) {
        if(requestCode==startTimeRequestCode)
            timeTextView.setText(time);
        if(requestCode==endTimeRequestCode)
            endTimeTextView.setText(time);

    }
}
