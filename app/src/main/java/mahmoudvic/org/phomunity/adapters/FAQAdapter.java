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

import java.util.List;

import mahmoudvic.org.phomunity.R;
import mahmoudvic.org.phomunity.pojo.FaqPOJO;

public class FAQAdapter extends RecyclerView.Adapter {
//    private ArrayList<String> questionsList;
//    private ArrayList<String> answersList;
    private List<FaqPOJO> faqPOJOS;
    private Context context;

    public FAQAdapter(Context context, List<FaqPOJO> faqPOJOS) {
        this.faqPOJOS = faqPOJOS;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_faq, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("theLAstLine","QList size:"+faqPOJOS.size()+" position:"+position);
        MyViewHolder faqHolder = (MyViewHolder) holder;
        faqHolder.questionTxt.setText(faqPOJOS.get(position).getQuestion());
        faqHolder.answerTxt.setText(faqPOJOS.get(position).getAnswer());
        faqHolder.questionTxt.setTypeface(((MyViewHolder) holder).questionTxt.getTypeface(), Typeface.BOLD);
        if(position == faqPOJOS.size()-1){
            Log.d("lastQ","Gooooooooooooooooooooooooone");
            faqHolder.separator.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return faqPOJOS.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        public TextView questionTxt;
        public TextView answerTxt;
        public View separator;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");


        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            questionTxt = (TextView) itemView.findViewById(R.id.faq_question);
            answerTxt = (TextView) itemView.findViewById(R.id.faq_Answer);
            Log.d("the answer Return:","the Return-->"+(TextView) itemView.findViewById(R.id.faq_Answer));
            separator = (View) itemView.findViewById(R.id.faq_view);

            questionTxt.setTypeface(quickSand);
        }
    }


}
