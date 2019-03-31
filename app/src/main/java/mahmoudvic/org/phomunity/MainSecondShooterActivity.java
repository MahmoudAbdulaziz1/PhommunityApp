package mahmoudvic.org.phomunity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mahmoudvic.org.phomunity.util.ToolbarUtil;
import me.anwarshahriar.calligrapher.Calligrapher;

public class MainSecondShooterActivity extends AppCompatActivity {


    private TextView assistantTextview;
    private TextView secondShooterTextview;
    private TextView registerAssistantTextview;
    private TextView registerShooterTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistant_secondshooter_main);
        ToolbarLayoutFragment toolbarLayoutFragment = new ToolbarLayoutFragment();
        ToolbarUtil.setFragment(MainSecondShooterActivity.this, toolbarLayoutFragment);

        assistantTextview = findViewById(R.id.assistant_textView);
        secondShooterTextview = findViewById(R.id.seconshooter_textview);
        registerAssistantTextview = findViewById(R.id.register_assistant_textview);
        registerShooterTextview = findViewById(R.id.register_secondshooter_textview);

        assistantTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSecondShooterActivity.this, LookingAssiatantActivity.class);
                startActivity(i);
            }
        });
        secondShooterTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSecondShooterActivity.this, LookingSecondShooterActivity.class);
                startActivity(i);
            }
        });
        registerAssistantTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSecondShooterActivity.this, AssistantRegisterActivity.class);
                startActivity(i);
            }
        });
        registerShooterTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSecondShooterActivity.this, SecondShooterRegisterActivity.class);
                startActivity(i);
            }
        });

        final Typeface quickSand = Typeface.createFromAsset(getBaseContext().getAssets(), "font/Quicksand-Bold.ttf");
        registerAssistantTextview.setTypeface(quickSand);
        registerShooterTextview.setTypeface(quickSand);
        secondShooterTextview.setTypeface(quickSand);
        assistantTextview.setTypeface(quickSand);
    }


}
