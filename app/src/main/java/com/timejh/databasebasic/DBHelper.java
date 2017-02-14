package com.timejh.databasebasic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.timejh.databasebasic.domain.Bbs;

import java.sql.SQLException;

/**
 * Created by tokijh on 2017. 2. 14..
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 생성자에서 호출되는 super(context...에서 database.db파일이 생성되어있지 않으면 호출
     *
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //Bbs.class에 정의된 Table을 생성한다.
            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 파일이 존재하지만 DATABASE_VERSION이 증가되면 호출된다.
     *
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //Bbs.class에 정의된 Talbe삭제
            TableUtils.dropTable(connectionSource, Bbs.class, false);

            //TODO 데이터를 보존해야할 필요성이 있으면 중간처리를 해야한다.
            //임시테이블을 생성하고 데이터를 저장하고, onCreate다음에 데이터를 입력한다.

            //onCreate로 테이블 생성
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dao<Bbs, Long> bbsdao = null;

    public Dao<Bbs, Long> getBbsdao() throws SQLException {
        if (bbsdao == null) {
            bbsdao = getDao(Bbs.class);
        }
        return bbsdao;
    }

    public void releaseBbsDao() {
        if (bbsdao != null) {
            bbsdao = null;
        }
    }
}