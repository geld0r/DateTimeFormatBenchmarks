#  DateTimeFormatBenchmarks

JMH based benchmarks to evaluate parsing a timestamp (with zone) to a ZonedDateTime (single threaded case)

##  Participants:
* java.time.ZonedDateTime#parse with java.time.format.DateTimeFormatter
* org.apache.commons.lang3.time.FastDateFormat (with conversion)
* org.joda.time.format.DateTimeFormat (with conversion)

## To build & run
`gradlew jmh`

## Info
* Warmup: 20 iterations, 1 s each
* Measurement: 20 iterations, 1 s each
* Timeout: 10 min per iteration
* Threads: 1 thread, will synchronize iterations
* Benchmark mode: Throughput, ops/time

## Results
* Intel Core i7 3632QM
* JMH 1.11.3
* JDK 1.8.0_11, VM 25.11-b03

| Benchmark                                     | Mode  | Cnt | Score      | Error      | Units |
| ----                                          | ----- | --- | ---------: | ---------: | ----- |
| ZonedDateTimeBenchmark.zonedDateTimeParse     | thrpt | 200 |  98270,686 | ±  407,439 | ops/s |
| ZonedDateTimeBenchmark.fastDateFormat         | thrpt | 200 | 594823,336 | ± 3296,509 | ops/s |
| ZonedDateTimeBenchmark.jodaDateTimeFormatter  | thrpt | 200 | 728459,747 | ± 5566,384 | ops/s |

---
* Intel Core i5-6600
* JMH 1.11.3
* JDK 1.8.0_101, 64-bit server VM 25.101-b13

| Benchmark                                     | Mode  | Cnt | Score       | Error      | Units |
| ----                                          | ----- | --- | ----------: | ---------: | ----- |
| ZonedDateTimeBenchmark.zonedDateTimeParse     | thrpt | 200 | 1312841.215 | ± 5033.865 | ops/s |
| ZonedDateTimeBenchmark.fastDateFormat         | thrpt | 200 |  911401.668 | ± 9761.407 | ops/s |
| ZonedDateTimeBenchmark.jodaDateTimeFormatter  | thrpt | 200 | 1184447.857 | ± 9598.121 | ops/s |

---
* Intel Atom N270
* JMH 1.11.3
* JDK 1.8.0_102, 32-bit Client VM 25.102-b14

| Benchmark                                     | Mode  | Cnt | Score     | Error      | Units |
| ----                                          | ----- | --- | --------: | ---------: | ----- |
| ZonedDateTimeBenchmark.zonedDateTimeParse     | thrpt | 200 | 28216.929 | ± 1077.058 | ops/s |
| ZonedDateTimeBenchmark.fastDateFormat         | thrpt | 200 | 20804.663 | ±  650.399 | ops/s |
| ZonedDateTimeBenchmark.jodaDateTimeFormatter  | thrpt | 200 | 30044.550 | ±  493.878 | ops/s |
