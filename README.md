[![Travis (.org)](https://img.shields.io/travis/ballerinax/hello.svg?logo=travis)](https://travis-ci.org/ballerinax/hello)

# Ballerina Hello Extension
 
Annotation based build extension implementation for ballerina. 

## Features:
- Generate text file with the greetings 

## Compatibility
|                     |    Version     |
|:-------------------:|:--------------:|
| Ballerina Language  | 1.2.x   |
| JDK | 1.8.x or later          |

## Supported Annotations:

### @hello:Greeting{}
|**Annotation Name**|**Description**|**Default value**|
|--|--|--|
|salutation|Greeting or Acknowledgement|Hello!|


## How to run

1. Download and install JDK 8 or later.
2. Get a clone or download the source from this repository (https://github.com/ballerinax/hello)
3. Run the gradle build command ``gradle build`` from within the hello-extension directory.
4. Copy ``build/libs/hello-extension-1.0-SNAPSHOT.jar`` file to ``<BALLERINA_HOME>/bre/lib`` directory.
5. Run ``ballerina build greet`` from hello-world to generate the artifacts.

The ``target/greetings/greet.txt`` file should contain the following text: ``Guten Tag! from salutation()``

### Annotation Usage Sample:
```ballerina
import ballerina/http;
import ballerina/log;
import ballerinax/hello;

@hello:Greeting { salutation: "Guten Tag!" }
@http:ServiceConfig {
    basePath: "/helloWorld"
}
service helloWorld on new http:Listener(9091) {
    resource function sayHello(http:Caller caller, http:Request request) {
        http:Response response = new;
        response.setTextPayload("Hello, World from service helloWorld ! \n");
        var responseResult = caller->respond(response);
        if (responseResult is error) {
            log:printError("error occurred while responding back to client", err = responseResult);
        }
    }
}
```
