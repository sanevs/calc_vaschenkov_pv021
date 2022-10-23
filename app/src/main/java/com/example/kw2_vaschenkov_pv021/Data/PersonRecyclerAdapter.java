package com.example.kw2_vaschenkov_pv021.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kw2_vaschenkov_pv021.R;

import java.util.ArrayList;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.ViewHolder> {
    private ArrayList<Person> dataSet;
    private Context context;

    public PersonRecyclerAdapter(ArrayList<Person> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view6,
                        parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonRecyclerAdapter.ViewHolder holder, int position) {
        Person person = this.dataSet.get(position);
        holder.tvName.setText(person.name);
        holder.tvSpeciality.setText(person.speciality);
        String age = String.valueOf(person.age) +
                " years old";
        holder.tvAge.setText(age);
        holder.ivAvatar.setImageDrawable(
                this.context.getResources().
                        getDrawable(((person.gender) ?
                                R.drawable.user_man: R.drawable.
                                user_woman), this.context.getTheme()));
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvSpeciality;
        public TextView tvAge;
        public ImageView ivAvatar;

        public ViewHolder(View v) {
            super(v);
            this.tvName = (TextView)
                    v.findViewById(R.id.tvName);
            this.tvSpeciality = (TextView)
                    v.findViewById(R.id.tvSpeciality);
            this.tvAge = (TextView)
                    v.findViewById(R.id.tvAge);
            this.ivAvatar = (ImageView)
                    v.findViewById(R.id.ivAvatar);        }
    }
}
