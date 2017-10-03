package co.in.dreamguys.littlekids;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import co.in.dreamguys.littlekids.Adapter.AdapterCategoryList;

public class MainActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static ArrayList<Integer> ImageList = new ArrayList<Integer>();
    private static ArrayList<String> AlphabetList = new ArrayList<>();
    private ListView mAlphabetList;
    private AdapterCategoryList adapterCategoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        /*mPager.setAdapter(new ImageViewAdapter(MainActivity.this, ImageList, AlphabetList));*/
        adapterCategoryList = new AdapterCategoryList(this, AlphabetList);
        mAlphabetList.setAdapter(adapterCategoryList);
    }


    private void initWidgets() {
        mAlphabetList = (ListView) findViewById(R.id.AM_LV_categories);
        AlphabetList.add("Alphabets");
        AlphabetList.add("Noun");
        /*mPager = (ViewPager) findViewById(R.id.pager);*/

    }
}
