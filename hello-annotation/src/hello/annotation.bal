# This record defines the fields of the @hello:Greeting annotation. 
#
# + salutation - The greeting message
public type HelloConfiguration record {|
    string salutation = "Hello!";
|};

# Define an annotation named `Greeting`. Its type is `HelloConfiguration` and it can be
# attached to services. 
public annotation HelloConfiguration Greeting on function;
