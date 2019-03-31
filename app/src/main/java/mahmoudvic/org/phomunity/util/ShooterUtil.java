package mahmoudvic.org.phomunity.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ShooterUtil {

    public void handleDefaultRequest(RecyclerView.ViewHolder holder, final Context mContext, final boolean isAssistant) {

        // Create custom dialog object
        final Dialog dialog = new Dialog(mContext);
        // Include dialog.xml file
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.request_info_dialog);
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.90);
        dialog.getWindow().setLayout(screenWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        // Set dialog title
        ImageView closeId = (ImageView) dialog.findViewById(R.id.closeId);
        // set values for custom dialog components - text, image and button
        final EditText messageEditText = (EditText) dialog.findViewById(R.id.message);

        dialog.show();

        Button sendMessage = (Button) dialog.findViewById(R.id.send_message);

        // if decline button is clicked, close the custom dialog

        closeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                if (!TextUtils.isEmpty(message)) {

                    final ProgressDialog progressDialog = new ProgressDialog(mContext);
                    progressDialog.setCancelable(false); // set cancelable to false
                    progressDialog.setMessage("Please Wait"); // set message
                    progressDialog.show(); // show progress dialog
                    Call<Integer> call = null;
                    if (isAssistant) {
                        call = (Api.getClient().orderAssistant(message, mContext.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0)));
                    } else {
                        call = (Api.getClient().orderSecondShooter(message, mContext.getSharedPreferences("remember", MODE_PRIVATE).getInt("id", 0)));
                    }

                    call.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {

                            if (response.isSuccessful()) {
                                //signUpResponsesData = response.body();
                                if (response.body() != null) {
                                    if (response.body().intValue() == 1) {
                                        Toast.makeText(mContext, "Success", Toast.LENGTH_LONG).show();
                                        Log.d("ok", response.toString());
                                        // Close dialog
                                        dialog.dismiss();
//                                                    finish();
                                    } else {
                                        Toast.makeText(mContext, "Check your connection", Toast.LENGTH_LONG).show();
                                    }


                                } else {
                                    Toast.makeText(mContext, " Response Failure", Toast.LENGTH_SHORT).show();
                                    Log.d("response fail", "Boom");
                                }
                            } else {
                                Toast.makeText(mContext, "Empty Response", Toast.LENGTH_SHORT).show();
                                Log.d("fail", response.toString());

                            }
                            progressDialog.dismiss();

                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Toast.makeText(mContext, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                            Log.d("response", t.getStackTrace().toString() + " " + call.toString());
                            progressDialog.dismiss();

                        }
                    });

                } else {
                    messageEditText.setError("enter  message");
                }

            }
        });


    }

    public void handleOtherRequest(String status, int orderId, final Context mContext, int userId) {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        Call<String> call = null;

        switch (status) {
            case "rejected":
                call = (Api.getClient().orderRejectAssistant(orderId));
                break;
            case "reviewed":
                call = (Api.getClient().orderReviewAssistant(orderId));
                break;
            case "accepted":
                call = (Api.getClient().orderAcceptAssistant(orderId));
                break;
            case "pending":
                call = (Api.getClient().orderReviewAssistant(orderId));
                break;
            case "cancelled":
                call = (Api.getClient().orderCancelAssistant(orderId, userId));
                break;
        }

        try {

            call.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            String sucess = response.body();
                            if (sucess.equals("success")) {
                                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "fail", Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(mContext, " Response Failure", Toast.LENGTH_SHORT).show();
                            Log.d("response fail", "Boom");
                        }
                    } else {
                        Toast.makeText(mContext, "Empty Response", Toast.LENGTH_SHORT).show();
                        Log.d("fail", response.toString());

                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(mContext, "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
