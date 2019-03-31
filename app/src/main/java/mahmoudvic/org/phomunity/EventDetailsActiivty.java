package mahmoudvic.org.phomunity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.internal.n;

import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.ImageSliderAdapter;
import mahmoudvic.org.phomunity.pojo.EventPOJO;
import mahmoudvic.org.phomunity.pojo.ImagePOJO;
import mahmoudvic.org.phomunity.pojo.Margin;
import mahmoudvic.org.phomunity.util.CommonUtil;
import mahmoudvic.org.phomunity.util.DateUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActiivty extends AppCompatActivity {
    private RecyclerView sliderRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_actiivty);

        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(EventDetailsActiivty.this, toolbarLayoutFragment);

        final TextView eventTitle = (TextView) findViewById(R.id.event_title);
        final TextView eventFrom = (TextView) findViewById(R.id.event_from);
        final TextView eventTo = (TextView) findViewById(R.id.event_to);
        final TextView eventAbout = (TextView) findViewById(R.id.event_about);
        final TextView eventFromTxt = findViewById(R.id.event_from_txt);
        final TextView eventToTxt = findViewById(R.id.event_to_txt);

        final Button callBtn = (Button) findViewById(R.id.call_btn);
        final Button bookBtn = (Button) findViewById(R.id.book_btn);

        final LinearLayout bookLayout = findViewById(R.id.book_layout);
        final LinearLayout callLayout = findViewById(R.id.call_layout);

        final ImageView instructorImage = (ImageView) findViewById(R.id.instructor_image);
        final ImageView arInstructorImage = (ImageView) findViewById(R.id.ar_instructor_Image);
        final CardView instructorCard = (CardView) findViewById(R.id.instructor_card);
        final CardView arInstructorcard = (CardView) findViewById(R.id.ar_instructor_card);
        final TextView instructorAbout = (TextView) findViewById(R.id.instructor_about);
        final TextView instructorName = (TextView) findViewById(R.id.instructor_name);
        final TextView faqText = (TextView) findViewById(R.id.event_faq);

        final LinearLayout faqLayout = (LinearLayout) findViewById(R.id.event_faq_layout);
        faqLayout.setVisibility(View.GONE);
        final TextView detailsTxt = (TextView) findViewById(R.id.detailsTxt);
        detailsTxt.setVisibility(View.GONE);
        Intent intent = this.getIntent();

        final int eventId = intent.getIntExtra("eventId", 0);
        final ProgressDialog progressDialog = new ProgressDialog(EventDetailsActiivty.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        try {
            (Api.getClient().getEvent(eventId)).enqueue(new Callback<EventPOJO>() {

                @Override
                public void onResponse(Call<EventPOJO> call, Response<EventPOJO> response) {

                    if (response.isSuccessful()) {
                        //signUpResponsesData = response.body();
                        if (response.body() != null) {
                            final EventPOJO eventPOJO = response.body();

                            eventTitle.setText(eventPOJO.getName());

                            String dateFrom = DateUtil.convertToString(DateUtil.convertToEntityAttribute(eventPOJO.getDateFrom()));
                            String dateTo = DateUtil.convertToString(DateUtil.convertToEntityAttribute(eventPOJO.getDateTo()));

                            String name = eventPOJO.getInstructorName();
                            String about = eventPOJO.getInstructorBio();

                            eventFrom.setText(dateFrom);
                            eventTo.setText(dateTo);
                            eventAbout.setText(eventPOJO.getAbout());
                            sliderRecyclerView = findViewById(R.id.image_slider);
                            if (eventPOJO.getSamplesSecondShooterPOJOS() != null) {
                                loadSlider(eventPOJO.getSamplesSecondShooterPOJOS());
                            } else {
                                sliderRecyclerView.setVisibility(View.GONE);
                            }

                            String imageFileName = "";
                            if (eventPOJO.getInstructorImage() != null) {
                                imageFileName = eventPOJO.getInstructorImage().getImageFileName();
                                if (!imageFileName.equals("")) {
                                    if (!new CommonUtil().isProbablyArabic(about)) {
                                        arInstructorcard.setVisibility(View.GONE);
                                        instructorCard.setVisibility(View.VISIBLE);
                                        Glide.with(EventDetailsActiivty.this)
                                                .asBitmap()
                                                .load(ImageUtil.BASE_IMAGE_URL + imageFileName)
                                                .into(instructorImage);
                                    } else {
                                        instructorCard.setVisibility(View.GONE);
                                        arInstructorcard.setVisibility(View.VISIBLE);
                                        Glide.with(EventDetailsActiivty.this)
                                                .asBitmap()
                                                .load(ImageUtil.BASE_IMAGE_URL + imageFileName)
                                                .into(arInstructorImage);
                                    }
                                }
                            }
                            SpannableString spannableStringName = new SpannableString(name);
                            spannableStringName.setSpan(new Margin(3, instructorImage.getMeasuredWidth() + 20), 0, spannableStringName.length(), 0);
                            instructorName.setText(spannableStringName);
                            eventFromTxt.setText(R.string.from);
                            eventToTxt.setText(R.string.to);
                            faqText.setText(R.string.faq_en);
                            bookBtn.setText(R.string.book_now);
                            callBtn.setText(R.string.call);

                            final Typeface quickSand = Typeface.createFromAsset(getBaseContext().getAssets(), "font/Quicksand-Bold.ttf");
                            instructorName.setTypeface(quickSand);
                            instructorAbout.setTypeface(quickSand);
                            eventAbout.setTypeface(quickSand);
                            eventTitle.setTypeface(quickSand);
                            eventFrom.setTypeface(quickSand);
                            eventTo.setTypeface(quickSand);
                            eventFromTxt.setTypeface(quickSand);
                            eventToTxt.setTypeface(quickSand);
                            faqText.setTypeface(quickSand);
                            bookBtn.setTypeface(quickSand);
                            callBtn.setTypeface(quickSand);

                            SpannableString spannableStringAbout = new SpannableString(about);
                            spannableStringAbout.setSpan(new Margin(3, instructorImage.getMeasuredWidth() + 20), 0, spannableStringAbout.length(), 0);
                            instructorAbout.setText(spannableStringAbout);
                            bookLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    handleBook(eventPOJO);
                                }
                            });
                            bookBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    handleBook(eventPOJO);
                                }
                            });

                            callBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    handleCall(eventPOJO);
                                }
                            });
                            callLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    handleCall(eventPOJO);
                                }
                            });


                        } else {
                            Toast.makeText(EventDetailsActiivty.this, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(EventDetailsActiivty.this, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<EventPOJO> call, Throwable t) {
                    Toast.makeText(EventDetailsActiivty.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadSlider(List<ImagePOJO> slides) {
        RecyclerView recyclerView = findViewById(R.id.image_slider);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EventDetailsActiivty.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if (slides != null) {
            ImageSliderAdapter adapter = new ImageSliderAdapter(EventDetailsActiivty.this, slides);
            recyclerView.setAdapter(adapter);
        } else {
            // show message to the user
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.book_layout:
//            case R.id.book_btn:
//                handleBook();
//
//        }
//    }

    private void handleCall(EventPOJO eventPOJO) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + eventPOJO.getPhonesNumber().get(0)));
        EventDetailsActiivty.this.startActivity(intent);
    }

    private void handleBook(final EventPOJO eventPOJO) {
        // Create custom dialog object
        Typeface quickSand = Typeface.createFromAsset(EventDetailsActiivty.this.getAssets(), "font/Quicksand-Regular.ttf");

        final Dialog dialog = new Dialog(EventDetailsActiivty.this);
        // Include dialog.xml file
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.book_event_dialog);
        DisplayMetrics metrics = EventDetailsActiivty.this.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.90);
        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);

        // Set dialog title
        ImageView closeId = (ImageView) dialog.findViewById(R.id.event_closeId);
        // set values for custom dialog components - text, image and button
        final EditText nameEditText = (EditText) dialog.findViewById(R.id.event_name_edit);
        final EditText emailEditText = (EditText) dialog.findViewById(R.id.event_email_edit);
        final EditText mobileEditText = (EditText) dialog.findViewById(R.id.event_mobile_edit);
        final EditText noteEditText = (EditText) dialog.findViewById(R.id.event_note_edit);
        nameEditText.setTypeface(quickSand);
        emailEditText.setTypeface(quickSand);
        mobileEditText.setTypeface(quickSand);
        noteEditText.setTypeface(quickSand);
        dialog.show();

        Button sendEvent = (Button) dialog.findViewById(R.id.send_event);

        // if decline button is clicked, close the custom dialog
        closeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String note = noteEditText.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(email)) {
                        if (!TextUtils.isEmpty(mobile)) {
                            if (!TextUtils.isEmpty(note)) {
                                final ProgressDialog progressDialog = new ProgressDialog(EventDetailsActiivty.this);
                                progressDialog.setCancelable(false); // set cancelable to false
                                progressDialog.setMessage("Please Wait"); // set message
                                progressDialog.show(); // show progress dialog

                                (Api.getClient().orderEvent(eventPOJO.getId(), name, email, mobile, note)).enqueue(new Callback<Integer>() {
                                    @Override
                                    public void onResponse(Call<Integer> call, Response<Integer> response) {

                                        if (response.isSuccessful()) {
                                            //signUpResponsesData = response.body();
                                            if (response.body() != null) {
                                                if (response.body().intValue() == 1) {
                                                    Toast.makeText(EventDetailsActiivty.this, "Success", Toast.LENGTH_LONG).show();
                                                    Log.d("ok", response.toString());
                                                    // Close dialog
                                                    dialog.dismiss();
//                                                    finish();
                                                } else {
                                                    Toast.makeText(EventDetailsActiivty.this, "Check your connection", Toast.LENGTH_LONG).show();
                                                }


                                            } else {
                                                Toast.makeText(EventDetailsActiivty.this, " Response Failure", Toast.LENGTH_SHORT).show();
                                                Log.d("response fail", "Boom");
                                            }
                                        } else {
                                            Toast.makeText(EventDetailsActiivty.this, "Empty Response", Toast.LENGTH_SHORT).show();
                                            Log.d("fail", response.toString());

                                        }
                                        progressDialog.dismiss();

                                    }

                                    @Override
                                    public void onFailure(Call<Integer> call, Throwable t) {
                                        Toast.makeText(EventDetailsActiivty.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                                        Log.d("response", t.getStackTrace().toString() + " " + call.toString());
                                        progressDialog.dismiss();

                                    }
                                });
                            } else {
                                noteEditText.setError("enter note");
                            }
                        } else {
                            mobileEditText.setError("enter mobile");
                        }
                    } else {
                        emailEditText.setError("enter email");
                    }
                } else {
                    nameEditText.setError("enter name");
                }

            }
        });

    }


}

