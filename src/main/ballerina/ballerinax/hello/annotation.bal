documentation {Hello annotation configuration
    F{{salutation}} - Greeting
}
public type HelloConfiguration record {
    string salutation;
};

documentation {@hello:Greeting annotation configuration}
public annotation <service> Greeting HelloConfiguration;
