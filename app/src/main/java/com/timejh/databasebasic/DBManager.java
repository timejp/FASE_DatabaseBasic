package com.timejh.databasebasic;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.timejh.databasebasic.domain.Bbs;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by tokijh on 2017. 2. 14..
 */

public class DBManager {

    private DBHelper dbHelper;
    private Dao<Bbs, Long> bbsDao;

    private static DBManager dbManager;
    private static Context context;

    private DBManager() throws SQLException {
        init();
    }

    public static DBManager getInstance(Context con) throws SQLException {
        if(context == null) {
            context = con;
        }
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private void init() throws SQLException {
        //1. DB 연결
        dbHelper = OpenHelperManager.getHelper(context, DBHelper.class);
        //2. 테이블을 조작하기 위한 객체를 생성한다.
        bbsDao = dbHelper.getBbsdao();
    }

    public void insert(Bbs bbs) throws SQLException {
        bbsDao.create(bbs);
    }

    public Bbs selectByID(long id) throws SQLException {
        return bbsDao.queryForId(id);
    }

    public List<Bbs> select() throws SQLException {
        return bbsDao.queryForAll();
    }

    public List<Bbs> select(String filedName, Object value) throws SQLException {
        return bbsDao.queryForEq(filedName, value);
    }

    public List<Bbs> select(String query) throws SQLException {
        //"SELECT title from Bbs;
        GenericRawResults<Bbs> rawResults = bbsDao.queryRaw(query, bbsDao.getRawRowMapper());
        return rawResults.getResults();
    }

    public void delete(long id) throws SQLException {
        bbsDao.deleteById(id);
    }

    public void delete(Bbs bbs) throws SQLException {
        bbsDao.delete(bbs);
    }

    public void update(Bbs bbs) throws SQLException {
        bbsDao.update(bbs);
    }
}
