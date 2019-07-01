package mqtt;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import settings.AdministratorSettings;

public class MqttMessageService {

    private static final String TAG = "MqttMessageService";
    private MemoryPersistence persistence = new MemoryPersistence();
    private MqttConnectOptions connOpts = new MqttConnectOptions();
    private MqttClient sampleClient = null;
    private AdministratorSettings settings = AdministratorSettings.getInstance();

    private OurCallback ourcallback;

    public MqttMessageService(OurCallback ourcallback) {

        connOpts.setCleanSession(true);//Set callback

        this.ourcallback = ourcallback;
    }

    public void start() {
        try {
            String MQTT_BROKER_URL = "tcp://" + settings.getMqttIP() + ":" + settings.getMqttPort();
            sampleClient = new MqttClient(MQTT_BROKER_URL, settings.getClientId2(), persistence);
            sampleClient.setCallback(ourcallback);
            sampleClient.connect(connOpts);
            sampleClient.subscribe(settings.getTopic2(), 2);

        } catch (Exception ex) {
            sampleClient = null;
            ex.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (sampleClient != null) {
                try {
                    sampleClient.unsubscribe(settings.getTopic2());
                    sampleClient.close();
                } catch (MqttException e) {
                    //e.printStackTrace();
                }
            }
        } catch (Exception ex) {

        }
    }


}