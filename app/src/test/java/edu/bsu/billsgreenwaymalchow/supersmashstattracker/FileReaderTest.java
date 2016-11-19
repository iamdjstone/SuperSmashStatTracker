package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class FileReaderTest {

    @Before
    public void setUp(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("TestAsset.xml");
        StatReader statReader = new StatReader();
        statReader.createDocument(inputStream);
    }

    @Test
    public void testReaderReturnsCorrectAttribute(){

    }




}

