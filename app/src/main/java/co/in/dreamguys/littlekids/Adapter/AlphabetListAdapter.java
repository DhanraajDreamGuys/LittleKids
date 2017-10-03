package co.in.dreamguys.littlekids.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.test.CategoryList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by user5 on 07-09-2017.
 */

public class AlphabetListAdapter extends BaseAdapter {

    private ArrayList<Integer> IMAGES;
    private ArrayList<CategoryList> AlphabetList;
    private LayoutInflater inflater;
    private Context context;


    public AlphabetListAdapter(Context context, ArrayList<Integer> IMAGES, ArrayList<CategoryList> alphabetList) {
        this.IMAGES = IMAGES;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.AlphabetList = alphabetList;
    }

    @Override
    public int getCount() {
        return AlphabetList.size();
    }

    @Override
    public Object getItem(int position) {
        return IMAGES.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_alphabets, null);
            viewHolder.input_image = (ImageView) convertView.findViewById(R.id.AAimage);
            viewHolder.input_textname = (TextView) convertView.findViewById(R.id.tv_txtname);
            viewHolder.input_Alphabet = (TextView) convertView.findViewById(R.id.tv_alphabet);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*for (int i = 0; i < IMAGES.size(); i++) {
            ArrayList<Integer> tempArray = new ArrayList<Integer>();
            tempArray.subList(IMAGES.get(i), IMAGES.get(i + 1));
        }*/

        /*viewHolder.input_image.setImageResource(IMAGES.get(position));*/
        /*viewHolder.input_textname.setText(AlphabetList.get(position).getAlphabetLetter());*/
        viewHolder.input_Alphabet.setText(AlphabetList.get(position).getAlphabetLetter());

        return convertView;
    }

    class ViewHolder {
        ImageView input_image;
        TextView input_textname, input_Alphabet;
    }

}
