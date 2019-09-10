package com.multirest.vishalverma.doodhmaster;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.multirest.vishalverma.doodhmaster.BottomNavBehaviour.BottomNavigationBehavior;
import com.multirest.vishalverma.doodhmaster.Fragments.AddFragment;
import com.multirest.vishalverma.doodhmaster.Fragments.HomeFragment;
import com.multirest.vishalverma.doodhmaster.Fragments.MisFragment;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements AddFragment.OnFragmentInteractionListener,MisFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        // attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
////        layoutParams.setBehavior(new BottomNavigationBehavior());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
//           String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
//


            switch (item.getItemId()) {
                case R.id.navigation_homr:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_add:
                    fragment = new AddFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_mis:
                    fragment = new MisFragment();
                    loadFragment(fragment);
                    return true;

            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {


    }
}
