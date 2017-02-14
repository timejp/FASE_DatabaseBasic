package com.timejh.databasebasic.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by tokijh on 2017. 2. 14..
 */

@DatabaseTable(tableName = "bbs")
public class Bbs {

    @DatabaseField(generatedId = true) //자동 증가
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String content;

    @DatabaseField
    private Date currentDate;

    public Bbs() {
        //이게 없으면 ormlite가 동작하지 않는다.
        //public이 없어도 동작한다.
    }

    public Bbs(int id, String title, String content, Date currentDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.currentDate = currentDate;
    }

    public Bbs(String title, String content, Date currentDate) {
        this.title = title;
        this.content = content;
        this.currentDate = currentDate;
    }


    public Date getCurrentDate() {
        return currentDate;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
