import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClassBImplTest {

    private DependencyContainer container;
    private ClassA classA;
    private ClassB classB;

    @Before
    public void setup(){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClassAImpl.class);
        classes.add(ClassBImpl.class);
        container = new DependencyContainer(classes);

        classB = container.getInstance(ClassB.class);
        classA = mock(ClassA.class);
    }

    @Test
    public void whenPrintClassAName_thenClassACalled() {
        doNothing().when(classA).printClassAName();
        classB.printClassAName();
        verify(classA,times(1)).getClass().getSimpleName();
    }
}