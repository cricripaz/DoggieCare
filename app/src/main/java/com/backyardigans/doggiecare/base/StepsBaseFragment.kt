package com.backyardigans.doggiecare.base

import androidx.fragment.app.Fragment

abstract class StepsBaseFragment: Fragment() {
    var onSuccess: (() -> Unit)? = null
    var onError: ((error: String) -> Unit)? = null

    fun setOnSuccessListener(callback: (() -> Unit)) {
        onSuccess = callback
    }

    fun setOnErrorListener(callback: ((error: String) -> Unit)) {
        onError = callback
    }
}