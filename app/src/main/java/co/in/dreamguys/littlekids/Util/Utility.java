package co.in.dreamguys.littlekids.Util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import co.in.dreamguys.littlekids.R;

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


    public static void showAlertDialog(final Context mContext, String message) {
        AlertDialog.Builder createBuilder = new AlertDialog.Builder(mContext);
        createBuilder.setCancelable(true).setMessage(message).setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        createBuilder.show();
    }

    public static void PassAlertPAct(final Activity mContext, String message) {
        AlertDialog.Builder createBuilder = new AlertDialog.Builder(mContext);
        createBuilder.setCancelable(true).setMessage(message).setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mContext.finish();
                dialog.cancel();
            }
        });
        createBuilder.show();
    }

    public static MediaPlayer createMediaplayerAudio(Context mContext) {
        return MediaPlayer.create(mContext, R.raw.switchs);
    }
}
