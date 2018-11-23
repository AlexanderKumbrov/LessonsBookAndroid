package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CrimeFragment extends Fragment {
    private Crime mCrime ;
    private EditText mTitleField;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        mCrime = new Crime();
    }
    @Override
    public View onCreateView(LayoutInflater inflater , final ViewGroup container , Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime , container , false);
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence c, int start, int before, int count) {
mCrime.setTitle(c.toString());
            }

            @Override
            public void afterTextChanged(Editable c) {

            }
        });
        return v;
    }

}