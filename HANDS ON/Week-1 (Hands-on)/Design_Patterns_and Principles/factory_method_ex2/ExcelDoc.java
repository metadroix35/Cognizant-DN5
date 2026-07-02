package factory_method_ex2;

public class ExcelDoc implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document...");
    }
}
