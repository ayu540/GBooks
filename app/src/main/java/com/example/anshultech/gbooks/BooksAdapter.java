package com.example.anshultech.gbooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AnshulTech on 07-Jan-18.
 */

public class BooksAdapter extends ArrayAdapter<Booksgetter_setter> {

    public BooksAdapter (Context context , List<Booksgetter_setter> booksdata )
    {
        super(context, 0, booksdata);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View Listview  = convertView;
        if(Listview ==  null){

            Listview = LayoutInflater.from(getContext()).inflate(R.layout.listview,parent, false);
        }
        //get curent position of data
        Booksgetter_setter currentdata = getItem(position);
        //set text in textviews by fetching the view id and fetching the data from Booksgetter_setter methods
        //set title text
        TextView titletextview = Listview.findViewById(R.id.titletext);
        titletextview.setText(currentdata.getmTitle());
        //set author text
        TextView authortextview = Listview.findViewById(R.id.authortext);
        authortextview.setText(currentdata.getmAuthor());

        return Listview;
    }
}
