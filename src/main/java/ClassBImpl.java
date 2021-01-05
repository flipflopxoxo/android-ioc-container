import util.Inject;

public class ClassBImpl implements ClassB {

    @Inject
    private ClassA classA;

    @Override
    public void printClassAName() {
        classA.printClassAName();
    }

    @Override
    public void printClassBName() {
        System.out.println(getClass().getSimpleName());
    }
}
