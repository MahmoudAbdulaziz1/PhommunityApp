package mahmoudvic.org.phomunity.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.MainEvaluationActivity;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.EvaluationPostPOJO;
import mahmoudvic.org.phomunity.util.ImageUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluationvideographyAdapter extends RecyclerView.Adapter<EvaluationvideographyAdapter.MyViewHolder> {

    ArrayList<String> answers;
    private Context context;
    private ArrayList<String> questions;
    private ArrayList<String> answersOne;
    private ArrayList<String> answersTwo;
    private ArrayList<String> answersThree;
    private ArrayList<String> answerFour;
    private ArrayList<String> answeersFour;
    private List<String> images;
    private ArrayList<Integer> marks;
    private String category;
    private Button submit;
    private String value = null;
    private ImageView close;
    private TextView result;
    private TextView feedbackTxt;

    public EvaluationvideographyAdapter(Context context, ArrayList questions, ArrayList answersOne, ArrayList answersTwo,
                                        ArrayList answersThree, ArrayList<String> answerFour, ArrayList answeersFour,
                                        ArrayList marks, Button submit, ArrayList answers, String category, List images) {
        this.context = context;
        this.questions = questions;
        this.answersOne = answersOne;
        this.answersTwo = answersTwo;
        this.answersThree = answersThree;
        this.answeersFour = answeersFour;
        this.submit = submit;
        this.answers = answers;
        this.marks = marks;
        this.answerFour = answerFour;
        this.category = category;
        this.images = images;
    }

    @NonNull
    @Override
    public EvaluationvideographyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photography_items, parent, false);
        return new EvaluationvideographyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EvaluationvideographyAdapter.MyViewHolder holder, final int position) {
        if (images.size() == 0) {
//            holder.img.setVisibility(View.GONE);
            holder.img.setVisibility(View.VISIBLE);
            Picasso.with(context).
                    load(R.drawable.camera_images
                    ).
                    into(holder.img);
        } else {
            holder.img.setVisibility(View.VISIBLE);
            Picasso.with(context).
                    load(ImageUtil.BASE_IMAGE_URL + images.get(position)).
                    into(holder.img);
        }

        holder.questions.setText(questions.get(position));
        holder.answer_1.setText(answersOne.get(position));
        holder.answer_2.setText(answersTwo.get(position));
        if (answersThree.size() == 0) {
            holder.answer_3.setVisibility(View.GONE);
        } else {
            holder.answer_3.setVisibility(View.VISIBLE);
            holder.answer_3.setText(answersThree.get(position));
        }

        if (answerFour.size() == 0) {
            holder.answer_4.setVisibility(View.GONE);
        } else {
            holder.answer_4.setVisibility(View.VISIBLE);
            holder.answer_4.setText(answerFour.get(position));
        }
        //holder.answer_4.setText(answeersFour.get(position));

        holder.answer_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.answer_1.isChecked()) {
                    holder.answer_2.setChecked(false);
                    holder.answer_3.setChecked(false);
                    holder.answer_4.setChecked(false);
                    value = holder.answer_1.getText().toString();
                    answers.set(position, holder.answer_1.getText().toString());
                }
            }
        });
        holder.answer_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.answer_2.isChecked()) {
                    holder.answer_1.setChecked(false);
                    holder.answer_3.setChecked(false);
                    holder.answer_4.setChecked(false);
                    value = holder.answer_2.getText().toString();
                    answers.set(position, holder.answer_2.getText().toString());
                }
            }
        });
        holder.answer_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.answer_3.isChecked()) {
                    holder.answer_1.setChecked(false);
                    holder.answer_2.setChecked(false);
                    holder.answer_4.setChecked(false);
                    value = holder.answer_3.getText().toString();
                    answers.set(position, holder.answer_3.getText().toString());
                }
            }
        });
        holder.answer_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.answer_4.isChecked()) {
                    holder.answer_1.setChecked(false);
                    holder.answer_2.setChecked(false);
                    holder.answer_3.setChecked(false);
                    value = holder.answer_4.getText().toString();
                    answers.set(position, holder.answer_4.getText().toString());
                }
            }
        });


        final Dialog dialog = new Dialog(context);
        // Include dialog.xml file
        Typeface quickSand = Typeface.createFromAsset(context.getAssets(), "font/Quicksand-Regular.ttf");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_photography_popup);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.80);


        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);

        close = (ImageView) dialog.findViewById(R.id.photography_closeId);
        result = (TextView) dialog.findViewById(R.id.result_value);
        feedbackTxt = (TextView) dialog.findViewById(R.id.feedback);
        result.setTypeface(quickSand);
        feedbackTxt.setTypeface(quickSand);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //answers.add(position, value);
//                int fullResult = 0;
//                int yourResult = 0;
//                for (int i=0 ;i<answeersFour.size();i++){
//                    fullResult += marks.get(i);
//                    if (answers.get(i).equals(answeersFour.get(i))){
//                        yourResult += marks.get(i);
//                    }
//                }
                Api.getClient().saveEvaluationReview(category, value).enqueue(new Callback<EvaluationPostPOJO>() {
                    @Override
                    public void onResponse(Call<EvaluationPostPOJO> call, Response<EvaluationPostPOJO> response) {

                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                EvaluationPostPOJO res = response.body();
                                String mark = res.getMarks();
                                String feedback = res.getFeedback();
                                result.setText(mark);
                                feedbackTxt.setText(feedback);
                                dialog.show();
                                Log.d("res", value + "  " + res.getMarks() + " feed  " + res.getFeedback());
                            } else {
                                Toast.makeText(context, R.string.response_empty, Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(context, R.string.response_failure, Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<EvaluationPostPOJO> call, Throwable t) {
                        Toast.makeText(context, R.string.connection_failure, Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(context, MainEvaluationActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView questions;
        CheckBox answer_1;
        CheckBox answer_2;
        CheckBox answer_3;
        CheckBox answer_4;
        ImageView img;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Bold.ttf");


        public MyViewHolder(View itemView) {
            super(itemView);
            questions = itemView.findViewById(R.id.question);
            answer_1 = itemView.findViewById(R.id.answer1);
            answer_2 = itemView.findViewById(R.id.answer2);
            answer_3 = itemView.findViewById(R.id.answer3);
            answer_4 = itemView.findViewById(R.id.answer4);
            img = itemView.findViewById(R.id.photogrphy_image);
            questions.setTypeface(quickSand);
        }
    }


    public class MyImageHolder extends RecyclerView.ViewHolder {
        public ImageView fisrtIndexImage;

        public MyImageHolder(View view) {
            super(view);
            fisrtIndexImage = (ImageView) view.findViewById(R.id.first_index);
        }
    }
}
