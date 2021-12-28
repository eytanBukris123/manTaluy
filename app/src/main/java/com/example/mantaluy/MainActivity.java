package com.example.mantaluy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button EnterBtn;
    ImageView iv;
    int level = 1 , counter = 0;
    EditText et;
    String word;
    boolean haveAWord = false;
    LinearLayout layout;
    LinearLayout.LayoutParams layoutParams;
    TextView[] lines = new TextView[10];
    TextView victoryText;
    int victory = 0;
    Button RestartBtn;
    TextView lettersStorage;
    String lettersUsed = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutParams = new LinearLayout.LayoutParams(50, 100);
        layout = (LinearLayout)findViewById(R.id.layout);
        EnterBtn = findViewById(R.id.button);
        iv = findViewById(R.id.imageView);
        et = findViewById(R.id.editText);
        lettersStorage = findViewById(R.id.lettersStorage);
        victoryText = findViewById(R.id.victoryText);
        RestartBtn = findViewById(R.id.RestartButton);
        RestartBtn.setVisibility(View.INVISIBLE);
        EnterBtn.setOnClickListener(this);
        RestartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v== EnterBtn){
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            if(!haveAWord) {
                word = et.getText().toString();
                et.setHint("write your guess");
                haveAWord = true;
                for (int i = word.length() -1; i >= 0; i--) {
                    lines[i] = new TextView(this);
                    lines[i].setLayoutParams(layoutParams);
                    lines[i].setText("___");
                    lines[i].setTextSize(20);
                    layout.addView(lines[i]);
                    et.setText("");
                }
            }
            else if(haveAWord && !et.getText().toString().equals("")){
                lettersUsed += et.getText().charAt(0) + ", ";
                lettersStorage.setText(lettersUsed);
                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == et.getText().charAt(0)){
                        victory = 0;
                        counter++;
                        lines[i].setText(et.getText().toString());
                        for(int j = 0; j < word.length(); j++){
                            if(lines[j].getText().toString() == "___"){
                                victory++;
                            }
                        }
                        if(victory == 0){
                            victoryText.setText("you won!!!!!");
                            RestartBtn.setVisibility(View.VISIBLE);
                            et.setEnabled(false);
                            EnterBtn.setEnabled(false);
                        }
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
                            victoryText.setText("you loser!!!!!");
                            RestartBtn.setVisibility(View.VISIBLE);
                            et.setEnabled(false);
                            EnterBtn.setEnabled(false);
                            break;
                    }
                }
                et.setText("");
                counter = 0;
            }
        }
        if(v==RestartBtn){
            for(int i = 0; i < lines.length; i++){
                lines[i] = null;
                layout.removeAllViews();
            }
                victoryText.setText("Man Taluy!!!");
                RestartBtn.setVisibility(View.INVISIBLE);
                et.setHint("write a word");
                haveAWord = false;
                iv.setImageResource(R.drawable.level1);
                level = 1;
                victory = 0;
                et.setEnabled(true);
                EnterBtn.setEnabled(true);
                lettersUsed = "";
                lettersStorage.setText(lettersUsed);
        }
    }
}
