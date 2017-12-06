# gc-memory-test

Application Generation Garbage for Comparison of Garbage Collectors

- Install IntellijIdea with VisualVM Plugin
- Uses [GCViewer](https://github.com/chewiebug/GCViewer) for a generation summary from logs

Print settings used in example

```
-Xloggc:
-verbose:gc
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:+PrintGCTimeStamps
-XX:+PrintTenuringDistribution
-XX:+PrintGCApplicationConcurrentTime 
-XX:+PrintGCApplicationStoppedTime
```

Remove Optimizations

```
-XX:-DoEscapeAnalysis
```

### Serial Collector
```
-Xmx6g -Xloggc:logs/gc_serial.log -XX:+UseSerialGC -XX:-DoEscapeAnalysis -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationConcurrentTime  -XX:+PrintGCApplicationStoppedTime
```

### Parallel Collector
```
-Xmx6g -Xloggc:logs/gc_parallel.log -XX:+UseParallelGC -XX:-DoEscapeAnalysis -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationConcurrentTime  -XX:+PrintGCApplicationStoppedTime
```

### CMS Collector
```
-Xmx6g -Xloggc:logs/gc_cms.log -XX:+UseUseConcMarkSweepGC -XX:-DoEscapeAnalysis -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationConcurrentTime  -XX:+PrintGCApplicationStoppedTime
```

### G1 Collector
```
-Xmx6g -Xloggc:logs/gc_g1.log -XX:+UseG1GC -XX:-DoEscapeAnalysis -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationConcurrentTime  -XX:+PrintGCApplicationStoppedTime
```

### Generate Results

```
java -jar gcviewer.jar logs/gc_serial.log summary/serial.csv &&
java -jar gcviewer.jar logs/gc_parallel.log summary/parallel.csv &&
java -jar gcviewer.jar logs/gc_cms.log summary/cms.csv &&
java -jar gcviewer.jar logs/gc_g1.log summary/g1.csv
```