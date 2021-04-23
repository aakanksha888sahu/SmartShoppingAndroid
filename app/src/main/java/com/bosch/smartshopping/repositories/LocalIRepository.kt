package com.bosch.smartshopping.repositories

interface LocalIRepository {

    fun saveDataInPref( saveUploadData : ((okMessage: String) -> Unit), message: String)
    fun saveDataInDb()

}
