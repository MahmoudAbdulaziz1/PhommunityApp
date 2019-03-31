package mahmoudvic.org.phomunity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.util.ConfirmMessageUtil;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotLoginJoinOurTeamActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 171;
    private EditText name;
    private EditText email;
    private EditText job;
    private EditText note;
    private TextView noFile;
    private LinearLayout noFileLayout;
    private Button uploadCV;
    private Button sendMessage;
    private Uri filePath;
    private String date;

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login_join_our_team);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "font/Quicksand-Regular.ttf", true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.not_login_join_our_team_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name = (EditText) findViewById(R.id.no_join_our_name_edit_text);
        email = (EditText) findViewById(R.id.no_join_our_email_edit_text);
        job = (EditText) findViewById(R.id.no_join_our_title_edit_text);
        note = (EditText) findViewById(R.id.no_join_our_note_edit_text);
        noFile = (TextView) findViewById(R.id.no_join_our_no_file_text);
        noFileLayout = (LinearLayout) findViewById(R.id.no_join_our_no_file_layout);
        uploadCV = (Button) findViewById(R.id.no_join_our_upload_cv_btn);
        sendMessage = (Button) findViewById(R.id.no_join_our_send_message);

        uploadCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImageStr();
            }
        });
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(name)) {
                    if (validateEmail(email)) {
                        if (validate(job)) {
                            joinTeam(name.getText().toString(),
                                    email.getText().toString(),
                                    job.getText().toString(),
                                    note.getText().toString(),
                                    date);
                        } else {
                            job.setError("Please Enter Your Job Title");
                        }
                    } else {
                        email.setError("Please Enter Your Email");
                    }
                } else {
                    name.setError("Please Insert Your Name");
                }
            }
        });

    }

    private void chooseImageStr() {
        Log.d("choose image", "done");
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        Log.d("choose image", "done2");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            Log.d("file", filePath.getPath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(new File(filePath.getPath()));
                Log.d("file", filePath.getPath());
                byte[] buf = new byte[1024];
                int n;
                while (-1 != (n = fis.read(buf)))
                    baos.write(buf, 0, n);

                byte[] videoBytes = baos.toByteArray();
                String result = Base64.encodeToString(videoBytes, 1);
                date = "data:application/pdf;base64," + result;
                Log.d("name", result + " ");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("name", e.getMessage() + " ");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("name", e.getMessage() + " ");
            }


            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //byte[] base64EncodedData = Base64.encodeBase64(loadFileAsBytesArray(filePath), isChunked);
            //image = encodeTobase64(bitmap);
//            star.setVisibility(View.GONE);
//            noFile.setVisibility(View.GONE);
            noFile.setText("Uploaded");
            //noFileLayout.setVisibility(View.GONE);

        }
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

    private void joinTeam(String nameSrt, String emailSrt, final String jobSrt, final String noteSrt, String cvSrt) {

        Api.getClient().joinOurTeam(nameSrt, emailSrt, jobSrt, noteSrt, cvSrt).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().intValue() == 1) {
                            Toast.makeText(getBaseContext(), "Message sent", Toast.LENGTH_LONG).show();
                            name.setText("");
                            email.setText("");
                            job.setText("");
                            note.setText("");
                            noFile.setText("No File Selected");
//                        noFileLayout.setVisibility(View.VISIBLE);
                            Log.d("if success", response.body().intValue() + " ");
                            LinearLayout layout = findViewById(R.id.join);
                            ConfirmMessageUtil.setMessage(layout, "Your Action finished", getBaseContext());
                        } else {
                            Toast.makeText(getBaseContext(), "Check your Connection ,Message Not Sent", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Response Failure", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getBaseContext(), "Empty Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(NotLoginJoinOurTeamActivity.this, "Check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public boolean validateEmail(EditText editText) {
        if (editText.getText().toString().trim().length() > 0 && isValidEmail(editText.getText().toString().trim())) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Enter Email");
        editText.requestFocus();
        return false;
    }
}