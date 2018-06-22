documentation {Hello annotation configuration
    F{{salutation}} - Greeting
}
public type HelloConfiguration {
    string salutation;
};

documentation {@hello:Greeting annotation configuration}
public annotation <service> Greeting HelloConfiguration;
