import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClassAImplTest {

    private DependencyContainer container;
    private ClassB classB;
    private ClassA classA;

    @Before
    public void setup(){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClassAImpl.class);
        classes.add(ClassBImpl.class);
        container = new DependencyContainer(classes);

        classA = container.getInstance(ClassA.class);
        classB = mock(ClassB.class);
    }

    @Test
    public void whenPrintClassBName_thenClassBCalled() {
        doNothing().when(classB).printClassBName();
        classA.printClassAName();
        verify(classB,times(1)).getClass().getSimpleName();
    }
}