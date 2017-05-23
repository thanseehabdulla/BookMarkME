package com.app.ats.com.bookmarkme.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.ats.com.bookmarkme.MainActivity;
import com.app.ats.com.bookmarkme.R;
import com.app.ats.com.bookmarkme.Webv;
import com.app.ats.com.bookmarkme.modelclass.RecyclerViewModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by abdulla on 20/5/17.
 */

public class RecyclerVewAdaper extends RecyclerView.Adapter<RecyclerVewAdaper.MyViewHolder> {


    private final MainActivity context;
    private List<RecyclerViewModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public RelativeLayout hold;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            hold = (RelativeLayout) view.findViewById(R.id.hold);
        }
    }


    public RecyclerVewAdaper(List<RecyclerViewModel> moviesList, MainActivity mainActivity) {
        this.moviesList = moviesList;
        this.context=mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecyclerViewModel movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        final String urlss=movie.getUrl();
        holder.hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i  = new Intent(context, Webv.class);
                i.putExtra("url",urlss);
                context.startActivity(i);



            }
        });



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void prepareMovieData() {
        SharedPreferences emer2=context.getSharedPreferences("url",MODE_PRIVATE);

        String snnn=emer2.getString("name","0");
        String suuu=emer2.getString("url","0");



        if(snnn!="0"){
            String[] numbers = snnn.split(",");
            String[] numbers2 = suuu.split(",");

            final String[] mobile = new String[numbers.length];
            final String[] mobile2 = new String[numbers.length];

            moviesList.clear();
            for (int i = 0; i < numbers.length; i++) {
                mobile[i] = (numbers[i]);
                mobile2[i] = (numbers2[i]);
                RecyclerViewModel movie = new RecyclerViewModel(mobile[i], "Task initiated");
                moviesList.add(movie);
            }

        }
        notifyDataSetChanged();
    }
}
