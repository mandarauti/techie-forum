package com.mansoft.practice.services.utils;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * @author mandar_auti
 *
 */
public class ServiceUtils {
	
	static boolean validateAgainstXSD(String xml, InputStream xsd)
	{
	    try
	    {
	        SchemaFactory factory = 
	            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new StreamSource(xsd));
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));
	        return true;
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
	        return false;
	    }
	}
	
	private static String createEmployeeInXml (String id, String name, String joiningdate,String department) {
        return "<employee>" +
        "<id>" + id + "</id>" +
        "<name>" + name + "</name>" +
        "<joiningdate>" + joiningdate + "</joiningdate>" +
        "<department>" + department + "</department>" +
        "</employee>";
    }
	
//	public static void main(String[] args) throws FileNotFoundException
//	{
//		File initialFile = new File("c:/Temp/Employee.xsd");
//	    InputStream targetStream = new FileInputStream(initialFile);
//	    
//	   String inputString= createEmployeeInXml("1",
//                "Mandar",
//                "12/12/78",
//                "it");
//	   System.out.println(validateAgainstXSD(inputString, targetStream));
//	}

}



