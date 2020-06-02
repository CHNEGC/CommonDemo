package com.example.roomdemo;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * TODO
 * shige chen on 2020/2/26
 * shigechen@globalsources.com
 */
@Database(entities = { UserInfoEntity.class }, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase mAppDataBase;

    public abstract UserDao getUserDao();

    public static void initDataBase(Context mContext) {
        if (null == mAppDataBase) {
            synchronized (AppDataBase.class) {
                if (null == mAppDataBase) {
                    mAppDataBase = Room.databaseBuilder(mContext.getApplicationContext(), AppDataBase.class,
                            "gsbuyer_database")
                            .addCallback(sCallback)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addMigrations(migrations)
                            .build();
                }
            }
        }
    }

    public static AppDataBase getAppDataBase() {
        if (null == mAppDataBase) {
            throw new NullPointerException(
                    "AppDataBase.initDataBase(context) has not call, remember call this function in your Application.class");
        }
        return mAppDataBase;
    }

    private static RoomDatabase.Callback sCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static Migration[] migrations = new Migration[] {
            new Migration(1, 2) {
                @Override
                public void migrate(SupportSQLiteDatabase database) {
                    //database.execSQL("ALTER TABLE userinfoentity " + " ADD COLUMN userKg3 INTEGER");
                }
            }
    };
}
