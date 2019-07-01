package mqtt;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import settings.AdministratorSettings;


public class OurCallback implements MqttCallback {

    private static final String TAG = "OurCallback";
    //Looper is from Main thread //We are in background thread

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());

        System.out.println("RECEIVED: " + payload);

        String[] parts = payload.split(" ");


        if (parts.length == 2) {
            String part1 = parts[0];
            String part2 = parts[1];

            if (part1.equals("frequencyoption")) {
                try {
                    int v = Integer.parseInt(part2);

                    AdministratorSettings.getInstance().setAndroid_frequency(v);
                } catch (Exception ex) {

                }
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
