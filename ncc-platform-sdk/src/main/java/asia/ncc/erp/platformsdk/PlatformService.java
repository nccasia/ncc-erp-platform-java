package asia.ncc.erp.platformsdk;

import asia.ncc.erp.platformsdk.config.kafka.producer.MessageProducer;
import asia.ncc.erp.platformsdk.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

    @Autowired
    private MessageProducer messageProducer;

    public void saveEmployee(EmployeeDTO employee) {
        messageProducer.sendGreetingMessage(employee);
    }
}
