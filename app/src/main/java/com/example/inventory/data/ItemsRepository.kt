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

/*ItemsRepository adalah antarmuka  yang mendefinisikan operasi CRUD (Create, Read, Update, Delete) untuk objek Item dari sumber data.*/
interface ItemsRepository {
    /*Fungsi ini digunakan untuk mengambil semua item dari sumber data dalam bentuk aliran data (Flow) yang berisi daftar item.*/
    fun getAllItemsStream(): Flow<List<Item>>

    /* Fungsi ini digunakan untuk mengambil item yang sesuai dengan ID yang diberikan. Mengembalikan aliran data (Flow) yang berisi satu objek Item atau null jika tidak ditemukan.*/
    fun getItemStream(id: Int): Flow<Item?>

    /*Fungsi ini digunakan untuk menyisipkan item baru ke dalam sumber data. Fungsi ini bersifat suspend, yang berarti ia bekerja dalam coroutine dan membutuhkan eksekusi secara asinkron.*/
    suspend fun insertItem(item: Item)

    /*Fungsi ini digunakan untuk menghapus item dari sumber data. Fungsi ini juga bersifat suspend untuk eksekusi asinkron.*/
    suspend fun deleteItem(item: Item)

    /*Fungsi ini digunakan untuk memperbarui item yang sudah ada di sumber data. Sama seperti fungsi lainnya, ini bersifat suspend.*/
    suspend fun updateItem(item: Item)
}
