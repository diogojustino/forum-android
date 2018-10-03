package br.com.dispositivosmoveis.forumandroid.restservice

interface ICall<T> {
    fun success(instance: T)
}