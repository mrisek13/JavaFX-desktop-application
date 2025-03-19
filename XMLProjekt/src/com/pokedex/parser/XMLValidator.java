package com.pokedex.parser;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import org.xml.sax.SAXParseException;
import java.io.File;

public class XMLValidator {

    public static String validateXML(String xmlPath, String xsdPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return "XML is valid!";
        } catch (SAXParseException e) {
            return "Validation error at line " + e.getLineNumber() + ", column " + e.getColumnNumber() + ": " + e.getMessage();
        } catch (Exception e) {
            return "Validation error: " + e.getMessage();
        }
    }
}

