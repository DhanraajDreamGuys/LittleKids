package co.in.dreamguys.littlekids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.kaelaela.verticalviewpager.VerticalViewPager;

/**
 * Created by user5 on 14-09-2017.
 */

public class Alphabets extends AppCompatActivity {

    VerticalViewPager verticalViewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_pager);

    }

    public void initWidgets() {
        verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
    }
}
