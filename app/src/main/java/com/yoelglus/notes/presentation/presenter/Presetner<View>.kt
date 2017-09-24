package com.yoelglus.notes.presentation.presenter

abstract class Presetner<View>() {

    protected var view: View? = null

    fun takeView(view:View) {
        this.view = view
        onTakeView()
    }
    fun dropView() {
        view = null
        onDropView()
    }

    protected open fun onTakeView() {

    }

    protected open fun onDropView() {

    }

}