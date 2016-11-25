package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class StatReader {

    public Document document;

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
            DocumentBuilder documentBuilder;
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
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

    public int findTotalNumberOfTrackers(){
        NodeList nodeList = document.getElementsByTagName("tracker");
        return nodeList.getLength();
    }
}
