package com.rkgroup.base.basemodule.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save( value: T):Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg value: T):List<Long>
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg value: T):Int

    @Delete
    suspend fun delete(vararg value: T):Int


}