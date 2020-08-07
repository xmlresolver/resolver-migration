package com.nwalsh.resolver.xmlresolver;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlresolver.tools.ResolvingXMLReader;

import java.io.File;
import java.io.FileInputStream;

public class Demo {
    public static void main(String[] argv) throws Exception {
        Demo demo = new Demo();
        demo.run();
    }

    public void run() throws Exception {
        File document = new File("src/test/resources/document.xml");
        MySaxHandler handler = new MySaxHandler();

        XMLReader reader = new ResolvingXMLReader();
        reader.setErrorHandler(handler);
        reader.setContentHandler(handler);
        reader.setFeature("http://apache.org/xml/features/xinclude", true);
        reader.setFeature("http://xml.org/sax/features/validation", true);

        InputSource source = new InputSource(new FileInputStream(document));
        source.setSystemId(document.getAbsolutePath());

        reader.parse(source);

        if (handler.success) {
            System.err.println("Parsed document");
        } else {
            System.err.println("An error occurred.");
        }
    }

    public class MySaxHandler extends DefaultHandler {
        public boolean success = true;

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            System.err.println("Started reading document");
        }

        @Override
        public void startElement (String uri, String localName,
                                  String qName, Attributes attributes)
                throws SAXException
        {
            super.startElement(uri, localName, qName, attributes);
            System.err.println("Start: " + qName);
        }

        @Override
        public void warning(SAXParseException exception) throws SAXException {
            success = false;
            System.err.println("Warning:" + exception);
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            success = false;
            System.err.println("Error:" + exception);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            success = false;
            System.err.println("Fatal error:" + exception);
        }
    }
}
