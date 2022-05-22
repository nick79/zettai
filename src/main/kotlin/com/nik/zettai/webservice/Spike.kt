package com.nik.zettai.webservice

import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

val app: HttpHandler = routes(
    "/greetings" bind Method.GET to ::greetings,
    "/data" bind Method.POST to ::receiveData
)

fun receiveData(req: Request): Response = Response(Status.CREATED).body("Received ${req.bodyString()}")

fun greetings(req: Request): Response = Response(Status.OK).body(htmlPage)

val htmlPage = """
<html>
    <body>
        <h1 style="text-align:center; font-size:3em;">Hello Functional World!</h1>
    </body>
</html>"""

fun main() {
    app.asServer(Jetty(8080)).start()
}