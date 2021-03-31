# testProject
demonstrates ui testing using seleniums page object model factory pattern. this repository contains as well backend api tests.

## frontend ui testing
contains most important functionalities to implement fe testing. 
- Concepts of how to replace conditional with polymorphism. 
- Selenium build in Factory pattern  
- usage of singleton
- config properties reading
- logging mechanism
- screenshots in case of failure

## backend api testing
demonstration of most important functions
- put, post, get operations
- reading of json: casting, mapping and streaming capabilities
- usage of data transfer object dto

## vm args to provide
```
-Denv=test
```
## run tests
```
mvn test -Denv=test
```

to provoke ApplicationException do this:
```
mvn test 
```
## run with surefire
```
mvn clean surefire-report:report -Denv=test 
```
