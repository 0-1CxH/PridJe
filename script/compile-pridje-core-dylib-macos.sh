g++ -I$PYTHON_HOME/include/python3.9 -I$JAVA_HOME/include -I$JAVA_HOME/include/darwin  -L$PYTHON_HOME/lib -lpython3.9 -dynamiclib -shared ../jni/pers_h01c_pridjecore_PridJeCoreEntry.cpp -o ../lib/libPridJeCore.dylib
install_name_tool -add_rpath $PYTHON_HOME/lib ../lib/libPridJeCore.dylib