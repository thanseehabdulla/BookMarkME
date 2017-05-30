package com.app.ats.com.bookmarkme.modelclass;

/**
 * Created by abdulla on 20/5/17.
 */

public class RecyclerViewModel {
    private String url,title;

    public RecyclerViewModel() {
    }

    public RecyclerViewModel(String title,String url) {
        this.title = title;
        this.url = url;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getUrl(){ return  url;}

    public void setUrl(String name){this.url=name;}

}
