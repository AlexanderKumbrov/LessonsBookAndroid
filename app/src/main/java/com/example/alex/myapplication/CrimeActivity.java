package com.example.alex.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity  {
    // Метод для передачи информации
    private static final String EXTRA_CRIME_ID ="com.example.alex.myapplication.crime_id";     //После создания явного интента мы вызываем putExtra(…), передавая строковый
    public static Intent newIntent (Context packageContext , UUID crimeId){                  //ключ и связанное с ним значение (crimeId). В данном случае вызывается версия
        Intent intent = new Intent(packageContext , CrimeActivity.class);                   //putExtra(String,Serializable), потому что UUID является объектомSerializable.
        intent.putExtra(EXTRA_CRIME_ID ,crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
