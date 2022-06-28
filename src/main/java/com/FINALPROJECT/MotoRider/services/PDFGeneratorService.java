package com.FINALPROJECT.MotoRider.services;

import com.FINALPROJECT.MotoRider.models.*;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Service
public class PDFGeneratorService {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    ProductService productService;

    @Autowired
    MotorcycleService motorcycleService;

    public void export(HttpServletResponse response) throws IOException, DocumentException {

        Receipt factura = receiptService.getReceipt(1);
        Client client = factura.getClient();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(new Color(28, 186, 213));

        Paragraph paragraph = new Paragraph("RECEIPT: 0000000"+factura.getId(), fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("MOTO RIDER SHOP", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.add(new Phrase(Chunk.NEWLINE));
        Paragraph paragraph3 = new Paragraph("Client data:", fontParagraph);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph3.add(new Phrase(Chunk.NEWLINE));
        Paragraph paragraph4 = new Paragraph("Surname and name: "+ client.getLastName()+" "+client.getFirstName(),fontParagraph);
        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph5 = new Paragraph("Address: "+ client.getAddress(),fontParagraph);
        paragraph5.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph6 = new Paragraph("Email: "+ client.getEmail(),fontParagraph);
        paragraph6.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph7 = new Paragraph("Contact: "+ client.getContact(),fontParagraph);
        paragraph7.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph7.add(new Phrase(Chunk.NEWLINE));
        paragraph7.add(new Phrase(Chunk.NEWLINE));

   /*     Image logo = Image.getInstance("https://img.poki.com/cdn-cgi/image/quality=78,width=600,height=600,fit=cover,f=auto/https://img.poki.com/2c6d5a46cdbceada277c870ce1c389ee.jpg");
        logo.setAbsolutePosition(0,200);
        logo.getImageMask();
        document.add(logo);*/

        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);
        document.add(paragraph7);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setBackgroundColor(Color.cyan);
        table.addCell("Product");
        table.addCell("Quantity of products");
        table.addCell("Unit price");
        table.addCell("Amount");


        Set<Double> total = new HashSet<>();

        //EXTRAIGO LAS ORDENES DE PRODUCTOS LA FACTURA

        Set<ProductPurchaseOrder> ppo = factura.getProductPurchases();
        if(ppo.size()>0) {
            ppo.stream().forEach(prod -> {//
                Product p = productService.getProduct(prod.getProducts().getId());

                double subTotalProduct = prod.getNumOfProducts() * p.getPrice();
                total.add(subTotalProduct);

                PdfPCell c1 = new PdfPCell(new Phrase(p.getType()));
                PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(prod.getNumOfProducts())));
                PdfPCell c3 = new PdfPCell(new Phrase(String.valueOf(p.getPrice())));
                PdfPCell c4 = new PdfPCell(new Phrase(String.valueOf(subTotalProduct)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBorderWidthBottom(0);
                c1.setBorder(Rectangle.NO_BORDER);
                c2.setBorder(Rectangle.NO_BORDER);
                c3.setBorder(Rectangle.NO_BORDER);
                c4.setBorder(Rectangle.NO_BORDER);
                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);

            });
        }
        //EXTRAIGO LAS ORDENES DE MOTOS DE LA FACTURA
        Set<MotorcyclePurchaseOrder>mpo = factura.getMotorcyclePurchaseOrders();
        if (mpo.size()>0){
             mpo.stream().forEach(motor ->{
                 Motorcycle m = motorcycleService.getMoto(motor.getMotorcycle().getId());

                 double subTotalMoto = motor.getNumOfProducts()*m.getPrice();
                 total.add(subTotalMoto);

                 PdfPCell c1 = new PdfPCell(new Phrase(m.getModel()));
                 PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(motor.getNumOfProducts())));
                 PdfPCell c3 = new PdfPCell(new Phrase(String.valueOf(m.getPrice())));
                 PdfPCell c4 = new PdfPCell(new Phrase(String.valueOf(subTotalMoto)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                 c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                 c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                 c1.setBorder(Rectangle.NO_BORDER);
                 c2.setBorder(Rectangle.NO_BORDER);
                 c3.setBorder(Rectangle.NO_BORDER);
                 c4.setBorder(Rectangle.NO_BORDER);
                 table.addCell(c1);
                 table.addCell(c2);
                 table.addCell(c3);
                 table.addCell(c4);

            });
        }

        Double totalFinal = total.stream().reduce(Double::sum).orElse(0.0);


        table.addCell("Total: ");
        table.addCell(String.valueOf(totalFinal));

        document.add(table);
        document.close();
    }

}
