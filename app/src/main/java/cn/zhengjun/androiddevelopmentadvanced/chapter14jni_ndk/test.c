#include "cn_zhengjun_androiddevelopmentadvanced_chapter14jni_ndk_JniTest.h"
#include <studio.h>

JNIEXPORT jstring JNICALL Java_cn_zhengjun_androiddevelopmentadvanced_chapter14jni_1ndk_JniTest_get
  (JNIEnv *env, jobject thiz){
        puts("invoke from C");
        return (*env)->NewStringUTF(env,"new string from JNI!");
  }

JNIEXPORT void JNICALL Java_cn_zhengjun_androiddevelopmentadvanced_chapter14jni_1ndk_JniTest_set
    (JNIEnv *env, jobject thiz, jstring s){
        puts("invoke from C");
        char* string = (char*) (*env)->GetSringUTFChars(env,string,NULL);
        printf("%s\n",string);
        (*env)->ReleaseStringUTFChars(env,s,string);
    }