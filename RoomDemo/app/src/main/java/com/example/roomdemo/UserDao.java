package com.example.roomdemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * TODO
 * shige chen on 2020/2/26
 * shigechen@globalsources.com
 */
@Dao
public interface UserDao {

    @Query("select * from userinfoentity")
    List<UserInfoEntity> getAllData();

    //REPLACE 删除旧的，以新的为准
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserInfo(UserInfoEntity infoEntity);

    //REPLACE 删除旧的，以新的为准
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUserInfo(UserInfoEntity infoEntity);

    @Delete
    void deleteUserInfo(UserInfoEntity infoEntity);
}
