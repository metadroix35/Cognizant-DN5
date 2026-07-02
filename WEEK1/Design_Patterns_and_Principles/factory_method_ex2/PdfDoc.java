package factory_method_ex2;

public class PdfDoc implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF Document...");
    }
}
