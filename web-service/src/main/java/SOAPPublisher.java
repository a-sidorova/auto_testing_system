import javax.xml.ws.Endpoint;

import service.AutoTestingSystemImpl;

public class SOAPPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/AutoTestingSystem", new AutoTestingSystemImpl());
        System.out.println("AutoTestingSystem is started");
    }
}
