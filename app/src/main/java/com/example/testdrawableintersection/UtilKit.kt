@file:JvmMultifileClass

package com.example.testdrawableintersection



object UtilKt {

    @JvmStatic
    fun getRidOfMillis(s: String?): String? {
        return s?.replace("\\.[0-9]*Z".toRegex(), "Z") ?: s
    }

    @JvmStatic
    fun list2String(list: List<String>): String = list.joinToString(" ")


    @JvmStatic
    fun historyList2String(list: List<String>): String = list.joinToString("\n") { it.toString() }


}