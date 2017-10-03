package co.in.dreamguys.littlekids.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by user5 on 12-09-2017.
 */

public class Utility {

    public static boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }


    public static void loadImage(final Context mcontext, final String url, final ImageView target) {
        Picasso.with(mcontext)
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(target, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.v("Picasso", "Image fetch through cache");
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(mcontext)
                                .load(url)
                                .into(target, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.v("Picasso", "Image fetch through internet");
                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
    }


}
