package co.in.dreamguys.littlekids.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.test.CategoryList;

/**
 * Created by user5 on 08-09-2017.
 */

public class VerticalViewpager extends PagerAdapter {

    private ArrayList<Integer> images = new ArrayList<Integer>();
    private ArrayList<CategoryList> alphabetList;
    private Context context;
    private LayoutInflater layoutInflater;

    public VerticalViewpager(Context context, ArrayList<Integer> images, ArrayList<CategoryList> alphabetList) {
        this.alphabetList = alphabetList;
        this.images = images;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public Object instantiateItem(ViewGroup view, int position) {
        View layout = layoutInflater.inflate(R.layout.adapter_alphabets, view, false);
        assert layout != null;
        TextView txtname, txtalphabet;
        ImageView input_image;

        txtname = (TextView) layout.findViewById(R.id.tv_txtname);
        txtalphabet = (TextView) layout.findViewById(R.id.tv_alphabet);
        input_image = (ImageView) layout.findViewById(R.id.AAimage);

        /*txtname.setText(alphabetList.get(position).getAlphabetsLists().get(position).getAlphabetname());*/
        txtalphabet.setText(alphabetList.get(position).getAlphabetLetter());
        view.addView(layout, 0);
        return layout;
    }

    @Override
    public int getCount() {
        return alphabetList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
