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

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat

    /*ItemEntryViewModel adalah kelas ViewModel yang mengelola dan memvalidasi
    data item untuk dimasukkan ke dalam database.*/
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /*itemUiState menyimpan status tampilan saat ini dari item, seperti detail item dan validitas input.*/
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /*updateUiState digunakan untuk memperbarui status UI dengan detail item baru dan memvalidasi input.*/
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /*saveItem menyimpan item ke database jika inputnya valid.*/
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    /*validateInput memeriksa apakah input (nama, harga, dan jumlah) sudah lengkap dan tidak kosong.*/
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

/*ItemUiState dan ItemDetails adalah data class untuk menyimpan status UI dan detail item.*/
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/*toItem() adalah fungsi ekstensi untuk mengonversi ItemDetails ke objek Item,
mengatur harga dan jumlah ke nilai default jika input tidak valid.*/
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

/*formatedPrice() mengonversi harga item ke format mata uang.*/
fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/*toItemUiState() dan toItemDetails() adalah fungsi ekstensi untuk mengonversi
objek Item ke dalam format yang sesuai untuk UI atau untuk penyimpanan di database.*/
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
