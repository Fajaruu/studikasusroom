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

import kotlinx.coroutines.flow.Flow

/*OfflineItemsRepository adalah kelas yang mengimplementasikan antarmuka ItemsRepository, \
bertanggung jawab untuk mengelola data item dari sumber data lokal (seperti database).*/
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    /*mengembalikan aliran data (Flow) yang berisi daftar semua item yang diambil dari ItemDao.*/
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /*mengembalikan aliran data (Flow) yang berisi satu item berdasarkan ID yang diberikan.*/
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /*menyisipkan item baru ke dalam database menggunakan itemDao.*/
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /*menghapus item dari database menggunakan itemDao.*/
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /*memperbarui item yang sudah ada di database menggunakan itemDao.*/
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}
