# resilience4j-spring-boot2-maven
Micro-Services with circuit breaker feature using resilence4j


This is example project which is implemented using spring-boot-2 and resilence4j and maven.

To see circuit breaker activated call following url several time 

http://localhost:8080/backendA/search/a

http://localhost:8080/backendA/futureFailure

your should see below message 

Recovered specific CallNotPermittedException: io.github.resilience4j.circuitbreaker.CallNotPermittedException: CircuitBreaker 'backendA' is OPEN and does not permit further calls
