package rs.raf.projekat1.nikola_trajkovic_4519rn.aplication;

import android.app.Application;

import timber.log.Timber;

public class DomaciAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
