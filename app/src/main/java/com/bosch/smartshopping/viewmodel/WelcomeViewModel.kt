package com.bosch.smartshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bosch.smartshopping.navigators.WelcomeNavigator
import com.bosch.smartshopping.repositories.LocalIRepository
import com.bosch.smartshopping.repositories.LocalRepository
import com.bosch.smartshopping.repositories.NetworkIRepository
import java.lang.ref.WeakReference

class WelcomeViewModel<T>(val localRepository: LocalIRepository, val networkRepository: NetworkIRepository): ViewModel() {
    var screenName : MutableLiveData<String> = MutableLiveData()
    var messageFRomService = MutableLiveData<String>()
    private lateinit var navigator:WeakReference<T>

    fun setScreenName(screenName : String) {
        this.screenName.value = screenName
    }

    fun setNavigator(navigator:T) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator() : T? {
        return navigator.get()
    }

    fun uploadData() {
        ( getNavigator() as WelcomeNavigator).uploadData()
    }

    fun readCartItems() {
        networkRepository.getCartItems()
    }

    fun saveDataLocally(message:String) {
        localRepository.saveDataInPref(( {
            messageFRomService.postValue(it)
        }), message = message)
    }

}