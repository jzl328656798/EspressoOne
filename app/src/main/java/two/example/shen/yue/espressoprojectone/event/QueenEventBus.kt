package two.example.shen.yue.espressoprojectone.event

/**
 * Author: Queen
 * Date: 2020/6/1 1:16 PM
 * Describe: QueenEventBus
 */
object QueenEventBus {

    private val cacheMap = hashMapOf<Any, MutableList<QueenSubscriberMethod>>()

    fun register(any: Any) {
        var list = cacheMap[any]
        if (list == null) {
            list = findMethods(any)
            cacheMap[any] = list
        }
    }

    fun post(any: Any) {
        cacheMap.forEach {
            it.value.forEach { methodItem ->
                if (methodItem.javaClass.isAssignableFrom(any.javaClass)) {
                    methodItem.method.invoke(it.key, any)
                }
            }
        }
    }

    private fun findMethods(any: Any): MutableList<QueenSubscriberMethod> {
        val list = mutableListOf<QueenSubscriberMethod>()
        any.javaClass.declaredMethods.forEach { methodItem ->
            methodItem.getAnnotation(QueenSubscribe::class.java)?.let {
                val parameterTypes = methodItem.parameterTypes
                if (parameterTypes.size == 1) {
                    list.add(QueenSubscriberMethod(methodItem, it.threadMode, parameterTypes[0]))
                }
            }
        }
        return list
    }

}