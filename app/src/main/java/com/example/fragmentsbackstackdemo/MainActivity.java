package com.example.fragmentsbackstackdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView count_view = findViewById(R.id.back_count_view);

        manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int count = manager.getBackStackEntryCount();
                count_view.setText(String.valueOf(count));
                Log.v("MainActivity ",String.valueOf(count));
            }
        });
    }

    public void addFragment(View v){
        Fragment fragment;

        // If a backstack is used
//        switch (manager.getBackStackEntryCount()){
//            case 0:
//                fragment = new FirstFragment();
//                Log.v("MainActivity","First fragment is added");
//                break;
//            case 1:
//                fragment = new SecondFragment();
//                Log.v("MainActivity","Second fragment is added");
//                break;
//            case 2:
//                fragment = new ThirdFragment();
//                Log.v("MainActivity","Third fragment is added");
//                break;
//            case 3:
//                fragment = new FourthFragment();
//                Log.v("MainActivity","Fourth fragment is added");
//                break;
//            default:
////                Log.v("MainActivity","No fragment is added");
//                fragment = new ThirdFragment();
//                break;
//
//        }

        // If a Backstack is not used
        fragment = manager.findFragmentById(R.id.container);
        if(fragment instanceof FirstFragment){
            fragment = new SecondFragment();
            Log.v("MainActivity","Second fragment is added");
        }
        else if(fragment instanceof  SecondFragment){
            fragment = new ThirdFragment();
            Log.v("MainActivity","Third fragment is added");
        }
        else if(fragment instanceof ThirdFragment){
            fragment = new FourthFragment();
            Log.v("MainActivity","Fourth fragment is added");
        }
        else if(fragment instanceof FourthFragment){
            fragment = new FirstFragment();
            Log.v("MainActivity","First fragment is added");
        }
        else{
            fragment = new FirstFragment();
            Log.v("MainActivity","First fragment is added");
        }

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container,fragment);
        transaction.commit();
    }

    public void removeFragment(View v){
        Log.v("MainActivity","removeFragment function is called");
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = manager.findFragmentById(R.id.container);
        if(fragment != null){
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
            Log.v("MainActivity","onBackPressed function is called");
            Log.v("MainActivity","An entry is removed from the back stack");
        }
        else{
            super.onBackPressed();
            Log.v("MainActivity","onBackPressed function is called");
            Log.v("MainActivity","There is no entry in the back stack");
        }

    }
}
