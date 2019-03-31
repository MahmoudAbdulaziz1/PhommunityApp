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
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.registerdropdown.ActionItem2;
import mahmoudvic.org.phomunity.registerdropdown.QuickAction2;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationOrderActivity extends Activity {
    private ImageView close;
    private EditText name;
    private EditText email;
    private EditText mobile;
    private EditText notes;
    private TextView branch;
    private TextView perHour;
    private EditText addMoreItems;
    private Button submit;
    private QuickAction2 mQuickAction;
    private QuickAction2 catQuickAction;
    private String branchName;
    private String hourName;

    private Calendar clickedDayCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activitiy_reservation_order_irent);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        close = (ImageView) findViewById(R.id.irent_reserve_closeId);
        name = (EditText) findViewById(R.id.irent_name_edit_text);
        email = (EditText) findViewById(R.id.irent_email_edit_text);
        mobile = (EditText) findViewById(R.id.irent_mobile_edit_text);
        notes = (EditText) findViewById(R.id.irent_notes);
        branch = (TextView) findViewById(R.id.irent_branch_edit_text);
        perHour = (TextView) findViewById(R.id.irent_per_hour_edit_text);
        addMoreItems = (EditText) findViewById(R.id.irent_add_more_item_edit_text);
        submit = (Button) findViewById(R.id.irent_reverse_submit);
        name.setText(getSharedPreferences("remember", MODE_PRIVATE).getString("first", "") + " " +
                getSharedPreferences("remember", MODE_PRIVATE).getString("last", ""));
        email.setText(getSharedPreferences("remember", MODE_PRIVATE).getString("email", ""));
        mobile.setText(getSharedPreferences("remember", MODE_PRIVATE).getString("mobile", ""));

        mQuickAction = new QuickAction2(getBaseContext(), QuickAction2.VERTICAL);
        catQuickAction = new QuickAction2(getBaseContext(), QuickAction2.VERTICAL);

        final ArrayList<String> ti = new ArrayList<>(Arrays.asList("12 Hours", "24 Hours"));
        ActionItem2 item1_b = new ActionItem2(0, "12 Hours");
        catQuickAction.addActionItem(item1_b);
        ActionItem2 item2_b = new ActionItem2(1, "24 Hours");
        catQuickAction.addActionItem(item2_b);

        catQuickAction.setOnActionItemClickListener(new QuickAction2.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction2 source, int pos, int actionId) {
                perHour.setText(ti.get(pos));
                getSharedPreferences("b",MODE_PRIVATE).edit().putString("hourName", ti.get(pos)).commit();
                hourName = ti.get(pos);
                Log.d("branch", hourName);
            }
        });

        Bundle branchB = getIntent().getBundleExtra("branch");
        final ArrayList<String> branchesArray = new ArrayList<>();
        int branchSize = branchB.getInt("size");
        if (branchSize > 0) {
            for (int i = 0; i < branchSize; i++) {
                branchesArray.add(branchB.getString("branch" + i) + " ");
                Log.d("branch is ", branchB.getString("branch" + i) + " ");
            }

        }

//        ActionItem b_item0 = new ActionItem(0, "Brand");
//        mQuickAction.addActionItem(b_item0);
        for (int i = 0; i < branchesArray.size(); i++) {

            ActionItem2 item1 = new ActionItem2(i, branchesArray.get(i));
            mQuickAction.addActionItem(item1);
        }

        mQuickAction.setOnActionItemClickListener(new QuickAction2.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction2 source, int pos, int actionId) {
                branch.setText(branchesArray.get(pos));
                getSharedPreferences("b",MODE_PRIVATE).edit().putString("branchName", branchesArray.get(pos)).commit();
                branchName = branchesArray.get(pos);
            }
        });

        Log.d("branch", getSharedPreferences("b",MODE_PRIVATE).getString("branchName",""));

        branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickAction.show(v);
            }
        });
        perHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catQuickAction.show(v);
            }
        });

        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.about_us));

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
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
                            if (!getSharedPreferences("b",MODE_PRIVATE).getString("branchName","").isEmpty()) {
                                if (!getSharedPreferences("b",MODE_PRIVATE).getString("hourName", "").equals("")) {
                                    if (clickedDayCalendar == null) {
                                        Toast.makeText(getBaseContext(), "Select your date From calender", Toast.LENGTH_LONG).show();
                                    } else {
                                        String nameStr = name.getText().toString().trim();
                                        String emailStr = email.getText().toString().trim();
                                        String mobileStr = mobile.getText().toString().trim();
                                        String notesStr = notes.getText().toString().trim();
                                        String branchStr = branch.getText().toString();
                                        String perHourStr = perHour.getText().toString().trim();
                                        String addMoreStr = addMoreItems.getText().toString().trim();
                                        String dateStr = clickedDayCalendar.getTime().toString().trim();
                                        Intent intent = getIntent();
                                        addRentCamera(intent.getIntExtra("id", 0), getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0), nameStr, mobileStr, emailStr, notesStr,
                                                addMoreStr, branchName, hourName, dateStr);
                                        finish();
                                    }
                                } else {
                                    Log.d("1", "hour");
                                    perHour.setError("Enter duration 12 or 24");
                                }
                            } else {
                                Log.d("1", "branch");
                                branch.setError("Enter the branch");
                            }
                        } else {
                            Log.d("1", "mobile");
                            mobile.setError("enter correct mobile");
                        }
                    } else {
                        Log.d("1", "mail");
                        email.setError("enter valid mail");
                    }
                } else {
                    Log.d("1", "name");
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

    private void addRentCamera(int product_id, int frontUSerId, String nameStr, String mobileStr, String emailStr, String notesStr
            , String addMoreStr, String branchStr, String perHourStr, String dateStr) {
        Api.getClient().rentCamera(product_id, frontUSerId, nameStr, mobileStr, emailStr, notesStr,
                addMoreStr, branchStr, perHourStr, dateStr).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("true", response.body().intValue() + "");
                        Toast.makeText(getBaseContext(), "your request has been Sent Successfully", Toast.LENGTH_LONG).show();
                        //ConfirmMessageUtil.setMessage((LinearLayout) findViewById(R.id.rent_msg),"your request has been Sent Successfully", getBaseContext());
                    } else {
                        Toast.makeText(getApplicationContext(), " Response Failure", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("false", "is empty");
                    Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(ReservationOrderActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
