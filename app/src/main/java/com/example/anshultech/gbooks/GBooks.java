package com.example.anshultech.gbooks;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GBooks extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Booksgetter_setter>>{
//&key=AIzaSyCDeh5j19TZtSGmF0YH2T0BYHHSf80sqCA
    private static String CONNECTING_URL= "https://www.googleapis.com/books/v1/volumes?q=flower";
    BooksAdapter mAdapter = null;// creating a blank variable for adapter class
    private static final int NULL_DATA_INITLOADER = 1;
    ProgressBar myprogressbar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gbooks);

        ListView bookslistview = (ListView) findViewById(R.id.list);
        mAdapter = new BooksAdapter(this, new ArrayList<Booksgetter_setter>());
        bookslistview.setAdapter(mAdapter);

        TextView nodatafound = (TextView) findViewById(R.id.textempty_data);
        nodatafound.setVisibility(View.GONE);
        myprogressbar = (ProgressBar) findViewById(R.id.progressbar);
        myprogressbar.setVisibility(View.VISIBLE);


        LoaderManager manager = getLoaderManager();
        manager.initLoader(NULL_DATA_INITLOADER, null, this);
    }

    @Override
    public Loader<List<Booksgetter_setter>> onCreateLoader(int i, Bundle bundle) {
        return new BooksLoaderAsync(this, CONNECTING_URL);// for the correction of this use import android.content.AsyncTaskLoader instead of classes from v4
    }

    @Override
    public void onLoadFinished(Loader<List<Booksgetter_setter>> loader, List<Booksgetter_setter> data) {
        myprogressbar.setVisibility(View.GONE);
        mAdapter.clear();
        mAdapter.addAll(data);

    }

    @Override
    public void onLoaderReset(Loader<List<Booksgetter_setter>> loader) {
        mAdapter.clear();
    }
}
