mkdir ../java/out/
$JAVA_HOME/bin/javac -d ../java/out/  ../java/src/pers/h01c/pridjecore/PridJeCoreEntry.java
cd ../java/out/ && $JAVA_HOME/bin/jar -cvf ../../lib/PridJeCoreEntry.jar pers/h01c/pridjecore/*