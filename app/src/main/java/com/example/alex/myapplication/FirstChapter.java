package com.example.alex.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

    private Question [] mQuestionsBank = new Question[]{        // Вызов конструктор  Question
            new Question(R.string.question_oceans ,true),
            new Question(R.string.question_mideast , false),
            new Question(R.string.question_africa , false),
            new Question(R.string.question_americas , true),
            new Question(R.string.question_asia , true),
    };
            private int mCurrentIndex = 0 ;

            private void updateQuestion (){
                int question = mQuestionsBank [mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
                mCurrentIndex = (mCurrentIndex +1) % mQuestionsBank.length;
            }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG , "onCreate(Bundle) called");
        setContentView(R.layout.first_chapter);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX , 0);
        }
    updateQuestion();

    }

    //Переопределение onSaveInstanceState для записи текущего вопроса
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG , "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX , mCurrentIndex);
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



    public void previous_button(View view){
                mPreviousButton = (ImageButton)findViewById(R.id.previous_button);
                mPreviousButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int question = mQuestionsBank [mCurrentIndex].getTextResId();
                        mQuestionTextView.setText(question);
                        mCurrentIndex = (mCurrentIndex -1) % mQuestionsBank.length;
                    }
                });
    }

    public void next_button (View view){
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
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
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG , "onDestroy () called");
    }

}
