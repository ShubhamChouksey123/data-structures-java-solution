package lambda;

public class GreetingImpl implements  Greeting{
    @Override
    public void perform() {
        System.out.println("Hello world greeting from impl class !");
    }
}
