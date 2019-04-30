# Hello annotation configuration
#
# + salutation - Greeting
public type HelloConfiguration record {|
    string salutation = "Hello!";
|};

# @hello:Greeting annotation configuration
public annotation <service> Greeting HelloConfiguration;
