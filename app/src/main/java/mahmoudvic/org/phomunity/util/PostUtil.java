package mahmoudvic.org.phomunity.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mahmoudvic.org.phomunity.APIClass.Api;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.adapters.PostAdapter;
import mahmoudvic.org.phomunity.adapters.PostDetailAdapter;
import mahmoudvic.org.phomunity.pojo.CommentPOJO;
import mahmoudvic.org.phomunity.pojo.PostPOJO;
import mahmoudvic.org.phomunity.pojo.ReturnedCommentPOJO;
import mahmoudvic.org.phomunity.pojo.ReturnedLikePOJO;
import mahmoudvic.org.phomunity.pojo.ReturnedPostPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostUtil {

    private static final String PLEASE_WAIT = "Please Wait";
    private static final String CHECK_CONN = "Check your Internet Connection";
    private static final String RESPONSE_FAIL = "Response Failure";
    private static final String RESPONSE_EMPTY = "Empty Response";

    public void storeLike(final Context mContext, int postId, int userId, final TextView textView, final TextView likeTextview) {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage(PLEASE_WAIT); // set message
        progressDialog.show(); // show progress dialog
        (Api.getClient().storeLike(postId, userId)).enqueue(new Callback<ReturnedLikePOJO>() {
            @Override
            public void onResponse(Call<ReturnedLikePOJO> call, Response<ReturnedLikePOJO> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ReturnedLikePOJO returnedLikePOJO = response.body();
                        textView.setText(String.valueOf(returnedLikePOJO.getCount()));
                        if (returnedLikePOJO.getAction().equals("deslike")) {
                            likeTextview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unlike, 0, 0, 0);
                        } else {
                            likeTextview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_icon, 0, 0, 0);
                        }


                    } else {
                        Toast.makeText(mContext, RESPONSE_FAIL, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, RESPONSE_EMPTY, Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ReturnedLikePOJO> call, Throwable t) {
                Toast.makeText(mContext, CHECK_CONN, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

    }

    public void storeComment(final Context mContext, int postId, int userId, String body, final List<CommentPOJO> commentPOJOS, final PostDetailAdapter postDetailAdapter) {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage(PLEASE_WAIT); // set message
        progressDialog.show(); // show progress dialog
        (Api.getClient().storeComment(postId, userId, body)).enqueue(new Callback<ReturnedCommentPOJO>() {
            @Override
            public void onResponse(Call<ReturnedCommentPOJO> call, Response<ReturnedCommentPOJO> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        progressDialog.dismiss();
                        ReturnedCommentPOJO returnedCommentPOJO = response.body();
                        commentPOJOS.add(0, returnedCommentPOJO.getCommentPOJOS().get(0));
                        postDetailAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(mContext, RESPONSE_FAIL, Toast.LENGTH_SHORT).show();
                        Log.d("response fail", "Boom");
                    }
                } else {
                    Toast.makeText(mContext, RESPONSE_EMPTY, Toast.LENGTH_SHORT).show();
                    Log.d("fail", response.toString());

                }
            }

            @Override
            public void onFailure(Call<ReturnedCommentPOJO> call, Throwable t) {
                Toast.makeText(mContext, CHECK_CONN, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

    }

    public void storePost(final Context mContext, int groupId, int userId, String body, String image, final PostAdapter postAdapter, final List<PostPOJO> postPOJOS) {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage(PLEASE_WAIT); // set message
        progressDialog.show(); // show progress dialog
        List<String> imageList = new ArrayList();

        imageList.add(image);

        (Api.getClient().storePost(groupId, userId, body, imageList)).enqueue(new Callback<ReturnedPostPOJO>() {

            @Override
            public void onResponse(Call<ReturnedPostPOJO> call, Response<ReturnedPostPOJO> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ReturnedPostPOJO returnedPost = response.body();
                        postPOJOS.add(0, returnedPost.getPostPOJOS().get(0));
                        postAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(mContext, RESPONSE_FAIL, Toast.LENGTH_SHORT).show();
                        Log.d("response fail", "Boom");
                    }
                } else {
                    Toast.makeText(mContext, RESPONSE_EMPTY, Toast.LENGTH_SHORT).show();
                    Log.d("fail", response.toString());

                }
            }


            @Override
            public void onFailure(Call<ReturnedPostPOJO> call, Throwable t) {
                Toast.makeText(mContext, CHECK_CONN, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

    }

    public String getDateDiff(String stringDate) {


        String date = stringDate.split("\\s+")[0];

        StringBuilder stringBuilder = new StringBuilder();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date currentDate = new Date();

        try {
            d1 = format.parse(stringDate);
            String newDate = DateUtil.convertToString(d1);
            DateTime dt1 = new DateTime(d1);
            DateTime currentDateTime = new DateTime(currentDate);

            int days = Days.daysBetween(dt1, currentDateTime).getDays();
            int hours = Hours.hoursBetween(dt1, currentDateTime).getHours() % 24;
            int minutes = Minutes.minutesBetween(dt1, currentDateTime).getMinutes() % 60;

            stringBuilder.append(newDate);

            if (days > 0) {
                stringBuilder.append(" / ");
                stringBuilder.append(days).append(" d");
            }
            if (days > 0 && hours > 0) {
                stringBuilder.append(" - ");
            }
            if (days <= 0 && hours > 0) {
                stringBuilder.append(" / ");
            }
            if (hours > 0) {
                stringBuilder.append(hours).append(" h");
            }
            if (days <= 0 && hours <= 0 && minutes > 0) {
                stringBuilder.append(" / ");
                stringBuilder.append(minutes).append(" m");
            }
            if (days <= 0 && hours <= 0 && minutes == 0) {
                stringBuilder.append(" / ");
                stringBuilder.append("now");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }


    }
}
