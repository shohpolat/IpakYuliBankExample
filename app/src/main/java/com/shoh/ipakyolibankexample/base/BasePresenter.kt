package com.shoh.ipakyolibankexample.base

import moxy.MvpPresenter

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>() {
}