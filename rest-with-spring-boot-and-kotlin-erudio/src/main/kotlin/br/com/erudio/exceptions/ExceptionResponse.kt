package br.com.erudio.exceptions

import java.util.Date

class ExceptionResponse (
    val timeStamp: Date,
    val message: String?, //Momentos não vem parametro
    val detail: String
    )