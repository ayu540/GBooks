package com.example.anshultech.gbooks;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.List;

/**
 * Created by AnshulTech on 07-Jan-18.
 */


public class BooksLoaderAsync extends AsyncTaskLoader<List<Booksgetter_setter>> {

    private String mURL= null ;

    public BooksLoaderAsync(Context context, String url) {
        super(context);
        mURL= url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Booksgetter_setter> loadInBackground() {
         List<Booksgetter_setter>  loaddata = null;

        if (mURL ==  null){
            return  null;
        }
        try {
            loaddata = BooksJsonActivity.extractJsondata(mURL); //called the jason parsing in background task so that data will be get filled in asynchonously
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loaddata;

    }
}
