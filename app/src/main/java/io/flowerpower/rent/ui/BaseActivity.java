package io.flowerpower.rent.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.flowerpower.rent.ui.fragment.BaseFragment;

/**
 * Created by Raman Branavitski on 6/10/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(int contentFrame, BaseFragment fragment) {
        replaceFragment(contentFrame, fragment, false, BaseFragment.getFragmentTagForClass(fragment.getClass()));
    }

    private void replaceFragment(int contentFrame, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(contentFrame, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
