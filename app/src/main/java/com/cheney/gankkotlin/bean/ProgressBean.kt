package com.cheney.gankkotlin.bean

data class ProgressBean(val status: Status) {

    var message: String = ""
    var cancelable: Boolean = false


    constructor(status: Status, message: String, cancelable: Boolean) : this(status) {
        this.message = message
        this.cancelable = cancelable
    }


    enum class Status {
        LOADING, FINISHED
    }

    fun isFinished(): Boolean {
        return status == Status.FINISHED
    }

    companion object {
        fun loading(message: String, cancelable: Boolean = false): ProgressBean {
            return ProgressBean(Status.LOADING, message, cancelable)
        }

        fun finished(): ProgressBean {
            return ProgressBean(Status.FINISHED)
        }
    }
}