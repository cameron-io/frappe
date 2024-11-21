# OOP Core Concepts

-   Encapsulation:
    -   implementation & Data Hiding, eg. via Getter/Setter
-   Inheritance:

    -   acquire properties of the superclass
    -   in Java, a sub-class can only have 1 superclass

-   Polymorphism:
    -   relates to inheritance
    -   1-to-many class relationship
    -   sub-classes may override methods of the superclass
        -   known as "runtime" polymorphism
        -   rules:
            -   same return type + args
            -   access-leval cannot be made more restrictive
            -   cannot override final/static methods
            -   cannot override ctor
    -   method overloading:
        -   methods w/same name but diff type/no. of args
-   Abstraction:
    -   provides only essential information about class
    -   achieved through abstract classes & interfaces:
        -   if classes are declared as abstract, sub-casses must implement its abstract methods
        -   interfaces may only contain static final variables
        -   interface can extend other interfaces
        -   a class can extend any number of interfaces.

Design Patterns:

-   Builder
    -   created to resolve issues with factory pattern
    -   for complex, highly configurable classes
        -   too may args, difficult to maintain ordering
        -   params might be optional:
            -   with factory patterns, these are forced to be passed as null values.
    -   steps:
        1. static nested class as the builder for the outer class
        2. builder needs public ctor with required attr as params
        3. builder needs methods for setting optional params
        4. finally, build() calls private ctor -> outer class
-   Factory
-   Singleton
-   Dependency Injection

SOLID Principles

-   Single Responsibility Principle:
    -   closely related to concepts: low coupling & high cohesion
    -   for example:
        InvoiceService > AddInvoice > DeleteInvoice > ValidateInvoice > SendEmail > ErrorLogging
        should be:
        InvoiceService > AddInvoice > DeleteInvoice
        ValidateService > Validate Invoice
        EmailService > SendEmail
        LoggerService > ErrorLogging
-   Open-Close Principle:
    -   modules, classes, functions etc. should be open for extension, close for modification:
        -   a module/class should be extendible without needing to alter its source code
    -   applications:
        -   creating derived classes to extend new functionality to base class.
        -   allow the client to access base class via abstract interface:
            -   use compositional design-patterns, eg. Strategy/Decorator
    -   benefit:
        -   decouples new from existing functionality
        -   thus, improves quality assurance (QA).
-   Liskov Substitution Principle:
    -   it should be possible to replace an instance of a parent class with the child class without affecting the behaviour of the base class instance.
        ```
        Animal a = new Cat();   // upcast to superclass 'Animal'
        ((Cat) a).makeSound();  // downcast to 'Cat' to access the cat's specific 'makeSound' method
        ```
-   Interface Segregation Principle:
    -   Clients should not be forced to implement any methods they do not use
        -   Larger interfaces should, thus, be split into numerous, smaller, more focused interfaces as required.
-   Dependency Inversion Principle:
    -   high-level classes should never depend on low-level implementations of other classes.
        -   instead, both should depend on abstractions.
