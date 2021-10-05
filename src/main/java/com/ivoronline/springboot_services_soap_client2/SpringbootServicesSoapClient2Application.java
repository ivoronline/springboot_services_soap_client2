package com.ivoronline.springboot_services_soap_client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootServicesSoapClient2Application {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SpringbootServicesSoapClient2Application.class, args);

    //CALL SERVER
    String response = PersonClient.getPerson();
    System.out.println(response);



  }

}
