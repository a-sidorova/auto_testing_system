# Automatic Testing Sytem
Course Distributed Systems, Second lab work, autumn 2021

*Language*: Java (Java 8)

*Technology*: WebServices (SOAP)

### How generate code for clients
```
wsimport http://localhost:8888/AutoTestingSystem?wsdl -keep -d "<path_to_repository>\auto_testing_system\client\src\main\java\."
```

The utility `wsimport` is in the directory `jdk1.8.0_271\bin`.


© Copyright Sidorova Alexandra, 2021