package mahmoudvic.org.phomunity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.FAQAdapter;
import mahmoudvic.org.phomunity.adapters.ImageSliderAdapter;
import mahmoudvic.org.phomunity.pojo.CoursePOJO;
import mahmoudvic.org.phomunity.pojo.FaqPOJO;
import mahmoudvic.org.phomunity.pojo.ImagePOJO;
import mahmoudvic.org.phomunity.pojo.Margin;
import mahmoudvic.org.phomunity.util.CommonUtil;
import mahmoudvic.org.phomunity.util.ConfirmMessageUtil;
import mahmoudvic.org.phomunity.util.DateUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import mahmoudvic.org.phomunity.util.Util;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailsActivity extends AppCompatActivity {

    private TextView courseTitle;
    private TextView eventFrom;
    private TextView eventTo;
    private TextView eventAbout;
    private Button callBtn;
    private Button bookBtn;
    private ImageView instructorImage;
    private TextView instructorAbout;
    private TextView instructorName;
    //    private Gallery instructorGallery;
    private LinearLayout faqLayout;
    private TextView eventFaq;
    private List<CoursePOJO> coursePOJOS = new ArrayList<>();
    private RecyclerView sliderRecyclerView;

//    private RecyclerView faqRecyclerView;
//    private RecyclerView.LayoutManager faqLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_actiivty);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(CourseDetailsActivity.this, toolbarLayoutFragment);

        final TextView eventTitle = (TextView) findViewById(R.id.event_title);
        final TextView eventFrom = (TextView) findViewById(R.id.event_from);
        final TextView eventTo = (TextView) findViewById(R.id.event_to);
        final TextView eventAbout = (TextView) findViewById(R.id.event_about);
        final TextView eventFromTxt=findViewById(R.id.event_from_txt);
        final TextView eventToTxt=findViewById(R.id.event_to_txt);

        final TextView faqText = (TextView) findViewById(R.id.event_faq);

        final Button callBtn = (Button) findViewById(R.id.call_btn);
        final Button bookBtn = (Button) findViewById(R.id.book_btn);

        final ImageView instructorImage = (ImageView) findViewById(R.id.instructor_image);
        final ImageView arInstructorImage = (ImageView) findViewById(R.id.ar_instructor_Image);
        final TextView instructorAbout = (TextView) findViewById(R.id.instructor_about);
        final TextView instructorName = (TextView) findViewById(R.id.instructor_name);
        final CardView instructorCard = (CardView) findViewById(R.id.instructor_card);
        final CardView arInstructorcard = (CardView) findViewById(R.id.ar_instructor_card);

//        final Gallery instructorGallery = (Gallery) findViewById(R.id.instructor_gallery);
//        final LinearLayout galleryLayout = (LinearLayout) findViewById(R.id.gallery_layout);
        final LinearLayout faqLayout = (LinearLayout) findViewById(R.id.event_faq_layout);
        final TextView eventFaq = (TextView) findViewById(R.id.event_faq);
        final TextView detailsTxt = (TextView) findViewById(R.id.detailsTxt);
        Intent intent = this.getIntent();

        final int courseId = intent.getIntExtra("courseId", 0);
        final ProgressDialog progressDialog = new ProgressDialog(CourseDetailsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        try {
            (Api.getClient().getCourse(courseId)).enqueue(new Callback<List<CoursePOJO>>() {

                @Override
                public void onResponse(Call<List<CoursePOJO>> call, Response<List<CoursePOJO>> response) {

                    if (response != null && response.isSuccessful()) {
                        if (response.body() != null) {
                            coursePOJOS = response.body();
                            final CoursePOJO coursePOJO = coursePOJOS.get(0);
                            eventTitle.setText(coursePOJO.getName());

//                            String dateFrom = coursePOJO.getStartDate().split("\\s+")[0];
//                            String dateTo = coursePOJO.getEndDate().split("\\s+")[0];
                            final List<FaqPOJO> faqPOJOS = coursePOJO.getFaqList();
                            String name = coursePOJO.getInstructorName();
                            sliderRecyclerView = findViewById(R.id.image_slider);
                            if (coursePOJO.getImages() != null) {
                                loadSlider(coursePOJO.getImages());
//                                instructorGallery.setAdapter(new ImageGalleryAdapter(CourseDetailsActivity.this, coursePOJO.getImages(), true));
                            } else {
                                sliderRecyclerView.setVisibility(View.GONE);
                            }
                            String dateFrom = DateUtil.convertToString(DateUtil.convertToEntityAttribute(coursePOJO.getStartDate()));
                            String dateTo = DateUtil.convertToString(DateUtil.convertToEntityAttribute(coursePOJO.getEndDate()));

                            eventFrom.setText(dateFrom);
                            eventTo.setText(dateTo);
                            eventAbout.setText(coursePOJO.getDescription());

                            String imageFileName = "";
                            if (coursePOJO.getImages() != null) {
                                imageFileName = coursePOJO.getImages().get(0).getImageFilename();
                                if (!imageFileName.equals("")) {
                                    if (!new CommonUtil().isProbablyArabic(coursePOJO.getInstructorBio())) {
                                        arInstructorcard.setVisibility(View.GONE);
                                        instructorCard.setVisibility(View.VISIBLE);
                                        Glide.with(CourseDetailsActivity.this)
                                                .asBitmap()
                                                .load(ImageUtil.BASE_IMAGE_URL + imageFileName)
                                                .into(instructorImage);
                                    } else {
                                        instructorCard.setVisibility(View.GONE);
                                        arInstructorcard.setVisibility(View.VISIBLE);
                                        Glide.with(CourseDetailsActivity.this)
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
                            bookBtn.setText(R.string.book_now);
                            callBtn.setText(R.string.call);
                            faqText.setText(R.string.faq_en);

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
                            detailsTxt.setTypeface(quickSand);

                            SpannableString spannableStringAbout = new SpannableString(coursePOJO.getInstructorBio());
                            spannableStringAbout.setSpan(new Margin(3, instructorImage.getMeasuredWidth() + 20), 0, spannableStringAbout.length(), 0);
                            instructorAbout.setText(spannableStringAbout);

                            callBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + coursePOJO.getPhonesNumber().get(0)));
                                    CourseDetailsActivity.this.startActivity(intent);
                                }
                            });

                            faqLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (faqPOJOS.get(0).getQuestion().isEmpty()) {
                                        ConfirmMessageUtil.setMessage((LinearLayout) findViewById(R.id.event_details_activity), "There Is No FAQ For This Course", getBaseContext());
                                    } else {
                                        Typeface quickSand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.ttf");

                                        final Dialog dialog = new Dialog(CourseDetailsActivity.this);
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        dialog.setContentView(R.layout.faq_activity);
                                        Util.setDialogSize(CourseDetailsActivity.this, dialog);
                                        final RecyclerView re = dialog.findViewById(R.id.faq_recycler_view);

                                        final ImageView closeId = (ImageView) dialog.findViewById(R.id.faq_closeId);
                                        RecyclerView.LayoutManager faqLayoutManager = new LinearLayoutManager(getParent(), LinearLayoutManager.VERTICAL, false);
                                        Log.d("NullCheck", "faqRecyclerView:" + re + "  faqLayoutManager:" + faqLayoutManager);
                                        re.setLayoutManager(faqLayoutManager);
                                        Log.d("toCheck", "faqPOJOS:" + faqPOJOS + " isempty:" + faqPOJOS.isEmpty() + " Q:" + faqPOJOS.get(0).getQuestion() + " QISEmpty:" + faqPOJOS.get(0).getQuestion().isEmpty() + " An:" + faqPOJOS.get(0).getQuestion() + " AnISEmpty:" + faqPOJOS.get(0).getQuestion().isEmpty());
                                        FAQAdapter faqAdapter = new FAQAdapter(CourseDetailsActivity.this, faqPOJOS);
                                        re.setAdapter(faqAdapter);
                                        dialog.show();

                                        closeId.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });
                                        coursePOJO.getFaqList();
                                    }

                                }
                            });
                            bookBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Create custom dialog object
                                    final Dialog dialog = new Dialog(CourseDetailsActivity.this);
                                    // Include dialog.xml file
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog.setContentView(R.layout.book_event_dialog);
                                    Util.setDialogSize(CourseDetailsActivity.this, dialog);
                                    // Set dialog title
                                    ImageView closeId = (ImageView) dialog.findViewById(R.id.event_closeId);
                                    // set values for custom dialog components - text, image and button
                                    final EditText nameEditText = (EditText) dialog.findViewById(R.id.event_name_edit);
                                    final EditText emailEditText = (EditText) dialog.findViewById(R.id.event_email_edit);
                                    final EditText mobileEditText = (EditText) dialog.findViewById(R.id.event_mobile_edit);
                                    final EditText noteEditText = (EditText) dialog.findViewById(R.id.event_note_edit);

                                    dialog.show();

                                    Button sendEvent = (Button) dialog.findViewById(R.id.send_event);
                                    final String name = nameEditText.getText().toString();
                                    final String email = emailEditText.getText().toString();
                                    final String mobile = mobileEditText.getText().toString();
                                    final String note = noteEditText.getText().toString();
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

                                            final ProgressDialog progressDialog = new ProgressDialog(CourseDetailsActivity.this);
                                            progressDialog.setCancelable(false); // set cancelable to false
                                            progressDialog.setMessage("Please Wait"); // set message
                                            progressDialog.show(); // show progress dialog

                                            (Api.getClient().orderCourse(coursePOJO.getId(), name, email, mobile, note)).enqueue(new Callback<Integer>() {
                                                @Override
                                                public void onResponse(Call<Integer> call, Response<Integer> response) {

                                                    if (response.isSuccessful()) {
                                                        //signUpResponsesData = response.body();
                                                        if (response.body() != null) {
                                                            if (response.body().intValue() == 1) {
                                                                Toast.makeText(CourseDetailsActivity.this, "Success", Toast.LENGTH_LONG).show();
                                                                Log.d("ok", response.toString());
                                                                // Close dialog
                                                                dialog.dismiss();
//                                                    finish();
                                                            } else {
                                                                Toast.makeText(CourseDetailsActivity.this, "Check your connection", Toast.LENGTH_LONG).show();
                                                            }


                                                        } else {
                                                            Toast.makeText(CourseDetailsActivity.this, "response fail ", Toast.LENGTH_LONG).show();
                                                            Log.d("response fail", "Boom");
                                                        }
                                                    } else {
                                                        Toast.makeText(CourseDetailsActivity.this, " failed " + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                                                        Log.d("fail", response.toString());

                                                    }
                                                    progressDialog.dismiss();

                                                }

                                                @Override
                                                public void onFailure(Call<Integer> call, Throwable t) {
                                                    Toast.makeText(CourseDetailsActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                                                    Log.d("response", t.getStackTrace().toString() + " " + call.toString());
                                                    progressDialog.dismiss();

                                                }
                                            });
                                        }
                                    });


                                }

                            });


                            List<String> days = coursePOJO.getDays();
                            StringBuffer details = new StringBuffer();
                            details.append("\nDetails\n");
                            if (days != null && !days.isEmpty()) {
                                int dayNumber = 1;
                                for (String day : days) {
                                    details.append("\nLecture " + dayNumber + "\n");
                                    details.append(day + "\n");
                                    dayNumber++;
                                }
                                Log.d("details", details.toString());
                                detailsTxt.setText(details.toString());

                            }
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(CourseDetailsActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                        }
                    } else {

                        Toast.makeText(CourseDetailsActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<List<CoursePOJO>> call, Throwable t) {
                    Toast.makeText(CourseDetailsActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadSlider(List<ImagePOJO> slides) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CourseDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        sliderRecyclerView.setLayoutManager(layoutManager);

        if (slides != null) {
            ImageSliderAdapter adapter = new ImageSliderAdapter(CourseDetailsActivity.this, slides);
            sliderRecyclerView.setAdapter(adapter);
        } else {
            // show message to the user
        }
    }
}

