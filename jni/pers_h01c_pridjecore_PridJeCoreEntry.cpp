#include "pers_h01c_pridjecore_PridJeCoreEntry.h"
#include "Python.h"

static PyObject *p_module = nullptr;
static PyObject *p_func = nullptr;

JNIEXPORT jint JNICALL Java_pers_h01c_pridjecore_PridJeCoreEntry_InitPridJeCore(JNIEnv * env, jobject obj){
    Py_Initialize();
    int is_init = Py_IsInitialized();
    if(is_init>0){
        PyRun_SimpleString("import sys");
        PyRun_SimpleString("sys.path.append('./PridJe')");
        p_module = PyImport_ImportModule("PridJeCorePortal");
        if(!p_module){return -3;}
        p_func = PyObject_GetAttrString(p_module, "process_function");
        if(!p_func){return -2;}
        return is_init;
    }
    return -1;
}

JNIEXPORT void JNICALL Java_pers_h01c_pridjecore_PridJeCoreEntry_UnInitPridJeCore(JNIEnv * env, jobject obj){
    Py_Finalize();
}

JNIEXPORT jstring JNICALL Java_pers_h01c_pridjecore_PridJeCoreEntry_ProcessFunction(JNIEnv * env, jobject obj, jstring input_j_string){
    if(!p_module|!p_func){return nullptr;}
    const char* param = (char*)env->GetStringUTFChars(input_j_string, NULL);
    PyObject *pyRet = PyObject_CallFunction(p_func, "s", param);
    jstring ret_string = env->NewStringUTF(PyUnicode_AsUTF8(pyRet));
    env->ReleaseStringUTFChars(input_j_string, param);
    return ret_string;
}
