package asia.ncc.erp.platformsdk;

import asia.ncc.erp.platformsdk.config.kafka.producer.MessageProducer;
import asia.ncc.erp.platformsdk.dto.KafkaEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

    @Autowired
    private MessageProducer messageProducer;

    public void sendMessage(String message) {
        messageProducer.sendMessage(message);
    }

    public void saveEmployee(KafkaEmployee employee) {
        messageProducer.sendGreetingMessage(employee);
    }
}
