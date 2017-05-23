package com.app.ats.com.bookmarkme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.ats.com.bookmarkme.adapter.RecyclerVewAdaper;
import com.app.ats.com.bookmarkme.modelclass.Broadcast;
import com.app.ats.com.bookmarkme.modelclass.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {


RecyclerView r;
    Button b;
    EditText e,e2;
    StringBuffer sb,sb2;
    private List<RecyclerViewModel> movieList = new ArrayList<>();
    private RecyclerVewAdaper mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b = (Button) findViewById(R.id.button);

        e = (EditText) findViewById(R.id.editText);


        r = (RecyclerView) findViewById(R.id.recyclerView);

        e2 = (EditText) findViewById(R.id.editText2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e2.getText().toString();

                String url = e.getText().toString();



                if(name.isEmpty())
                    Toast.makeText(getApplicationContext(),"Fill All the Fields",Toast.LENGTH_SHORT).show();
                else if(url.isEmpty())
                    Toast.makeText(getApplicationContext(),"Fill All the Fields",Toast.LENGTH_SHORT).show();

               String surl = getSharedPreferences("url",MODE_PRIVATE).getString("url","0");
               String sname = getSharedPreferences("url",MODE_PRIVATE).getString("name","0");


                sb = new StringBuffer();
                sb2 = new StringBuffer();


                if(!surl.equals("0")) {
                    sb2.append(surl);
                    sb.append(sname);
                }



                sb.append(name);
                sb.append(",");

                sb2.append(url);
                sb2.append(",");


                getSharedPreferences("url",MODE_PRIVATE).edit().putString("name",name);
                getSharedPreferences("url",MODE_PRIVATE).edit().putString("url",url).apply();

                EventBus.getDefault().postSticky(new Broadcast("syncing"));
            }
        });


        mAdapter = new RecyclerVewAdaper(movieList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        r.setLayoutManager(mLayoutManager);
        r.setItemAnimator(new DefaultItemAnimator());
        r.setAdapter(mAdapter);

        prepareMovieData();



    }

    private void prepareMovieData() {

        SharedPreferences emer2=getSharedPreferences("url",MODE_PRIVATE);

        String snnn=emer2.getString("name","0");
        String suuu=emer2.getString("url","0");

        if(snnn!="0"){
            String[] numbers = snnn.split(",");
            String[] numbers2 = suuu.split(",");

            final String[] mobile = new String[numbers.length];
            final String[] mobile2 = new String[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                mobile[i] = (numbers[i]);
                mobile2[i] = (numbers2[i]);
                RecyclerViewModel movie = new RecyclerViewModel(mobile[i], mobile2[i]);
                movieList.add(movie);
            }

        }
        mAdapter.notifyDataSetChanged();



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }




    @Override
    public void onResume(){
        super.onResume();
        EventBus.getDefault().registerSticky(this);
        // Set title bar


    }


    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    // This method will be called when a HelloWorldEvent is posted
    public void onEvent(Broadcast event){
//        Toast.makeText(getActivity(), event.getMessage(), Toast.LENGTH_SHORT).show();

        mAdapter.prepareMovieData();



    }









}
