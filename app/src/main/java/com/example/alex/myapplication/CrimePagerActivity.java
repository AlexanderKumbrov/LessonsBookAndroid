package com.example.alex.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;
//С помощью ViewPager реализуется перелистывание жестом
public class CrimePagerActivity extends FragmentActivity {
    private static final String EXTRA_CRIME_ID ="com.example.alex.myapplication.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    // Метод для передачи информации
    // После создания явного интента мы вызываем putExtra(…), передавая строковый
    public static Intent newIntent (Context packageContext , UUID crimeId){                  //ключ и связанное с ним значение (crimeId). В данном случае вызывается версия
        Intent intent = new Intent(packageContext , CrimePagerActivity.class);                   //putExtra(String,Serializable), потому что UUID является объектомSerializable.
        intent.putExtra(EXTRA_CRIME_ID ,crimeId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();              //Получаем экземпляр FragmentManager
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {      //назначается безымянный экземпляр FragmentStatePagerAdapter.
            @Override                                                               //Для создания FragmentStatePagerAdapter необходим объект FragmentManager. FragmentStatePagerAdapter —  агент, управляющий взаимодействием с ViewPager.
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
for (int i = 0 ; i <mCrimes.size(); i++){               //В ViewPager помещается id выбранного элемента
    if (mCrimes.get(i).getId().equals(crimeId)){        //так как по умолчанию ViewPager вставляет первый элемент в списке
        mViewPager.setCurrentItem(i);
        break;
    }
}
    }

}
