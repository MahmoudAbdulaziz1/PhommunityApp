package mahmoudvic.org.phomunity.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mahmoudvic.org.phomunity.R;
import me.anwarshahriar.calligrapher.Calligrapher;

public class NotificationAdapter extends RecyclerView.Adapter {

    private List<String> newNotify = new ArrayList<>();
    private List<String> earlierNotify = new ArrayList<>();
    private List<String> linksList = new ArrayList<>();
    private Context context;
    private List<String> allNotify = new ArrayList<>();
    private int count;
    private Activity activity;

    //title // message//link
    public NotificationAdapter(Context context, List newNotify, List earlierNotify, List linksList, int count, Activity activity) {
        this.context = context;
        this.newNotify = newNotify;
        this.earlierNotify = earlierNotify;
        this.linksList = linksList;
        this.count = count;
        this.activity = activity;
        Log.d("number", count + " s");
        //allNotify.addAll(newNotify);
        int t = count;
        if (count > 0) {
            allNotify.clear();
            allNotify.add(0, "New");
            linksList.add(0, "");
            //Collections.reverse(newNotify);
            //Collections.reverse(earlierNotify);
            //Collections.reverse(linksList);
            Log.d("add new", count + "  1s");
            for (int i = 0; i < t; i++) {
                Log.d("add new", "bbbbb");
                allNotify.add(earlierNotify.get(i).toString() + " " + newNotify.get(i).toString());
                Log.d("add new", earlierNotify.get(i).toString() + " test");
            }
            Log.d("add new", allNotify.size() + "  " + allNotify.get(allNotify.size() - 1) + " ");
            if (newNotify.size() > count) {
                allNotify.add(count + 1, "Earlier");
                linksList.add(count + 1, "");
            }
            for (int j = count + 1; j < earlierNotify.size(); j++) {
                if (earlierNotify.get(j).toString().isEmpty() || earlierNotify.get(j).toString().equals(null)) {
                    allNotify.add(newNotify.get(j).toString());
                } else {
                    allNotify.add(earlierNotify.get(j).toString());
                }
            }
            Log.d("number", allNotify.size() + " +sss");
        } else {
            allNotify.clear();
            allNotify.add(0, "Earlier");
            linksList.add(0, "");
            //Collections.reverse(newNotify);
            //Collections.reverse(earlierNotify);
            //Collections.reverse(linksList);
            for (int i = 0; i < earlierNotify.size(); i++) {
                if (earlierNotify.get(i).toString().isEmpty() || earlierNotify.get(i).toString().equals(null)) {
                    allNotify.add(newNotify.get(i).toString());
                } else {
                    allNotify.add(earlierNotify.get(i).toString());
                }
            }
            //allNotify.addAll(earlierNotify);
            Log.d("number", allNotify.size() + " ss");
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_recycler_elements, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        Log.d("numbertry", allNotify.size() + "  now");
        if (allNotify.get(position).trim().equals("New") || allNotify.get(position).trim().equals("Earlier")) {
            ((MyViewHolder) holder).notifyItem.setText(allNotify.get(position));
            Log.d("number", allNotify.get(position) + " ");
            Typeface quickSandBold = Typeface.createFromAsset(context.getAssets(), "font/Quicksand-Bold.ttf");
            ((MyViewHolder) holder).notifyItem.setGravity(Gravity.LEFT);
            ((MyViewHolder) holder).visible.setVisibility(View.GONE);
            ((MyViewHolder) holder).notifyItem.setTypeface(quickSandBold, Typeface.BOLD);

        } else {
            if (position == allNotify.size() - 1) {
                ((MyViewHolder) holder).notifyItem.setText(allNotify.get(position));
                ((MyViewHolder) holder).visible.setVisibility(View.GONE);
            } else {
                ((MyViewHolder) holder).notifyItem.setText(allNotify.get(position));
                ((MyViewHolder) holder).visible.setVisibility(View.VISIBLE);
            }
            ((MyViewHolder) holder).notifyItem.setText(allNotify.get(position));
            ((MyViewHolder) holder).visible.setVisibility(View.VISIBLE);

            ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (linksList.get(position).isEmpty() || linksList.get(position).equals(null)) {

                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(linksList.get(position)));
                            PackageManager packageManager = context.getPackageManager();
                            if (intent.resolveActivity(packageManager) != null) {
                                context.startActivity(intent);
                            } else {
                                Log.d("no to open", "No Intent available to handle action");
                            }
                            //context.startActivity(intent);
                        }
                        //Toast.makeText(context, linksList.get(position)+"", Toast.LENGTH_LONG).show();
                    } catch (NullPointerException e) {

                    }
                }
            });
        }
//

    }

    @Override
    public int getItemCount() {
        return allNotify.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView notifyItem;
        View visible;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public MyViewHolder(View itemView) {
            super(itemView);
            Calligrapher calligrapher = new Calligrapher(context);
            calligrapher.setFont(activity, "font/Quicksand-Regular.ttf", true);
            // get the reference of item view's
            notifyItem = (TextView) itemView.findViewById(R.id.notification_item);
            visible = (View) itemView.findViewById(R.id.notify_view);
            notifyItem.setTypeface(quickSand);
        }
    }
}
