import util.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class is used to initialise and provide instances with dependencies to the application
 */

public class DependencyContainer {

    private final Set<Object> dependencies = new HashSet<>();

    public DependencyContainer(Collection<Class<?>> serviceClasses) {
        try {
            initDependencies(serviceClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function instantiates all classes defined in the project
     */
    private void initDependencies(Collection<Class<?>> classes) throws Exception {
        //Instantiate the classes using default (compiler) constructor
        for (Class<?> clazz : classes) {
            Constructor<?> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            dependencies.add(constructor.newInstance());
        }

        //Check if the class has any fields as dependencies
        for (Object instance : dependencies) {
            for (Field field : instance.getClass().getDeclaredFields()) {

                //Check for the @util.Inject annotation field to screen out Integer and String classes
                if(!field.isAnnotationPresent(Inject.class)){
                    // this field is none of our business
                    continue;
                }
                //Check each field type
                Class<?> fieldType = field.getType();
                //Access private fields
                field.setAccessible(true);

                //Check the instances set for any matches
                for (Object matchingType : dependencies) {
                    if (fieldType.isInstance(matchingType)) {
                        //Initialise the field with the matching type
                        field.set(instance, matchingType);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * Function takes in a class parameter
     * and attempts to return an instance of that type
     * @param clazz
     */
    public <T> T getInstance(Class<T> clazz){
        for(Object dependency : dependencies){
            if(clazz.isInstance(dependency)){
                return (T) dependency;
            }
        }
        //No match was found
        return null;
    }

}
