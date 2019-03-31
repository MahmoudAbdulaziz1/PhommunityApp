package mahmoudvic.org.phomunity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.adapters.EvaluationLightingAdapter;
import mahmoudvic.org.phomunity.pojo.AnswersPOJO;
import mahmoudvic.org.phomunity.pojo.EvaluationImagePOJO;
import mahmoudvic.org.phomunity.pojo.PhotographyPOJO;
import mahmoudvic.org.phomunity.pojo.QuestionsPOJO;
import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluationLightingActivity extends AppCompatActivity {


    private ArrayList<String> questions = new ArrayList<String>();//(Arrays.asList("Question1  ..........?",
    //"Question2  ..........?", "Question3  ..........?", "Question4  ..........?", "Question5  ..........?",
    //"Question6  ..........?", "Question7  ..........?"));
    private ArrayList<String> answer1 = new ArrayList<>();//Arrays.asList("Answer1......", "Answer1......", "Answer1......", "Answer1......",
    //"Answer1......", "Answer1......", "Answer1......"));
    private ArrayList<String> answers2 = new ArrayList<>();//Arrays.asList("Answer1......", "Answer1......", "Answer1......", "Answer1......",
    //"Answer1......", "Answer1......", "Answer1......"));
    private ArrayList<String> answers3 = new ArrayList<>();//Arrays.asList("Answer1......", "Answer1......", "Answer1......", "Answer1......",
    //"Answer1......", "Answer1......", "Answer1......"));
    private ArrayList<String> answers4 = new ArrayList<>();//Arrays.asList("Answer1......", "Answer1......", "Answer1......", "Answer1......",
    //"Answer1......", "Answer1......", "Answer1......"));
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Integer> marks = new ArrayList<>();
    private ArrayList<String> answer1_isCorrect = new ArrayList<>();
    private TextView noQuestions;
    private List<String> images = new ArrayList<>();


    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_lighting);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(EvaluationLightingActivity.this, toolbarLayoutFragment);

        submit = (Button) findViewById(R.id.lighting_submit);
        noQuestions = (TextView) findViewById(R.id.lighting_no_question);

        Api.getClient().getPhotographyOuestions("lighting").enqueue(new Callback<PhotographyPOJO>() {
            @Override
            public void onResponse(Call<PhotographyPOJO> call, Response<PhotographyPOJO> response) {

                if (response != null && response.isSuccessful()) {
                    if (response.body() != null) {
                        List<QuestionsPOJO> questionsPOJOS = response.body().getQuestions();
                        if (questionsPOJOS.size() > 0) {
                            noQuestions.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            for (QuestionsPOJO pojos : questionsPOJOS) {
                                EvaluationImagePOJO po;
                                if (pojos.getImage() == null) {

                                } else {
                                    po = pojos.getImage();
                                    images.add(po.getImage_filename());
                                }
                                questionsPOJOS.add(pojos);
                                questions.add(pojos.getTitle());
                                marks.add(pojos.getMark());
                                List<AnswersPOJO> answersPOJOS = pojos.getAnswers();
                                answer1.add(answersPOJOS.get(0).getBody());
                                if (answersPOJOS.get(0).isRight()) {
                                    answer1_isCorrect.add(answersPOJOS.get(0).getBody());
                                } else if (answersPOJOS.get(1).isRight()) {
                                    answer1_isCorrect.add(answersPOJOS.get(1).getBody());
                                }

                                if (answersPOJOS.size() >= 3) {
                                    if (answersPOJOS.get(2).isRight()) {
                                        answer1_isCorrect.add(answersPOJOS.get(2).getBody());
                                    }
                                }


                                if (answersPOJOS.size() >= 4) {
                                    if (answersPOJOS.get(3).isRight()) {
                                        answer1_isCorrect.add(answersPOJOS.get(3).getBody());
                                    }
                                }

                                answers2.add(answersPOJOS.get(1).getBody());
                                //answer2_isCorrect.add(answersPOJOS.get(1).isRight());
                                answers3.add(answersPOJOS.get(2).getBody());
                                //answer3_isCorrect.add(answersPOJOS.get(2).isRight());
                                if (answersPOJOS.size() == 4) {
                                    answers4.add(answersPOJOS.get(3).getBody());
                                }

                            }
                            for (int i = 0; i < answers2.size(); i++) {
                                answers.add(i, "");
                            }


                            String category = "lighting";
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lighting_recycle);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView


                            questions.add(0, new String());
                            answer1.add(0, new String());
                            answers2.add(0, new String());
                            answers3.add(0, new String());
                            answers4.add(0, new String());
                            answer1_isCorrect.add(0, new String());
                            marks.add(0, new Integer(0));
                            answers.add(0, new String());
                            images.add(0, new String());
                            EvaluationLightingAdapter adapter = new EvaluationLightingAdapter(EvaluationLightingActivity.this, questions, answer1,
                                    answers2, answers3, answers4, answer1_isCorrect, marks, submit, answers, category, images,
                                    R.drawable.eva_icon, "Lighting", EvaluationLightingActivity.this);


//                        EvaluationvideographyAdapter adapter = new EvaluationvideographyAdapter(EvaluationLightingActivity.this, questions, answer1,
//                                answers2, answers3, answers4, answer1_isCorrect, marks, submit, answers, category, images);
                            recyclerView.setAdapter(adapter);

                            Log.d("true", response.body().getQuestions().get(0).getTitle());
                        } else {
                            noQuestions.setVisibility(View.VISIBLE);
                            submit.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(EvaluationLightingActivity.this, R.string.response_empty, Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(EvaluationLightingActivity.this, R.string.response_failure, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PhotographyPOJO> call, Throwable t) {
                Toast.makeText(EvaluationLightingActivity.this, R.string.connection_failure, Toast.LENGTH_LONG).show();

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
