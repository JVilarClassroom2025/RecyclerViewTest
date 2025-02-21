package es.uji.jvilar.recyclerviewtest.phoneactivity

import androidx.lifecycle.ViewModel

class PhoneViewModel: ViewModel() {
    var currentName: String = ""
    var currentNumber: String = ""
    var view: PhoneView? = null
    fun onShowPhoneRequested(phone: Phone) {
        currentName = phone.name
        currentNumber = phone.number
        view?.showPhoneDialog()
    }
}