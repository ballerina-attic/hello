import ballerina/http;
import ballerinax/hello;

@hello:Greeting { salutation: "Guten Tag!" }
@http:ServiceConfig {
    basePath: "/helloWorld"
}
service helloWorld on new http:Listener(9091) {
    resource function sayHello(http:Caller outboundEP, http:Request request) {
        http:Response response = new;
        response.setTextPayload("Hello, World from service helloWorld ! \n");
        _ = outboundEP -> respond(response);
    }
}
