# Spring Boot Event Processing with Kafka

This code sample has been written as an exercise for techniques described in
[Building Your First Event-Driven Application with Spring Boot and Apache Kafka](https://medium.com/cloud-native-daily/building-your-first-event-driven-application-with-spring-boot-and-apache-kafka-13c7adc8159b)

It is left to the reader to properly split the solution into:
* Models module (shared by the other two)
* Producer module
* Consumer module

## K6

The code also includes sample k6 script. To execute for 100,000 iterations across 100 virtual 
sessions use:
```shell
k6 run --iterations 100000 --vus 100  .\simple.k6.js
```

On a laptop with AMD Ryzen 9 7945HX (with ESET antivirus going nuts scanning):
```text

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: .\simple.k6.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 10m30s max duration (incl. graceful stop):
           * default: 100000 iterations shared among 100 VUs (maxDuration: 10m0s, gracefulStop: 30s)


     ✓ Status is 200
     ✓ Success

     checks.........................: 100.00% ✓ 200000      ✗ 0
     data_received..................: 10 MB   502 kB/s
     data_sent......................: 28 MB   1.4 MB/s
     http_req_blocked...............: avg=2.14µs  min=0s med=0s      max=2.08ms   p(90)=0s      p(95)=0s
     http_req_connecting............: avg=0s      min=0s med=0s      max=0s       p(90)=0s      p(95)=0s
     http_req_duration..............: avg=19.77ms min=0s med=18.55ms max=366.12ms p(90)=29.15ms p(95)=33.11ms
       { expected_response:true }...: avg=19.77ms min=0s med=18.55ms max=366.12ms p(90)=29.15ms p(95)=33.11ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 100000
     http_req_receiving.............: avg=45.96µs min=0s med=0s      max=22.19ms  p(90)=0s      p(95)=503.2µs
     http_req_sending...............: avg=13.53µs min=0s med=0s      max=13.17ms  p(90)=0s      p(95)=0s
     http_req_tls_handshaking.......: avg=0s      min=0s med=0s      max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=19.71ms min=0s med=18.49ms max=365.85ms p(90)=29.08ms p(95)=33ms
     http_reqs......................: 100000  5019.573022/s
     iteration_duration.............: avg=19.9ms  min=0s med=18.67ms max=367.02ms p(90)=29.28ms p(95)=33.24ms
     iterations.....................: 100000  5019.573022/s
     vus............................: 100     min=100       max=100
     vus_max........................: 100     min=100       max=100


running (00m19.9s), 000/100 VUs, 100000 complete and 0 interrupted iterations
default ✓ [======================================] 100 VUs  00m19.9s/10m0s  100000/100000 shared iters
```
