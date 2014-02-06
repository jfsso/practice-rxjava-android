package jp.joao.android.xxx.rxjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import jp.joao.android.xxx.rxjava.data.prefs.BooleanPreference;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class PreferencesHelper implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String ENABLED_PREFERENCE = "example_checkbox";

    private SharedPreferences mSharedPreferences;

    protected final BehaviorSubject<BooleanPreference> mEnabledPreferenceSubject;

    public PreferencesHelper(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        mEnabledPreferenceSubject = BehaviorSubject.create(new BooleanPreference(mSharedPreferences, ENABLED_PREFERENCE));
        mEnabledPreferenceSubject.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case ENABLED_PREFERENCE:
                mEnabledPreferenceSubject.onNext(new BooleanPreference(sharedPreferences, key));
                break;
        }
    }

    public Observable<BooleanPreference> subscribeEnabledPreference() {
        return mEnabledPreferenceSubject;
    }
}
