package com.example.appreadingcomic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.appreadingcomic.R;
import com.example.appreadingcomic.object.Comic;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends ArrayAdapter<Comic> {
    private Context ct;
    private ArrayList<Comic> arr;
    public ComicAdapter(@NonNull Context context, int resource, @NonNull List<Comic> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_comic, null);
        }
        if (arr.size() > 0){
            Comic comic = this.arr.get(position);

            TextView nameComic = convertView.findViewById(R.id.txvNameComic);
            TextView nameChap = convertView.findViewById(R.id.txvNameChap);
            ImageView imgComic = convertView.findViewById(R.id.imgComic);

            nameComic.setText(comic.getNameComic());
            nameChap.setText(comic.getNameChap());
            Glide.with(this.ct).load(comic.getLinkComic()).into(imgComic);
        }
        return  convertView;
    }
}