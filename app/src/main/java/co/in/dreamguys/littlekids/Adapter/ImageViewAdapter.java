package co.in.dreamguys.littlekids.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.test.CategoryList;

/**
 * Created by user5 on 07-09-2017.
 */

public class ImageViewAdapter extends PagerAdapter {

    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private ArrayList<CategoryList> alphabetList;
    private Context context;


    public ImageViewAdapter(Context context, ArrayList<CategoryList> alphabetList) {
        this.context = context;
        this.IMAGES = IMAGES;
        this.alphabetList = alphabetList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return alphabetList.size();
    }


    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.activity_imageview_pager, view, false);
        assert imageLayout != null;
        ListView LV_imageList = (ListView) imageLayout.findViewById(R.id.lv_alphabets);
        /*VerticalViewPager verticalViewPager = (VerticalViewPager) imageLayout.findViewById(R.id.verticalviewpager);
        verticalViewPager.setAdapter(new VerticalViewpager(context, IMAGES, alphabetList));*/
        AlphabetListAdapter alphabetListAdapter;
        alphabetListAdapter = new AlphabetListAdapter(context, IMAGES, alphabetList);
        LV_imageList.setAdapter(alphabetListAdapter);
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
