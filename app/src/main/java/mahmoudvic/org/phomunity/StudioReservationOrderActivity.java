package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.StudioRentAdapter;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudioReservationOrderActivity extends Activity {
    private ImageView close;
    private EditText name;
    private EditText email;
    private EditText mobile;
    private EditText notes;
    private Button submit;
    private Calendar clickedDayCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_studio_reservation_order);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        close = (ImageView) findViewById(R.id.studio_reserve_closeId);
        name = (EditText) findViewById(R.id.studio_name_edit_text);
        email = (EditText) findViewById(R.id.studio_email_edit_text);
        mobile = (EditText) findViewById(R.id.studio_mobile_edit_text);
        notes = (EditText) findViewById(R.id.studio_notes);
        submit = (Button) findViewById(R.id.studio_reverse_submit);


        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.about_us));

        final CalendarView calendarView = (CalendarView) findViewById(R.id.studio_calendarView);
        calendarView.setEvents(events);


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                clickedDayCalendar = eventDay.getCalendar();
                //List<Calendar> selectedDates = calendarView.getSelectedDates();
                Toast.makeText(getBaseContext(), clickedDayCalendar.getTime() + "", Toast.LENGTH_LONG).show();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(getBaseContext(), dateStr, Toast.LENGTH_LONG).show();
                if (validate(name)) {
                    if (validate(email)) {
                        if (validate(mobile)) {
                            if (clickedDayCalendar == null) {
                                Toast.makeText(getBaseContext(), "Select your date From calender", Toast.LENGTH_LONG).show();
                            } else {
                                String nameStr = name.getText().toString().trim();
                                String emailStr = email.getText().toString().trim();
                                String mobileStr = mobile.getText().toString().trim();
                                String notesStr = notes.getText().toString().trim();
                                String dateStr = clickedDayCalendar.getTime().toString().trim();
                                Intent intent = getIntent();
                                addRentStudio(intent.getIntExtra(StudioRentAdapter.INTENT_ID, 0), intent.getStringExtra(StudioRentAdapter.INTENT_TITLE), nameStr, mobileStr, emailStr, notesStr, null);
                                finish();
                            }
                        } else {
                            mobile.setError("enter correct mobile");
                        }
                    } else {
                        email.setError("enter valid mail");
                    }
                } else {
                    name.setError("Enter your name");
                }


            }
        });
    }

    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private void addRentStudio(final int studio_option_id, final String title, String clientName, String clientPhoneNumber,
                               String clientEmailAddress, String notes, String selected) {
        Api.getClient().rentStudio(studio_option_id, title, clientName, clientPhoneNumber, clientEmailAddress, notes, selected).enqueue(new Callback<Integer>() {


            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (response != null && response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(getBaseContext(), R.string.request_success, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(StudioReservationOrderActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(StudioReservationOrderActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("Failed", t.getMessage());
                Toast.makeText(StudioReservationOrderActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();

            }
        });

    }

}