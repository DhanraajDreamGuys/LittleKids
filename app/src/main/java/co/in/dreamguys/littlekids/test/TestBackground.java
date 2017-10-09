package co.in.dreamguys.littlekids.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.Util.Utility;

/**
 * Created by user5 on 06-10-2017.
 */

public class TestBackground extends AppCompatActivity {

    private ImageView inputSun1, inputCloud1, inputCloud;
    Handler handler, handler1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_background);
        inputSun1 = (ImageView) findViewById(R.id.AB_IV_sun1);
        inputCloud = (ImageView) findViewById(R.id.AB_IV_cloud);
        handler = new Handler();
        handler1 = new Handler();

        Utility.handler(TestBackground.this, inputSun1, 13000, handler);
        Utility.MoveImage(TestBackground.this, inputCloud, 5000, handler);
        Utility.MoveImage1(TestBackground.this, inputSun1, 14000, handler1);

    }
}
