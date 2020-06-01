package two.example.shen.yue.espressoprojectone.event

import java.lang.reflect.Method

/**
 * Author: Queen
 * Date: 2020/6/1 1:18 PM
 * Describe: QueenSubscriberMethod
 */
data class QueenSubscriberMethod(val method: Method, val threadMode: QueenThreadMode, val javaClass: Class<*>)