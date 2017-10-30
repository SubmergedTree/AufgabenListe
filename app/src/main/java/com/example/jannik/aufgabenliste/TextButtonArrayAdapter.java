package com.example.jannik.aufgabenliste;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jannik on 19.05.17.
 */

interface AdapterButtonInterface {
    void adapterButtonClicked(int position);
}

public class TextButtonArrayAdapter extends ArrayAdapter<Exercise> {

    private int layout;
    private List<Exercise> copyObjects;
    private AdapterButtonInterface adapterButtonInterface;

    public TextButtonArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Exercise> objects/*,
                                  AdapterButtonInterface adapterButtonInterface*/) {
        super(context, resource, objects);
        layout = resource;
        copyObjects = objects;

        this.adapterButtonInterface = (AdapterButtonInterface) context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(layout, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.Headline2);
        Button button= (Button) convertView.findViewById(R.id.deleteButton);

        textView.setText(copyObjects.get(position).getHeadline());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterButtonInterface.adapterButtonClicked(position);
                //copyObjects.remove(position);
                TextButtonArrayAdapter.this.notifyDataSetChanged();
            }
        });
        convertView.setTag(position);
        return convertView;
    }
}