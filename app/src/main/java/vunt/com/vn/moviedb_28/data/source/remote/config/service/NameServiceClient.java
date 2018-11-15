package vunt.com.vn.moviedb_28.data.source.remote.config.service;

import android.app.Application;

public class NameServiceClient extends ServiceClient {
    private static final String END_POINT_URL = "https://api.themoviedb.org/3";
    private static final String EXCEPTION_MESSAGE =
            " is not initialized, call initialize(..) method first.";
    private static Api sApiInstacne;

    public static void initialize (Application application) {
        sApiInstacne = createService(application, END_POINT_URL, Api.class);
    }

    public static Api getInstance() {
        if (sApiInstacne == null) {
            throw new IllegalStateException(NameServiceClient.class.getSimpleName()
                    + EXCEPTION_MESSAGE);
        }
        return sApiInstacne;
    }
}
