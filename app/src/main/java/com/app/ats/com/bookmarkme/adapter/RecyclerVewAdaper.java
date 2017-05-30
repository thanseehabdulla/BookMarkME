package com.app.ats.com.bookmarkme.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        public TextView url;
        public RelativeLayout hold;
        public Button delete;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            url = (TextView) view.findViewById(R.id.url);
            hold = (RelativeLayout) view.findViewById(R.id.hold);
            delete = (Button) view.findViewById(R.id.button2);

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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RecyclerViewModel movie = moviesList.get(position);
        if(movie.getTitle().equals("")){
         holder.hold.setVisibility(View.GONE);

        }else {
            holder.hold.setVisibility(View.VISIBLE);
            holder.title.setText(movie.getTitle());
            holder.url.setText(movie.getUrl());
            final String urlss = movie.getUrl();

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences emer2 = context.getSharedPreferences("url", MODE_PRIVATE);

                    String snnn = emer2.getString("name", "0");
                    String suuu = emer2.getString("url", "0");

                    StringBuffer sb, sb2;

                    if (snnn != "0") {
                        String[] numbers = snnn.split(",");
                        String[] numbers2 = suuu.split(",");

                        final String[] mobile = new String[numbers.length];
                        final String[] mobile2 = new String[numbers.length];

                        moviesList.clear();
                        sb = new StringBuffer();
                        sb2 = new StringBuffer();
                        for (int i = 0; i < numbers.length; i++) {
                            if (i == position) {

                            } else {
                                mobile[i] = (numbers[i]);
                                mobile2[i] = (numbers2[i]);

                                sb.append(mobile[i]);
                                sb.append(",");

                                sb2.append(mobile2[i]);
                                sb2.append(",");
                            }

                        }
                        context.getSharedPreferences("url", MODE_PRIVATE).edit().putString("name", sb.toString()).apply();
                        context.getSharedPreferences("url", MODE_PRIVATE).edit().putString("url", sb2.toString()).apply();
                        prepareMovieData();
                    }
                }
            });


            holder.hold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(context, Webv.class);
                    i.putExtra("url", urlss);
                    context.startActivity(i);


                }
            });

        }

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
                RecyclerViewModel movie = new RecyclerViewModel(mobile[i], mobile2[i]);
                moviesList.add(movie);
            }

        }
        notifyDataSetChanged();
    }
}
