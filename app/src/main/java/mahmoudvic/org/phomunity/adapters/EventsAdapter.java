package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mahmoudvic.org.phomunity.EventDetailsActiivty;
import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.EventPOJO;
import mahmoudvic.org.phomunity.util.DateUtil;
import mahmoudvic.org.phomunity.util.ImageUtil;

public class EventsAdapter extends RecyclerView.Adapter {

    private List<EventPOJO> eventPOJOS;
    private Context mContext;


    public EventsAdapter(Context mContext, List<EventPOJO> eventPOJOS) {
        this.eventPOJOS = eventPOJOS;
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
            return new EventsAdapter.MyImageHolder(itemView);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.events_item, parent, false);
            return new EventsAdapter.MyViewHolder(itemView);

        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof EventsAdapter.MyViewHolder) {
            final EventPOJO eventPOJO = eventPOJOS.get(position);
            ((MyViewHolder) holder).eventName.setText(eventPOJO.getName());
            Typeface quickSand = Typeface.createFromAsset(mContext.getAssets(), "font/Quicksand-Bold.ttf");
            ((MyViewHolder) holder).eventName.setTypeface(quickSand);

            ((MyViewHolder) holder).eventAddress.setText(eventPOJO.getAddress());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtil.convertToEntityAttribute(eventPOJO.getDateFrom()));

            SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd");
            String date = formatter.format(calendar.getTime());
            String dates[] = date.split("-");

            String dayName = DateUtil.getDayName(calendar.get(Calendar.DAY_OF_WEEK));
            ((MyViewHolder) holder).eventDayName.setText(dayName);

            try {
                calendar.setTime(new SimpleDateFormat("HH:mm:ss").parse(eventPOJO.getTimeFrom()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            formatter = new SimpleDateFormat("hh aa");
            date = formatter.format(calendar.getTime());
            ((MyViewHolder) holder).eventTime.setText(date);

            ((MyViewHolder) holder).eventMonth.setText(dates[0]);
            ((MyViewHolder) holder).eventDayNum.setText(dates[1]);

            String imageFileName = "";
            if (eventPOJO.getDocument() != null) {
                imageFileName = eventPOJO.getDocument();
                if (!imageFileName.equals("")) {
                    Glide.with(mContext)
                            .asBitmap()
                            .load(ImageUtil.BASE_IMAGE_URL+eventPOJO.getDocument())
                            .into(((MyViewHolder) holder).eventImage);
                }
            }



            ((MyViewHolder) holder).eventLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent=new Intent(mContext, EventDetailsActiivty.class);
                    intent.putExtra("eventId", eventPOJO.getId());
                    mContext.startActivity(intent);
                }
            });

        }else {
            ((EventsAdapter.MyImageHolder) holder).fisrtIndexImage.setImageResource(R.drawable.event_header);
        }


    }

    @Override
    public int getItemCount() {
        return eventPOJOS.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageView eventImage;
        public TextView eventName;
        public TextView eventDayNum;
        public TextView eventMonth;
        public TextView eventDayName;
        public TextView eventTime;
        public TextView eventAddress;
        public LinearLayout eventLayout;


        public MyViewHolder(View view) {
            super(view);
            eventImage = (ImageView) view.findViewById(R.id.event_image);
            eventName = (TextView) view.findViewById(R.id.event_name);
            eventDayNum = (TextView) view.findViewById(R.id.event_day_number);
            eventMonth = (TextView) view.findViewById(R.id.event_month);
            eventDayName = (TextView) view.findViewById(R.id.event_day_name);
            eventTime = (TextView) view.findViewById(R.id.event_time);
            eventAddress = (TextView) view.findViewById(R.id.event_address);

            eventLayout = (LinearLayout) view.findViewById(R.id.event_layout);

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
