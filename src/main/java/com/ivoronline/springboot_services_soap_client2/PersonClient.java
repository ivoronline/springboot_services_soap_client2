package com.ivoronline.springboot_services_soap_client2;

import com.ivoronline.soap.GetPersonRequest;
import com.ivoronline.soap.GetPersonResponse;
import org.w3c.dom.Document;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

public class PersonClient {

  //PROPERTIES
  static String serverURL = "http://localhost:8080/GetPerson";

  //============================================================================================
  // GET PERSON
  //============================================================================================
  public static String getPerson() throws Exception {

    //CREATE REQUEST OBJECT
    GetPersonRequest      requestObject       = new GetPersonRequest();
                          requestObject.setId(1);

    //MARSHAL REQUEST OBJECT
    Document              requestXMLDocument  = UtilXML.marshal(requestObject, GetPersonRequest.class);

    //ADD SOAP ENVELOPE
    SOAPMessage           requestSOAPMessage  = MessageFactory.newInstance().createMessage();
                          requestSOAPMessage.getSOAPBody().addDocument(requestXMLDocument);

    //SEND REQUEST
    SOAPConnectionFactory factory             = SOAPConnectionFactory.newInstance();
    SOAPConnection        connection          = factory.createConnection();
    SOAPMessage           responseSOAPMessage = connection.call(requestSOAPMessage, serverURL);

    //UNMARSHAL RESPONSE XML INTO OBJECT
    Document              responseXMLDocument = UtilSOAP.extractDocumentFromSOAPMessage(responseSOAPMessage);
    GetPersonResponse     responseObject      = (GetPersonResponse) UtilXML.unmarshal(responseXMLDocument, GetPersonResponse.class);

    //GET PERSON NAME
    String                name                = responseObject.getName();

    //RETURN RESPONSE OBJECT
    return "Hello " + name;

  }

}
