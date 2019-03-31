package mahmoudvic.org.phomunity.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mahmoudvic.org.phomunity.R;

public class HelpAdapter extends RecyclerView.Adapter {
    private ArrayList<String> questionsList;
    private ArrayList<Integer> idList;
    private Context context;

    public HelpAdapter(Context context, ArrayList<String> questionsList, ArrayList<Integer> idList){
        this.context = context;
        this.questionsList = questionsList;
        this.idList = idList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_help, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Typeface quickSandBold = Typeface.createFromAsset(context.getAssets(), "font/Quicksand-Bold.ttf");
        if (idList.get(position) == 1){
            Log.d("rrrrrrr","rrrrrrrrrrrr:"+idList.get(position));
            ((MyViewHolder)holder).questionTxt.setText(questionsList.get(position));
            ((MyViewHolder)holder).visible.setVisibility(View.VISIBLE);
        }else {
            Log.d("rrrrrrr","rrrrrrrrrrrr:"+idList.get(position));
            ((MyViewHolder)holder).questionTxt.setText(questionsList.get(position));
            ((MyViewHolder)holder).questionTxt.setTypeface(quickSandBold);
            ((MyViewHolder)holder).visible.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView questionTxt;
        View visible;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            questionTxt = (TextView) itemView.findViewById(R.id.help_question);
            visible = (View) itemView.findViewById(R.id.visible);
            questionTxt.setTypeface(quickSand);
        }
    }


}
