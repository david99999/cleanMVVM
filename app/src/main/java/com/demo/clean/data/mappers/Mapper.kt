package com.demo.clean.data.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}