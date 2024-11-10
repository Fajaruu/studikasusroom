/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*interface untuk DAO*/
@Dao
interface ItemDao {
    /*Mendefinisikan query untuk mengambil semua item dari tabel 'items' dan mengurutkan berdasarkan nama secara ascending (ASC)*/
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

    /*Mendefinisikan query untuk mengambil satu item berdasarkan ID dari tabel 'items'*/
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /*Menambahkan item baru ke dalam database; jika item sudah ada, strategi IGNORE akan mengabaikan konflik*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /*Memperbarui item yang ada di database*/
    @Update
    suspend fun update(item: Item)

    /*Menghapus item dari database*/
    @Delete
    suspend fun delete(item: Item)
}
