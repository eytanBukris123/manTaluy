package com.example.mantaluy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    ImageView iv;
    int level = 1 , counter = 0;
    EditText et;
    String word;
    boolean haveAWord = false;
    LinearLayout layout;
    LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutParams = new LinearLayout.LayoutParams(100, 100);
        layout = (LinearLayout)findViewById(R.id.layout);
        button = findViewById(R.id.button);
        iv = findViewById(R.id.imageView);
        et = findViewById(R.id.editText);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==button){
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            if(!haveAWord) {
                word = et.getText().toString();
                et.setHint("write your guess");
                haveAWord = true;
                for (int i = 0; i < word.length(); i++) {

                    TextView line = new TextView(this);
                    line.setLayoutParams(layoutParams);
                    line.setText("___");
                    layout.addView(line);
                    et.setText("");
                }
            }
            else if(haveAWord && et.getText()!=null){
                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == et.getText().charAt(0)){
                        counter++;
                    }
                }
                if(counter == 0) {
                    switch (level) {
                        case 1:
                            iv.setImageResource(R.drawable.level2);
                            level++;
                            break;
                        case 2:
                            iv.setImageResource(R.drawable.level3);
                            level++;
                            break;
                        case 3:
                            iv.setImageResource(R.drawable.level4);
                            level++;
                            break;
                        case 4:
                            iv.setImageResource(R.drawable.level5);
                            level++;
                            break;
                        case 5:
                            iv.setImageResource(R.drawable.level6);
                            level++;
                            break;
                        case 6:
                            iv.setImageResource(R.drawable.level7);
                            level++;
                            break;
                        case 7:
                            iv.setImageResource(R.drawable.level1);
                            level = 1;
                            break;
                    }
                    et.setText("");
                }
            }
        }
    }
}
