package mahmoudvic.org.phomunity.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mahmoudvic.org.phomunity.R;

public class RegisterLevelPopUp extends RecyclerView.Adapter {

    private ArrayList<String> levels = new ArrayList<>();
    private Context context;
    private Dialog dialog;
    private TextView level;
    public RegisterLevelPopUp(Context context, ArrayList<String> levels, Dialog dialog, TextView level){
        this.context = context;
        this.levels = levels;
        this.dialog = dialog;
        this.level = level;
    }
    @NonNull
    @Override
    public RegisterLevelPopUp.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.register_level_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RegisterLevelPopUp.MyViewHolder vh = new RegisterLevelPopUp.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).levelTextView.setText(levels.get(position));
        ((RegisterLevelPopUp.MyViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level.setText(levels.get(position));
                SharedPreferences.Editor editor = context.getSharedPreferences("level", Context.MODE_PRIVATE).edit();
                editor.putString("val", levels.get(position));
                editor.commit();
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView levelTextView ;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            levelTextView = (TextView) itemView.findViewById(R.id.register_level_popup_layout);
            levelTextView.setTypeface(quickSand);
        }
    }

}
