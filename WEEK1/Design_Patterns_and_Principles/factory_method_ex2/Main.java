package factory_method_ex2;

public class Main {
    public static void main(String[] args) {

        DocFactory wordFactory = new WordDocFactory();
        Document word = wordFactory.createDocument();
        word.open();

        DocFactory pdfFactory = new PdfDocFactory();
        Document pdf = pdfFactory.createDocument();
        pdf.open();

        DocFactory excelFactory = new ExcelDocFactory();
        Document excel = excelFactory.createDocument();
        excel.open();
    }
}
