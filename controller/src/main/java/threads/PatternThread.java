package threads;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import settings.AdministratorSettings;

import java.util.HashMap;
import java.util.Random;

// https://stackoverflow.com/questions/20914165/morse-code-converter

public class PatternThread extends Thread {
    private String[] msg;
    private HashMap<Character, String> morseMap = new HashMap<Character, String>();
    private static final long UNIT = 300; // ms
    private static final long DOT_DURATION = 1 * UNIT;
    private static final long DASH_DURATION = 3 * UNIT;
    private static final long INTERELEMENT_DURATION = 3 * UNIT;
    private static final long INTERLETTER_DURATION = 3 * UNIT;
    private static final long INTERWORD_DURATION = 7 * UNIT;

    private Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', '0', ' '};
    private String[] code = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..", ".----", "..---", "...--", "....-", ".....",
            "-....", "--...", "---..", "----.", "-----", "|"};

    public PatternThread(String[] msg) {
        this.msg = msg;

        for (int i = 0; i < letters.length; i++) {
            morseMap.put(letters[i], code[i]);
        }
    }

    @Override
    public void run() {
        super.run();

        AdministratorSettings settings = AdministratorSettings.getInstance();
        String topic = settings.gettopic();
        int qos = 2;
        String broker = "tcp://" + settings.getMqttIP() + ":" + settings.getMqttPort() + "";
        String clientId = settings.getclientId() + new Random().nextInt(); //random thread id
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);

            String sDot = "flashOn " + DOT_DURATION;
            String sDash = "flashOn " + DASH_DURATION;

            MqttMessage messageDot = new MqttMessage(sDot.getBytes());
            MqttMessage messageDash = new MqttMessage(sDash.getBytes());
            messageDot.setQos(qos);
            messageDash.setQos(qos);

            try {
                for (String word : msg) {
                    for (Character c : word.toCharArray()) {
                        String code = morseMap.get(c);

                        for (int i = 0; i < code.length(); i++) {
                            String t = code.charAt(i) + "";
                            if (t.equals(".")) {
                                System.out.println(".");
                                sampleClient.publish(topic, messageDot);
                                Thread.sleep(DOT_DURATION);
                            } else {
                                System.out.println("-");
                                sampleClient.publish(topic, messageDash);
                                Thread.sleep(DASH_DURATION);
                            }
                            Thread.sleep(INTERELEMENT_DURATION);
                        }

                        Thread.sleep(INTERLETTER_DURATION);
                    }
                    Thread.sleep(INTERWORD_DURATION);
                }
            } catch (Exception ex) {
                // ex.printStackTrace();
            }

            sampleClient.disconnect();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }


    }
}
