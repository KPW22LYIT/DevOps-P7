/*
 * PaymentService
 *
 * Version information
 *
 * Date 30/05/2021
 *
 * Author: Kevin Page-Wood and L00162107
 *
 * Copyright notice
 */
package revolut;

public class PaymentService {
    private String serviceName;
    private String responseFromService;


    //set the response from service provider
    public void setResponseFromService(String responseFromService) {
        this.responseFromService = responseFromService;
    }

    //get the response from the provider
    public String getResponseFromService() {
        return responseFromService;
    }

    //set the payment service to to sufficient funds or insufficient funds
    public PaymentService(String name){
        this(name, "sufficientFunds");
    }
    //create and instance of the service provider
    public PaymentService(String name, String response){
        this.serviceName = name;
        this.responseFromService = response;
    }

    //get the service name
    public String getType() {
        return serviceName;
    }

    //set the service name
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
