package com.osamaaftab.dindinn.domain.usecase.base

abstract class UseCase<in I, out T> {

    abstract fun run(input: I? = null): T

}