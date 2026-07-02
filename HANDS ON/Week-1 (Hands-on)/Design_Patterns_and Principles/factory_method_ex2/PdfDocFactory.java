package factory_method_ex2;

public class PdfDocFactory extends DocFactory{
    @Override
    public Document createDocument() {
        return new PdfDoc();
    }
}
