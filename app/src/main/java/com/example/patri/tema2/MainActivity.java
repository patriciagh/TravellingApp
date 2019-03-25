package com.example.patri.tema2;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Year;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String tripName;
    private String destination;
    private EditText editText1;
    private EditText editText2;

    private SeekBar seekBar;
    private RadioGroup radioTripTypeGroup;
    private RadioButton radioTripTypeButton;

    private Button startDateDisplay;
    private DatePickerDialog.OnDateSetListener startDateListener;
    private Button endDateDisplay;
    private DatePickerDialog.OnDateSetListener endDateListener;
    private String startDate="";
    private String endDate="";

    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addListenerOnRadioButtons();
        //EDIT TEXT 1
        editText1=((EditText)findViewById(R.id.editText1));
        editText1.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        tripName=s.toString();//am luat stringul
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );

        //EDIT TEXT 2
        editText2=((EditText)findViewById(R.id.editText2));
        editText2.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        destination=s.toString();//am luat stringul
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );

        //RADIO BUTTONS
        radioTripTypeGroup = (RadioGroup)findViewById(R.id.trip_type_group);
        seekBar=(SeekBar)findViewById(R.id.seek_bar);


        //DATE start + end

        //START DATE
        startDateDisplay=findViewById(R.id.buttonStartDate);
        startDateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iau ziua de azi si o afisez
                Calendar cal=Calendar.getInstance();
                int yearToday=cal.get(Calendar.YEAR);
                int monthToday=cal.get(Calendar.MONTH);
                int dayToday=cal.get(Calendar.DAY_OF_MONTH);
                //creez un date picker cu data de azi ca data initiala
                DatePickerDialog dialog=new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        startDateListener,
                        yearToday,monthToday,dayToday
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String date = day + "/" + month + "/" + year;
                startDateDisplay.setText(date);
                //Toast.makeText(MainActivity.this, "START Date is : "+day+" "+month+" "+year, Toast.LENGTH_SHORT).show();
                startDate="Start Date: "+day+"/"+month+"/"+year+"\n";
            }
        };


        //END DATE
        endDateDisplay=findViewById(R.id.buttonEndDate);
        endDateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iau ziua de azi si o afisez
                Calendar cal=Calendar.getInstance();
                int yearToday=cal.get(Calendar.YEAR);
                int monthToday=cal.get(Calendar.MONTH);
                int dayToday=cal.get(Calendar.DAY_OF_MONTH);
                //creez un date picker cu data de azi ca data initiala
                DatePickerDialog dialog=new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        endDateListener,
                        yearToday,monthToday,dayToday
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String date = day + "/" + month + "/" + year;
                endDateDisplay.setText(date);
               // Toast.makeText(MainActivity.this, "END Date is : "+day+" "+month+" "+year, Toast.LENGTH_SHORT).show();
                endDate="End Date: "+day+"/"+month+"/"+year+"\n";
            }
        };


        ratingBar=(RatingBar)findViewById(R.id.rating_bar);

        //BUTTON SALVARE
        findViewById(R.id.button1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Iau id-ul radiobuttonului selectat
                        int selectedId=radioTripTypeGroup.getCheckedRadioButtonId();
                        //gasesc radio buttonul in functie de id-ul sau
                        radioTripTypeButton=(RadioButton)findViewById(selectedId);
                        int seekBarValue = seekBar.getProgress();

                        Toast.makeText(MainActivity.this, "Trip name: "+tripName+"\n"+
                                "Destination: "+destination+"\n"+
                                "Trip type: "+radioTripTypeButton.getText()+"\n"+
                                "Price: "+Integer.toString(seekBarValue)+"\n"
                                +startDate+" "+endDate+
                                "Rating:"+ ratingBar.getRating(), Toast.LENGTH_LONG).show();
                        //curat edittexturile
                        editText1.setText("");
                        editText2.setText("");
                        tripName="";
                        destination="";
                    }
                }
        );



    }




}
