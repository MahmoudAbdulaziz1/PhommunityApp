package mahmoudvic.org.phomunity.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluationLightingAdapter extends RecyclerView.Adapter {

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
    private int image_url;
    private String name;
    private Activity activity;

    public EvaluationLightingAdapter(Context context, ArrayList questions, ArrayList answersOne, ArrayList answersTwo,
                                     ArrayList answersThree, ArrayList<String> answerFour, ArrayList answeersFour,
                                     ArrayList marks, Button submit, ArrayList answers, String category, List images, int image_url,
                                     String name, Activity activity) {
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
        this.image_url = image_url;
        this.name = name;
        this.activity = activity;
        Calligrapher calligrapher = new Calligrapher(context);
        calligrapher.setFont(activity, "font/Quicksand-Regular.ttf", true);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == 1) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.first_index_recycler, parent, false);
            return new EvaluationLightingAdapter.MyImageHolder(itemView);

        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photography_items, parent, false);
            return new EvaluationLightingAdapter.MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            if (images.size() <= 1) {
//            holder.img.setVisibility(View.GONE);
                ((MyViewHolder) holder).img.setVisibility(View.VISIBLE);
                Picasso.with(context).
                        load(R.drawable.camera_images
                        ).
                        into(((MyViewHolder) holder).img);
            } else {
                ((MyViewHolder) holder).img.setVisibility(View.VISIBLE);
                Picasso.with(context).
                        load(ImageUtil.BASE_IMAGE_URL + images.get(position)).
                        into(((MyViewHolder) holder).img);
            }

            ((MyViewHolder) holder).questions.setText(questions.get(position));
            ((MyViewHolder) holder).answer_1.setText(answersOne.get(position));
            ((MyViewHolder) holder).answer_2.setText(answersTwo.get(position));
            if (answersThree.size() <= 1) {
                ((MyViewHolder) holder).answer_3.setVisibility(View.GONE);
            } else {
                ((MyViewHolder) holder).answer_3.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).answer_3.setText(answersThree.get(position));
            }

            if (answerFour.size() <= 1) {
                ((MyViewHolder) holder).answer_4.setVisibility(View.GONE);
            } else {
                ((MyViewHolder) holder).answer_4.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).answer_4.setText(answerFour.get(position));
            }
            //holder.answer_4.setText(answeersFour.get(position));

            ((MyViewHolder) holder).answer_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (((MyViewHolder) holder).answer_1.isChecked()) {
                        ((MyViewHolder) holder).answer_2.setChecked(false);
                        ((MyViewHolder) holder).answer_3.setChecked(false);
                        ((MyViewHolder) holder).answer_4.setChecked(false);
                        value = ((MyViewHolder) holder).answer_1.getText().toString();
                        answers.set(position, ((MyViewHolder) holder).answer_1.getText().toString());
                    }
                }
            });
            ((MyViewHolder) holder).answer_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (((MyViewHolder) holder).answer_2.isChecked()) {
                        ((MyViewHolder) holder).answer_1.setChecked(false);
                        ((MyViewHolder) holder).answer_3.setChecked(false);
                        ((MyViewHolder) holder).answer_4.setChecked(false);
                        value = ((MyViewHolder) holder).answer_2.getText().toString();
                        answers.set(position, ((MyViewHolder) holder).answer_2.getText().toString());
                    }
                }
            });
            ((MyViewHolder) holder).answer_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (((MyViewHolder) holder).answer_3.isChecked()) {
                        ((MyViewHolder) holder).answer_1.setChecked(false);
                        ((MyViewHolder) holder).answer_2.setChecked(false);
                        ((MyViewHolder) holder).answer_4.setChecked(false);
                        value = ((MyViewHolder) holder).answer_3.getText().toString();
                        answers.set(position, ((MyViewHolder) holder).answer_3.getText().toString());
                    }
                }
            });
            ((MyViewHolder) holder).answer_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (((MyViewHolder) holder).answer_4.isChecked()) {
                        ((MyViewHolder) holder).answer_1.setChecked(false);
                        ((MyViewHolder) holder).answer_2.setChecked(false);
                        ((MyViewHolder) holder).answer_3.setChecked(false);
                        value = ((MyViewHolder) holder).answer_4.getText().toString();
                        answers.set(position, ((MyViewHolder) holder).answer_4.getText().toString());
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

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    activity.finish();
                }
            });
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
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        } else {
            ((MyImageHolder) holder).fisrtIndexImage.setImageResource(image_url);
            ((MyImageHolder) holder).name.setText(name);
        }

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
            answer_1.setTypeface(quickSand);
            answer_2.setTypeface(quickSand);
            answer_3.setTypeface(quickSand);
            answer_4.setTypeface(quickSand);


        }
    }


    public class MyImageHolder extends RecyclerView.ViewHolder {
        public ImageView fisrtIndexImage;
        public TextView name;

        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public MyImageHolder(View view) {
            super(view);
            fisrtIndexImage = (ImageView) view.findViewById(R.id.index);
            name = (TextView) view.findViewById(R.id.main_evaluation_textview);
            name.setTypeface(quickSand);
        }
    }

}