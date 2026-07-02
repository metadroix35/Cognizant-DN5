package factory_method_ex2;

public class ExcelDocFactory extends DocFactory{
    @Override
    public Document createDocument() {
        return new ExcelDoc();
    }
}
