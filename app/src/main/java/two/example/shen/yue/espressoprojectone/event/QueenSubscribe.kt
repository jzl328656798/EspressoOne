package two.example.shen.yue.espressoprojectone.event

/**
 * Author: Queen
 * Date: 2020/6/1 1:27 PM
 * Describe: QueenSubscribe
 */
//@Target({ElementType.METHOD})
//@Retention(RetentionPolicy.RUNTIME)


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class QueenSubscribe(val threadMode: QueenThreadMode = QueenThreadMode.MAIN)

