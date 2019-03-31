package mahmoudvic.org.phomunity.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import mahmoudvic.org.phomunity.R;

public class RegisterInterestPopupAdapter extends RecyclerView.Adapter {

    ArrayList<String> multi = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
    private ArrayList<String> levels;
    private Context context;
    private Dialog dialog;
    private TextView interest;
    private Button getSelectedBtn;
    private StringBuilder interestValues = null;
    private ArrayList<Integer> saved = new ArrayList<>();

    public RegisterInterestPopupAdapter(Context context, ArrayList<String> levels, Dialog dialog, TextView level, Button getSelectedBtn) {
        this.context = context;
        this.levels = levels;
        this.dialog = dialog;
        this.interest = level;
        this.getSelectedBtn = getSelectedBtn;
    }

    @NonNull
    @Override
    public RegisterInterestPopupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.register_interest_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RegisterInterestPopupAdapter.MyViewHolder vh = new RegisterInterestPopupAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


        ((RegisterInterestPopupAdapter.MyViewHolder) holder).levelTextView.setText(levels.get(position));
        interestValues = new StringBuilder("");
        ((MyViewHolder) holder).levelTextView.setChecked(context.getSharedPreferences
                ("shared", Context.MODE_PRIVATE).getBoolean("check"+position,false));
        if (context.getSharedPreferences
                ("shared", Context.MODE_PRIVATE).getBoolean("check"+position,false)){
            interestValues.append(levels.get(position));
            multi.add(position, levels.get(position));
        }
        ((RegisterInterestPopupAdapter.MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (interestValues == null || interestValues.length() == 0 || interestValues.toString().equals("")) {
                    interestValues.insert(0, levels.get(position));
                    //Toast.makeText(context, interestValues.toString() + "yes", Toast.LENGTH_LONG).show();
                } else {
                    interestValues.append(" - ");
                    interestValues.append(levels.get(position));
                    //Toast.makeText(context, interestValues.toString() + "not", Toast.LENGTH_LONG).show();

                }

            }
        });

        ((MyViewHolder) holder).levelTextView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    multi.set(position, levels.get(position));
                    //saved.add(position);
                    context.getSharedPreferences("shared", Context.MODE_PRIVATE).
                            edit().putBoolean("check"+position,true).commit();
                   // Log.d("svaed", saved.size()+"");
                } else {
                    multi.set(position, "");
                    context.getSharedPreferences("shared", Context.MODE_PRIVATE).
                            edit().putBoolean("check"+position, false).commit();
                    //saved.remove(saved.indexOf(position));
                }
            }
        });


        getSelectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (multi.get(0).equals("") && multi.get(1).equals("") && multi.get(2).equals("") && multi.get(3).equals("") &&
                        multi.get(4).equals("") && multi.get(5).equals("") && multi.get(6).equals("") && multi.get(7).equals("") &&
                        multi.get(8).equals("") && multi.get(9).equals("") && multi.get(10).equals("") && multi.get(11).equals("") &&
                        multi.get(12).equals("") && multi.get(13).equals("") && multi.get(14).equals("")) {

                    //interest.setText(interestValues.toString());
                    Toast.makeText(context, "Please select your choice", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < multi.size(); i++) {
                        if (!(multi.get(i).equals(""))) {
                            if (interestValues.length() == 0 || interestValues.toString().equals(null) || interestValues.toString().equals("")) {
                                interestValues.append(multi.get(i));

                            } else {
                                interestValues.append(" - ");
                                interestValues.append(multi.get(i));
                            }
                        }
                    }
                    interest.setText(interestValues.toString());
                    //Toast.makeText(context, interestValues.toString(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        CheckBox levelTextView;
        Typeface quickSand = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Quicksand-Regular.ttf");

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            levelTextView = (CheckBox) itemView.findViewById(R.id.register_interest_popup_layout);
            levelTextView.setTypeface(quickSand);
        }
    }

}
