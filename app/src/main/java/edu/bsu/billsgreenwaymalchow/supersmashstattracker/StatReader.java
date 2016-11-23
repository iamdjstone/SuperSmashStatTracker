package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class StatReader {

    public void createDocument(InputStream inputStream){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
