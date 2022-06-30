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
import org.springframework.security.core.Authentication;
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

    @Autowired
    ClientService clientService;

    public void export(HttpServletResponse response, Authentication authentication) throws IOException, DocumentException {
        Client client = clientService.getCurrent(authentication);
        Receipt factura = client.getReceipts().get(client.getReceipts().size()-1);
        //int receiptId = client.getReceipts()
        //Receipt factura = receiptService.getReceipt(receiptId);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(new Color(86, 115, 229));

        Paragraph paragraph = new Paragraph("RECEIPT: 0000000"+factura.getId(), fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTotal.setSize(14);
        fontTotal.setColor(new Color(86, 115, 229));

        Font fontThead = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontThead.setSize(14);
        fontThead.setColor(Color.WHITE);

        Paragraph paragraph2 = new Paragraph("MOTO RIDER SHOP", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.add(new Phrase(Chunk.NEWLINE));
        Paragraph paragraph3 = new Paragraph("Client data:", fontParagraph);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph3.add(new Phrase(Chunk.NEWLINE));
        Paragraph paragraph4 = new Paragraph("Surname and name: "+ client.getLastName()+" "+client.getFirstName(),fontParagraph);
        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph5 = new Paragraph("Address: 34 N bayles",fontParagraph);
        paragraph5.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph8 = new Paragraph("Zip: 11050",fontParagraph);
        paragraph8.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph7 = new Paragraph("Contact: +1 5162825729 ",fontParagraph);
        paragraph7.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph6 = new Paragraph("Email: "+ client.getEmail(),fontParagraph);
        paragraph6.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph6.add(new Phrase(Chunk.NEWLINE));
        paragraph6.add(new Phrase(Chunk.NEWLINE));

        Image logo = Image.getInstance("https://cdn.discordapp.com/attachments/985294289091321861/991778398491922472/LOGO_LETRAS_PROJECTO_FINALFondo.png");
        logo.setAbsolutePosition(50,300);
        logo.scaleToFit(500,500);
        logo.getImageMask();
        document.add(logo);

        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph6);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setBackgroundColor(new Color(86, 115, 229));
        table.getDefaultCell().setBorderWidthRight(0);
        table.getDefaultCell().setBorderWidthLeft(0);
        table.getDefaultCell().setBorderWidthTop(0);
        table.getDefaultCell().setBorderWidthBottom(2);
        table.getDefaultCell().setPaddingBottom(5);


        PdfPCell cell1 = new PdfPCell(new Phrase("Product",fontThead));
        PdfPCell cell2 = new PdfPCell(new Phrase("Quantity of products",fontThead));
        PdfPCell cell3 = new PdfPCell(new Phrase("Unit price",fontThead));
        PdfPCell cell4 = new PdfPCell(new Phrase("Amount",fontThead));
        cell1.setBackgroundColor(new Color(86, 115, 229));
        cell2.setBackgroundColor(new Color(86, 115, 229));
        cell3.setBackgroundColor(new Color(86, 115, 229));
        cell4.setBackgroundColor(new Color(86, 115, 229));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBorder(Rectangle.NO_BORDER);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell3.setBorder(Rectangle.NO_BORDER);
        cell4.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);


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

        PdfPCell c1 = new PdfPCell(new Phrase());
        PdfPCell c2 = new PdfPCell(new Phrase());
        PdfPCell c3 = new PdfPCell(new Phrase("Total: ",fontTotal));
        PdfPCell c4 = new PdfPCell(new Phrase(String.valueOf(totalFinal),fontTotal));
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

        document.add(table);
        document.close();
    }

}
