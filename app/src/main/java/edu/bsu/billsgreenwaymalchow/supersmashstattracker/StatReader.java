package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


@SuppressWarnings("WeakerAccess")
public class StatReader {

    private Document document;

    public void createDocument(File file){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Document getDocument(){
        return document;
    }

}
