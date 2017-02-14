package com.timejh.databasebasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.timejh.databasebasic.domain.Bbs;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private Dao<Bbs, Long> bbsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            insert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert() throws SQLException {
        //1. DB 연결
        dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        //2. 테이블을 조작하기 위한 객체를 생성한다.
        bbsDao = dbHelper.getBbsdao();
        //3. Bbs생성 및 dao를 통해 insert
        bbsDao.create(new Bbs("Title1", "Content1", new Date(System.currentTimeMillis())));
        bbsDao.create(new Bbs("Title2", "Content2", new Date(System.currentTimeMillis())));
        bbsDao.create(new Bbs("Title3", "Content3", new Date(System.currentTimeMillis())));
        bbsDao.create(new Bbs("Title4", "Content4", new Date(System.currentTimeMillis())));
        bbsDao.create(new Bbs("Title5", "Content5", new Date(System.currentTimeMillis())));
        bbsDao.create(new Bbs("Title6", "Content6", new Date(System.currentTimeMillis())));
        bbsDao.create(new Bbs("Title7", "Content7", new Date(System.currentTimeMillis())));

        //내용을 가져온다.
        List<Bbs> bbses = bbsDao.queryForAll();
        for (Bbs item : bbses) {
            Log.i("MainActivity", String.format("id : %d, title : %s, content : %s, date : %s", item.getId(), item.getTitle(), item.getContent(), item.getCurrentDate().toString()));
        }
        Bbs bbs2 = bbsDao.queryForId(2L);

        List<Bbs> title3Bbs = bbsDao.queryForEq("title", "Title3");

    }
}
