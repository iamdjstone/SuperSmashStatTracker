package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;

import java.io.File;
import java.io.InputStream;

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

    public Document createDocumentForTest(InputStream inputStream){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(inputStream);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return document;
        }
    }

    public Document getDocument(){
        return document;
    }

}
