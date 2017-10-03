package co.in.dreamguys.littlekids.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.littlekids.Adapter.ImageViewAdapter;
import co.in.dreamguys.littlekids.R;

/**
 * Created by user5 on 25-09-2017.
 */

public class TestActivity extends AppCompatActivity {


    ViewPager viewPager;

    ArrayList<CategoryList> categoryLists = new ArrayList<CategoryList>();
    List<AlphabetList> alphabetLists = new ArrayList<>();
    CategoryList categoryList;
    AlphabetList alphabetList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        categoryList = new CategoryList();
        categoryList.setAlphabetLetter("A");
        categoryLists.add(categoryList);

        categoryList = new CategoryList();
        categoryList.setAlphabetLetter("B");
        categoryLists.add(categoryList);

        /*alphabetList = new AlphabetList();
        alphabetList.setAlphabetname("Apple");
        alphabetLists.add(alphabetList);
        categoryList.setAlphabetsLists(alphabetLists);

        alphabetList = new AlphabetList();
        alphabetList.setAlphabetname("Ant");
        alphabetLists.add(alphabetList);
        categoryList.setAlphabetsLists(alphabetLists);
*/

        viewPager = (ViewPager) findViewById(R.id.vp_test);
        viewPager.setAdapter(new ImageViewAdapter(TestActivity.this, categoryLists));
    }
}
