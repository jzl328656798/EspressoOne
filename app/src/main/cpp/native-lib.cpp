#include <jni.h>
#include <string>

//extern "C" JNIEXPORT jstring JNICALL
//Java_com_cadillac_sc_myapplication_MainActivity_stringFromJNI(
//        JNIEnv* env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}
extern "C"
JNIEXPORT jstring JNICALL
Java_two_example_shen_yue_espressoprojectone_test16_Test16Activity1_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}