package mahmoudvic.org.phomunity.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mahmoudvic.org.phomunity.CourseDetailsActivity;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.CoursePOJO;
import mahmoudvic.org.phomunity.util.DateUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;
import me.anwarshahriar.calligrapher.Calligrapher;

public class CoursesAdapter extends RecyclerView.Adapter {

    private List<CoursePOJO> coursePOJOS;
    private Context mContext;


    public CoursesAdapter(Context mContext, List<CoursePOJO> coursePOJOS) {
        this.coursePOJOS = coursePOJOS;
        this.mContext = mContext;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == 1) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_event_header, parent, false);
            return new CoursesAdapter.MyImageHolder(itemView);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.events_item, parent, false);

//            FontUtil.setFont(mContext);
            return new CoursesAdapter.MyViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CoursesAdapter.MyViewHolder) {
            MyViewHolder basicViewHolder = (MyViewHolder) holder;
            final CoursePOJO coursePOJO = coursePOJOS.get(position);

            Glide.with(mContext)
                    .asBitmap()
//                .load(eventPOJO.getSamplesSecondShooterPOJOS().get(0).getImageFileName())
                    .load(ImageUtil.BASE_IMAGE_URL.concat(coursePOJO.getImages().get(0).getImageFilename()))
                    .into(basicViewHolder.courseImage);
            basicViewHolder.courseName.setText(coursePOJO.getName());

            basicViewHolder.courseAddress.setVisibility(View.GONE);
//            basicViewHolder.courseTime.setVisibility(View.GONE);
            basicViewHolder.courseDayName.setVisibility(View.INVISIBLE);
//            basicViewHolder.timeLayout.setVisibility(View.GONE);
            basicViewHolder.timeAt.setVisibility(View.GONE);
            basicViewHolder.addressLayout.setVisibility(View.GONE);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtil.convertToEntityAttribute(coursePOJO.getStartDate()));

            SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd");
            String date = formatter.format(calendar.getTime());
            String dates[] = date.split("-");

            String dayName = DateUtil.getDayName(calendar.get(Calendar.DAY_OF_WEEK));
            basicViewHolder.courseTime.setText(dayName);
            Log.d("dayName", dayName);
//            basicViewHolder.courseDayName.setText(dayName);
            basicViewHolder.courseMonth.setText(dates[0]);
            basicViewHolder.courseDayNum.setText(dates[1]);

//            try {
//                calendar.setTime(new SimpleDateFormat("HH:mm:ss").parse(coursePOJO.getTimeFrom()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            formatter = new SimpleDateFormat("hh aa");
//            date = formatter.format(calendar.getTime());
//            ((MyViewHolder) holder).courseTime.setText(date);


            basicViewHolder.courseLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CourseDetailsActivity.class);
                    intent.putExtra("courseId", coursePOJO.getId());
                    mContext.startActivity(intent);
                }
            });

        } else {
            //TODO change the header image from event image to course image
            ((CoursesAdapter.MyImageHolder) holder).fisrtIndexImage.setImageResource(R.drawable.course_header);
        }


    }

    @Override
    public int getItemCount() {
        return coursePOJOS.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageView courseImage;
        public TextView courseName;
        public TextView courseDayNum;
        public TextView courseMonth;
        public TextView courseDayName;
        public TextView courseTime;
        public TextView courseAddress;
        public LinearLayout courseLayout;
        public LinearLayout timeLayout;
        public TextView timeAt;
        public LinearLayout addressLayout;

//        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");


        public MyViewHolder(View view) {

            super(view);
//            Calligrapher calligrapher = new Calligrapher(mContext);
//            calligrapher.setFont((Activity) mContext, "font/Quicksand-Regular.ttf", true);
            courseImage = (ImageView) view.findViewById(R.id.event_image);
            courseName = (TextView) view.findViewById(R.id.event_name);
            courseDayNum = (TextView) view.findViewById(R.id.event_day_number);
            courseMonth = (TextView) view.findViewById(R.id.event_month);
            courseDayName = (TextView) view.findViewById(R.id.event_day_name);
            courseTime = (TextView) view.findViewById(R.id.event_time);
            courseAddress = (TextView) view.findViewById(R.id.event_address);
            courseLayout = (LinearLayout) view.findViewById(R.id.event_layout);
            timeLayout = (LinearLayout) view.findViewById(R.id.event_time_layout);
            timeAt = (TextView) view.findViewById(R.id.event_at);
            addressLayout = (LinearLayout) view.findViewById(R.id.event_address_layout);


//            courseName.setTypeface(quickSand);
//            courseDayNum.setTypeface(quickSand);
//            courseMonth.setTypeface(quickSand);
//            courseDayName.setTypeface(quickSand);
//            courseTime.setTypeface(quickSand);
//            courseAddress.setTypeface(quickSand);
//            timeAt.setTypeface(quickSand);

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
