package co.in.dreamguys.littlekids;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by user5 on 28-09-2017.
 */

public class LittleKids extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //Realm init
        Realm.init(this);
        // create your Realm configuration
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                schemaVersion(1).
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);

        // Picasso offline cache init
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);


    }
}
