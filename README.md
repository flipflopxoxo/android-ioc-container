# Approach

## Implementation
I used a TDD Agile approach to developing this IoC application.

Considering that a DI Container holds references to dependencies and provides them when requested, it deemed necessary to develop a
central class (DependencyContainer) that is capable of instantiating and providing dependencies to the application.

Two classes were introduced: ClassAImpl and ClassBImpl. Additionally two interfaces were introduced for the classes, ClassA and ClassB respectively.
By using polymorphism, it made it possible to add the 'Impl' files to the dependency container and retrieve them as interfaces, thereby being able
to call the overridden methods in the 'Impl' classes. 

This application field injects dependencies into the classes that require them. Field injection was chosen over constructor injection because it
addresses a common problem of circular dependencies by not requiring a dependency to be provided to the constructor.

The 'Main' class in the application initialises the 'DependencyContainer' class, adds the ClassAImpl and ClassBImpl definitions and creates instances of them.
It then calls the 'performJob()' method that extracts ClassA and ClassB instances from the DependencyContainer and executes their methods.

Because the dependencies have been instantiated via field injection, the instances can now be used as if they were manually instantiated via a constructor.

A helper interface was implemented that annotates the fields to be injected with the @Inject annotation. This was done to screen out any other field types
such as Integer and String in the classes and ensure only @Inject fields were used.

## Testing
In order to test the application's functionality, it was necessary to test the instance added to the dependency container were returned as not null.
This was done using JUnit's assertNotNull() function, which indicated that a not-null instance was retrieved. This was done for ClassA and ClassB instances.

A third class, namely ClassC was introduced to test that when an instance was requested that was not added to the dependency container, it should return null.
This was checked using JUnit's assertNull() function, indicating that no instances were returned.

For the 'ImplTest' classes, it was necessary to test whether the dependencies in the 'Impl' classes instantiated successfully and their methods could be 
called. This was achieved using the Mockito testing framework, where one of the classes would be instantiated using the dependency container and the other
would be instantiated as a Mock object.

The Mock tests concluded that the field injected dependencies were instantiated successfully and making the correct calls. 

## Further Improvements
- This project could be further improved by implementing functionality that could scan the project structure and add classes to the dependency container automatically. 
- The dependency container could use the Singleton pattern.

