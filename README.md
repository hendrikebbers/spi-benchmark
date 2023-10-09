# spi-benchmark
Benchmark for Java service loader

The `code-generator` module contains the `GenerateThousandsOfServices` class that must be executed to create all the 
services that should be loaded by SPI.

Execution:

```bash
% hyperfine --prepare './mvnw -P custom-service-count clean verify -DnumberOfServices=100' 'java -jar sample-spi/target/app.jar'
```

## First results

Execution on M1 Max MacBook with 64 GB RAM and local SSD

### sample-empty
```bash
% hyperfine 'java -jar app.jar' 
Benchmark 1: java -jar app.jar
  Time (mean ± σ):      93.9 ms ±   2.3 ms    [User: 110.2 ms, System: 14.4 ms]
  Range (min … max):    90.9 ms … 101.3 ms    31 runs
```

### sample-module
```bash
% hyperfine 'java -jar app.jar' 
Benchmark 1: java -jar app.jar
  Time (mean ± σ):     118.5 ms ±   2.6 ms    [User: 155.3 ms, System: 17.8 ms]
  Range (min … max):   113.7 ms … 124.1 ms    24 runs
```

### sample-thousands-module (20 services)
```bash
% hyperfine 'java -jar app.jar' 
Benchmark 1: java -jar target/app.jar
  Time (mean ± σ):     120.3 ms ±   5.6 ms    [User: 157.6 ms, System: 17.6 ms]
  Range (min … max):   110.4 ms … 128.2 ms    23 runs
```

### sample-thousands-module (200 services)
```bash
% hyperfine 'java -jar app.jar' 
Benchmark 1: java -jar target/app.jar
  Time (mean ± σ):     171.0 ms ±   4.7 ms    [User: 287.8 ms, System: 27.8 ms]
  Range (min … max):   165.3 ms … 181.2 ms    17 runs
```

### sample-thousands-module (2_000 services)
```bash
% hyperfine 'java -jar app.jar' 
Benchmark 1: java -jar target/app.jar
  Time (mean ± σ):     405.4 ms ±   6.2 ms    [User: 1117.9 ms, System: 69.3 ms]
  Range (min … max):   397.1 ms … 416.5 ms    10 runs
```

### sample-thousands-module (20_000 services)
```bash
% hyperfine 'java -jar app.jar' 
Benchmark 1: java -jar app.jar
  Time (mean ± σ):      2.357 s ±  0.046 s    [User: 5.046 s, System: 0.398 s]
  Range (min … max):    2.317 s …  2.458 s    10 runs
```

