package com.nik.zettai.webservice

import com.nik.zettai.domain.ListName
import com.nik.zettai.domain.ToDoItem
import com.nik.zettai.domain.ToDoList
import com.nik.zettai.domain.User
import org.http4k.core.HttpHandler
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    val items = listOf("write chapter", "insert code", "draw diagrams")
    val toDoList = ToDoList(ListName("book"), items.map(::ToDoItem))
    val lists = mapOf(User("milan") to listOf(toDoList))

    val app: HttpHandler = Zettai(lists)
    app.asServer(Jetty(8080)).start()
    println("Server started at http://localhost:8080/todo/milan/book")
}