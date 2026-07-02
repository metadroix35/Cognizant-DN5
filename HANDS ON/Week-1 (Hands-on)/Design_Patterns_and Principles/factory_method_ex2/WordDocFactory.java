package factory_method_ex2;

public class WordDocFactory extends DocFactory{
    @Override
    public Document createDocument() {
        return new WordDoc();
    }
}
