package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

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
    private File file;

    public Document createSaveXMLDocument(File file) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
        statKeeper = document.createElement("statKeeper");
        document.appendChild(statKeeper);

        printToScreen(file);
        return document;
    }

    private void printToScreen(File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

    public void update(StatTrackerElement statTrackerElement) throws TransformerException {
        statKeeper.appendChild(statTrackerElement.getTheXMLElement());
        printToScreen(file);
    }
}
