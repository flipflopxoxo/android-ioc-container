import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DependencyContainerTest {

    private DependencyContainer container;

    @Before
    public void setup(){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClassAImpl.class);
        classes.add(ClassBImpl.class);
        container = new DependencyContainer(classes);
    }

    @Test
    public void getClassAInstance() {
        ClassA classA = container.getInstance(ClassA.class);
        assertNotNull(classA);
    }

    @Test
    public void getClassBInstance() {
        ClassB classB = container.getInstance(ClassB.class);
        assertNotNull(classB);
    }

    @Test
    public void getNonExistentInstance() {
        ClassC classC = container.getInstance(ClassC.class);
        assertNull(classC);
    }
}