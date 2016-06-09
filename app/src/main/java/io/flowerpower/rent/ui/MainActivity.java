package io.flowerpower.rent.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.flowerpower.rent.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new MapRentFragment()).commit();
    }

}
