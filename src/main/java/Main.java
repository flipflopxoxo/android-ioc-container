import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Entry point for the application
 */
public class Main {

    /**
     * Function initialises the dependency container and performs calls
     * on the dependencies
     * @param args
     */
    public static void main(String[] args) {
        DependencyContainer container = createDependencyContainer();
        performJob(container);
    }

    /**
     * Function creates a DependencyContainer instance
     * @return DependencyContainer instance
     */
    private static DependencyContainer createDependencyContainer(){
        //Create a set of generic type classes
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClassAImpl.class);
        classes.add(ClassBImpl.class);

        return new DependencyContainer(classes);
    }

    /**
     * Function calls dependencies from the container and calls their methods
     * @param container
     */
    private static void performJob(DependencyContainer container){
        ClassA classA = container.getInstance(ClassA.class);
        ClassB classB = container.getInstance(ClassB.class);

        classA.printClassBName();
        classB.printClassAName();

    }
}