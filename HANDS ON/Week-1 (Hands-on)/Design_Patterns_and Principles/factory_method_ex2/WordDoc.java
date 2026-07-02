package factory_method_ex2;

public class WordDoc implements Document{
    @Override
    public void open() {
        System.out.println("Opening Word Document...");
    }
}
