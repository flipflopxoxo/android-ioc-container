import util.Inject;

public class ClassAImpl implements ClassA {

    @Inject
    private ClassB classB;

    @Override
    public void printClassBName() {
       classB.printClassBName();
    }

    @Override
    public void printClassAName() {
        System.out.println(getClass().getSimpleName());
    }

}
