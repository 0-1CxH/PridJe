mkdir ../java/out/
$JAVA_HOME/bin/javac -Djava.ext.dirs=../java/lib -classpath ../java/src -d ../java/out/  ../java/src/pers/h01c/pridjemultiplex/PridJeJsonMultiplexer.java
cd ../java/out/ && $JAVA_HOME/bin/jar cvfm  ../../lib/PridJeJsonMultiplexer.jar  ../manifests/manifest-jsonmx pers/h01c/pridjemultiplex/*.class  pers/h01c/pridjecore/*.class
