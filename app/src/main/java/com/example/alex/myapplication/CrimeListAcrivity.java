package com.example.alex.myapplication;

import android.support.v4.app.Fragment;

public class CrimeListAcrivity  extends  SingleFragmentActivity{
@Override
    protected Fragment createFragment(){
    return new CrimeListFragment();
}
}
