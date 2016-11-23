package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FileReaderTest {

    @Before
    public void setUp() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("TestAsset.xml");
        StatReader statReader = new StatReader();
        statReader.createDocument(inputStream);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    }

    @Test
    public void testReaderReturnsCorrectAttribute(){

    }




}

