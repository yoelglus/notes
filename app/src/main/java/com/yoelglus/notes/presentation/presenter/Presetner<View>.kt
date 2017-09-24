package com.yoelglus.notes.presentation.presenter

abstract class Presetner<View>() {

    protected var view: View? = null

    fun takeView(view:View) {
        this.view = view
        onTakeView()
    }
    fun dropView() {
        view = null
    }

    protected open fun onTakeView() {

    }

}