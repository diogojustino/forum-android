package com.moveis.forum.restservice

interface ICallbackResponse<T> {
    fun success(instance: T)
}