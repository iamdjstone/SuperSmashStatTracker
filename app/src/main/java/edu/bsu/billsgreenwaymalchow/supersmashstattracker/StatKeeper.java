package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class StatKeeper {

    private Document document;
    private Element statKeeper;

    public Document createSaveXMLDocument() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
        statKeeper = document.createElement("statKeeper");
        document.appendChild(statKeeper);

        printToScreen();
        return document;
    }

    private void printToScreen() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult (System.out);
        transformer.transform(source, result);
    }

    public void update(StatTrackerElement statTrackerElement){
        statKeeper.appendChild(statTrackerElement.getElement());
    }
}
