package co.in.dreamguys.littlekids.Adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import co.in.dreamguys.littlekids.LearningAct;
import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.RealmModel.SectionLists;
import io.realm.RealmList;


public class AdapterLearningAct extends PagerAdapter {

    Context context;
    RealmList<SectionLists> listItems;
    int adapterType;
    MediaPlayer mediaPlayer;

    public AdapterLearningAct(Context context, RealmList<SectionLists> listItems, int adapterType) {
        this.context = context;
        this.listItems = listItems;
        this.adapterType = adapterType;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        TextView inputAlphabetLetter, inputAlphabetName;
        ImageView inputAlphabetImage;
        ImageView inputAlphabetPlay;
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_learning_act, null);
        try {
            LinearLayout linMain = (LinearLayout) view.findViewById(R.id.linMain);
            inputAlphabetImage = (ImageView) view.findViewById(R.id.AL_TV_alphabet_image);
            inputAlphabetLetter = (TextView) view.findViewById(R.id.AL_TV_alphabet_letter);
            inputAlphabetName = (TextView) view.findViewById(R.id.AL_TV_alphabet_name);
            inputAlphabetPlay = (ImageView) view.findViewById(R.id.AL_IV_alphabet_play);

            inputAlphabetPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(listItems.get(position).getSection_audio_url());
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                }
            });
            linMain.setTag(position);

            switch (adapterType) {
                case LearningAct.ADAPTER_TYPE_TOP:
                    linMain.setBackgroundResource(R.drawable.shadow);
                    inputAlphabetImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Picasso.with(context)
                            .load(listItems.get(position).getSection_img_url())
                            .into(inputAlphabetImage);
                    inputAlphabetName.setText(listItems.get(position).getSection_name());
                    inputAlphabetLetter.setText(listItems.get(position).getSection_title());
                    break;
            }


            container.addView(view);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

}