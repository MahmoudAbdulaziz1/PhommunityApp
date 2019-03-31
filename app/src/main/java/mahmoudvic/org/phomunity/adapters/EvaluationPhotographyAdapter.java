package mahmoudvic.org.phomunity.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mahmoudvic.org.phomunity.MainEvaluationActivity;
import mahmoudvic.org.phomunity.R;

public class EvaluationPhotographyAdapter extends RecyclerView.Adapter<EvaluationPhotographyAdapter.MyViewHolder> {

    ArrayList<String> answers;
    private Context context;
    private ArrayList<String> questions;
    private ArrayList<String> answersOne;
    private ArrayList<String> answersTwo;
    private ArrayList<String> answersThree;
    private ArrayList<String> answeersFour;
    private ArrayList<String> answerFour;
    private ArrayList<Integer> marks;
    private Button submit;
    private String value = null;
    private ImageView close;
    private TextView result;

    public EvaluationPhotographyAdapter(Context context, ArrayList questions, ArrayList answersOne, ArrayList answersTwo,
                                        ArrayList answersThree, ArrayList answerFour, ArrayList answeersFour, ArrayList marks,Button submit,
                                        ArrayList answers) {
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
    }

    @NonNull
    @Override
    public EvaluationPhotographyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photography_items, parent, false);
        return new EvaluationPhotographyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EvaluationPhotographyAdapter.MyViewHolder holder, final int position) {
        holder.questions.setText(questions.get(position));
        holder.answer_1.setText(answersOne.get(position));
        holder.answer_2.setText(answersTwo.get(position));
        if (answersThree.size() == 0){
            holder.answer_3.setVisibility(View.GONE);
        }else {
            holder.answer_3.setVisibility(View.VISIBLE);
            holder.answer_3.setText(answersThree.get(position));
        }

        if (answerFour.size()==0){
            holder.answer_4.setVisibility(View.GONE);
        }else {
            holder.answer_4.setVisibility(View.VISIBLE);
            holder.answer_4.setText(answerFour.get(position));
        }


        holder.answer_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.answer_1.isChecked()) {
                    holder.answer_2.setChecked(false);
                    holder.answer_3.setChecked(false);
                    holder.answer_4.setChecked(false);
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
                    answers.set(position, holder.answer_4.getText().toString());
                }
            }
        });


        final Dialog dialog = new Dialog(context);
        // Include dialog.xml file
        Typeface quickSand = Typeface.createFromAsset(context.getAssets(), "font/Quicksand-Bold.ttf");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_photography_popup);
        close = (ImageView)dialog.findViewById(R.id.photography_closeId);
        result = (TextView) dialog.findViewById(R.id.result_value);
        result.setTypeface(quickSand);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //answers.add(position, value);
                int fullResult = 0;
                int yourResult = 0;
                for (int i=0 ;i<answeersFour.size();i++){
                    fullResult += marks.get(i);
                    if (answers.get(i).equals(answeersFour.get(i))){
                        yourResult += marks.get(i);
                    }
                }
                result.setText(yourResult+" / "+fullResult);
                Log.d("YOUR RESULT", yourResult+" / "+fullResult);
                dialog.show();

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
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");



        public MyViewHolder(View itemView) {
            super(itemView);
            questions = itemView.findViewById(R.id.question);
            answer_1 = itemView.findViewById(R.id.answer1);
            answer_2 = itemView.findViewById(R.id.answer2);
            answer_3 = itemView.findViewById(R.id.answer3);
            answer_4 = itemView.findViewById(R.id.answer4);
            questions.setTypeface(quickSand);
        }
    }
}
