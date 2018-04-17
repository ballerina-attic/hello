package ballerinax.hello;

@Description {value:"Hello annotation configuration"}
@Field {value:"salutation: Greeting or Acknowledgement"}
public type HelloConfiguration {
    string salutation;
};

@Description {value:"Configurations annotation for Docker"}
public annotation <service> Greeting HelloConfiguration;
