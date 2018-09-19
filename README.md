[![Travis (.org)](https://img.shields.io/travis/ballerinax/hello.svg?logo=travis)](https://travis-ci.org/ballerinax/hello)

# Ballerina Hello Extension
 
Annotation based build extension implementation for ballerina. 

## Features:
- Generate text file with the greetings 


## Supported Annotations:

### @hello:Greeting{}
|**Annotation Name**|**Description**|**Default value**|
|--|--|--|
|salutation|Greeting or Acknowledgement|Hello!|


## How to run

1. Download and install JDK 8 or later
2. Get a clone or download the source from this repository (https://github.com/ballerinax/hello)
3. Run the Maven command ``mvn clean  install`` from within the hello directory.
4. Copy ``target/hello-extension-0.970.0.jar`` file to ``<BALLERINA_HOME>/bre/lib`` directory.
5. Run ``ballerina build <bal filename>`` to generate artifacts.

The hello world artifacts will be created in a folder called target with following structure.
```bash
target/
├── outputfilename.txt
└── outputfilename.balx
```

### Annotation Usage Sample:
```ballerina
import ballerina/http;
import ballerinax/hello;

@hello:Greeting{salutation : "Guten Tag!"}
@http:ServiceConfig {
    basePath:"/helloWorld"
}
service<http:Service> helloWorld bind {port:9091}{
    sayHello (endpoint outboundEP, http:Request request) {
        http:Response response = new;
        response.setStringPayload("Hello, World from service helloWorld ! \n");
        _ = outboundEP -> respond(response);
    }
}
```
