
package com.ivoronline.springboot_services_soap_client2;

import org.w3c.dom.Document;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import java.io.ByteArrayOutputStream;

public class UtilSOAP {

  //=======================================================================================
  // EXTRACT SOAP BODY
  //=======================================================================================
  public static Document extractDocumentFromSOAPMessage(SOAPMessage soapMessage) throws Exception {
    return soapMessage.getSOAPBody().extractContentAsDocument();
  }

  //=======================================================================================
  // CREATE SOAP DOCUMENT
  //=======================================================================================
  public static Document createSOAPDocument(Document document) throws Exception {

    //ADD SOAP ENVELOPE
    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
                soapMessage.getSOAPBody().addDocument(document);

    //CONVER SOAP MESSAGE TO DOCUMENT
    Document    soapDocument = SOAPToDocument(soapMessage);

    //RETURN SOAP DOCUMENT
    return soapDocument;

  }

  //=======================================================================================
  // SOAP TO DOCUMENT
  //=======================================================================================
  public static Document SOAPToDocument(SOAPMessage soapMessage) throws Exception {

    Source             src         = soapMessage.getSOAPPart().getContent();
    TransformerFactory tf          = TransformerFactory.newInstance();
    Transformer        transformer = tf.newTransformer();
    DOMResult          result      = new DOMResult();
                       transformer.transform(src, result);
    Document           document    = (Document) result.getNode();

    return document;

  }

  //=======================================================================================
  // SOAP TO STRING
  //=======================================================================================
  public static String SOAPToString(SOAPMessage soapMessage) throws Exception {

    //CONVERT SOAP MESSAGE TO OUTPUT STREAM
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                          soapMessage.writeTo(outputStream);

    //STORE RESULT INTO STRING
    String output = new String(outputStream.toByteArray());

    //RETURN STRING
    return output;

  }

}
