package com.bosch.smartshopping.repositories

class LocalRepository : LocalIRepository{

    override fun saveDataInPref(saveUploadData: (okMessage: String) -> Unit, message: String) {
        // create a shared prefernce and store this information inside in local storage
        // and reply back to the caller using lambda method passed
        saveUploadData.invoke("cached status for: $message")
    }

    override fun saveDataInDb() {

    }

}