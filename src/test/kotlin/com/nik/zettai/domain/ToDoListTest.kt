package com.nik.zettai.domain

import com.nik.zettai.tooling.*
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ToDoListTest {
    val validCharset = uppercase + lowercase + digits + "-"
    val invalidCharset = " !@#$%^&*()_+={}[]|:;'<>,./?\u2202\u2203\u2204\u2205"

    @Test
    fun `Valid names are alphanum_hiphen between 1 and 40 chars length`() {
        stringsGenerator(validCharset, 1, 40)
            .take(100)
            .forEach {
                expectThat(ListName.fromUntrusted(it)).isEqualTo(ListName.fromTrusted(it))
            }
    }

    @Test
    fun `Name cannot be empty`() {
        expectThat(ListName.fromUntrusted("")).isEqualTo(null)
    }

    @Test
    fun `Names longer than 40 chars are not valid`() {

        stringsGenerator(validCharset, 41, 200)
            .take(100)
            .forEach {
                expectThat(ListName.fromUntrusted(it)).isEqualTo(null)
            }
    }

    @Test
    fun `Invalid chars are not allowed in the name`() {

        stringsGenerator(validCharset, 1, 30)
            .map { substituteRandomChar(invalidCharset, it) }
            .take(1000).forEach {
                expectThat(ListName.fromUntrusted(it)).isEqualTo(null)
            }
    }

}