package com.example.alex.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstChapter extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

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
            }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_chapter);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionsBank.length;
                int question = mQuestionsBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
                updateQuestion();
            }
        });
        updateQuestion();
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
}
