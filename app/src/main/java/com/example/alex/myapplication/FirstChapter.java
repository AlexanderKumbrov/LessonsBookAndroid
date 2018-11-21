package com.example.alex.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FirstChapter extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private static final String TAG ="FirstChapter";
    private static final String KEY_INDEX = "index";
    private Button mCheatButton;

    private Question [] mQuestionsBank = new Question[]{        // Вызов конструктор  Question
            new Question(R.string.question_oceans ,true),
            new Question(R.string.question_mideast , false),
            new Question(R.string.question_africa , false),
            new Question(R.string.question_americas , true),
            new Question(R.string.question_asia , true),
    };
            private int mCurrentIndex = 0 ;
            private int mCurrentIndexPrev = 1 - mCurrentIndex;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_chapter);
        Log.d(TAG , "onCreate(Bundle) called");
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        int question =mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX , 0);
        }
        updateQuestionNext();
        updateQuestionPrev();
    }

    public void cheat(View view){
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean answerIsTrue = mQuestionsBank [mCurrentIndex].isAnswerTrue();
               Intent intent = CheatActivity.newIntent(FirstChapter.this , answerIsTrue);
                startActivity(intent);
            }
        });
        updateQuestionPrev();
    }

    private void updateQuestionNext (){
        int question = mQuestionsBank [mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

    }
    private void updateQuestionPrev(){
        int question = mQuestionsBank [mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }



    public void previous_button(View view){
        mPreviousButton = (ImageButton)findViewById(R.id.previous_button);
        mCurrentIndex = (mCurrentIndex -1) % mQuestionsBank.length;
        updateQuestionPrev();
    }

    public void next_button (View view){
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mCurrentIndex = (mCurrentIndex +1) % mQuestionsBank.length;
        updateQuestionNext();
    }

    private void checkAnswer (boolean userPressedTrue){
                boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();
                int messageResId = 0;

                if (userPressedTrue == answerIsTrue){
                    messageResId = R.string.correct_toast;
                }
                else {
                    messageResId = R.string.incorrect_toast;
                }
                Toast.makeText(this,messageResId ,Toast.LENGTH_SHORT).show();
    }

    public void True (View view){
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
    }
    public void  False (View view){
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG , "onStarted () called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG , "onPause () called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG , "onResume () called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG , "onStop () called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG , "onDestroy () called");
    }
    //Переопределение onSaveInstanceState для записи текущего вопроса
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG , "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX , mCurrentIndex);
    }
}
