package com.example.hospitalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChildActivity extends AppCompatActivity implements View.OnClickListener {
    private Reaction reaction;
    private Button btnSend;
    private Intent intent1;
    private String username;
    private int seekBarProgress;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        RelativeLayout relativeLayout = findViewById(R.id.relative_layout);
        btnSend=findViewById(R.id.btnSend);
        SeekBar seekBar = findViewById(R.id.seekBar);
        ImageView imageView = findViewById(R.id.Emoji);
        TextView test=findViewById(R.id.test);
        final TextView seekBarValue = findViewById(R.id.seekBarValue);
        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
        String password = intent.getStringExtra("PASSWORD");
        reaction = (Reaction) getIntent().getSerializableExtra("REACTION_OBJECT");
        btnSend.setOnClickListener(this);
        if (reaction==null)
        {
            reaction = new Reaction(username);
        }
        if (username!=null)
        {
        if(username.charAt(username.length() - 1)=='1')
            test.setText("כמה כואב לך?");
        if(username.charAt(username.length() - 1)=='2')
            test.setText("כמה לדעתך כואב לבינך/ביתך?");
        if(username.charAt(username.length() - 1)=='3')
            test.setText("כמה לדעתך כואב למטופל/ת?");
        if(username.charAt(username.length() - 1)=='4')
            test.setText("כמה לדעתך כואב למטופל/ת?");}
        else
        {
            test.setText("כמה לדעתך כואב למטופל/ת?");
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setTextColor(Color.WHITE);
                seekBarValue.setTextSize(20);
                seekBarValue.setText(" " + progress);
                seekBarProgress=progress;
                if (progress==0)
                {
                    relativeLayout.setBackgroundColor(Color.parseColor("#6c92a9"));
                } else if (progress==1) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#557f97"));
                    imageView.setImageResource(R.drawable.one);
                } else if (progress==2) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#54717f"));
                } else if (progress==3) {
                    imageView.setImageResource(R.drawable.two);
                    relativeLayout.setBackgroundColor(Color.parseColor("#827553"));
                } else if (progress==4) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#a17938"));
                } else if (progress==5) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#b1792e"));
                    imageView.setImageResource(R.drawable.three);
                } else if (progress==6) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#b5642e"));
                } else if (progress==7) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#b2572c"));
                    imageView.setImageResource(R.drawable.four);
                } else if (progress==8) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#b44a34"));
                } else if (progress==9) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#a2362f"));
                } else if (progress==10) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#99282a"));
                    imageView.setImageResource(R.drawable.five);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Called when the user starts touching the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Called when the user stops touching the SeekBar
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view==btnSend)
        {
            if (reaction.getCode().charAt(reaction.getCode().length() - 1)=='3'||reaction.getCode().charAt(reaction.getCode().length() - 1)=='4') {
                intent1 = new Intent(ChildActivity.this, nurseMainPage.class);
                reaction.setRating(seekBarProgress);
                intent1.putExtra("REACTION_OBJECT", reaction);
                startActivity(intent1);
            }
            else if (reaction.getCode().charAt(reaction.getCode().length() - 1)=='2'||reaction.getCode().charAt(reaction.getCode().length() - 1)=='1')
            {
                if(seekBarProgress>0)
                {
                    intent1=new Intent(ChildActivity.this,finish_rating.class);
                    startActivity(intent1);
                }
            }
        }
    }
}