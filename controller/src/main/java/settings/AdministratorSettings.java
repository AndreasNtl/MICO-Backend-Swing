package settings;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AdministratorSettings {

    private String topic = null;
    private String topic2 = null;
    private String mqttIP = null;
    private String mqttPort = null;
    private String clientId = null;
    private String clientId2 = null;
    private String pattern = null;

    private String dataFinalCSV = null;
    private String tsCSV = null;

    private int music_minduration;
    private int music_maxduration;

    private int flash_minduration;
    private int flash_maxduration;

    private int max_delay = 3000; // ms

    private int android_frequency = 3000; // ms

    private int consume_frequency = 3000; // ms

    //singleton
    private static AdministratorSettings instance = null;

    private AdministratorSettings() {

    }

    public static synchronized AdministratorSettings getInstance() {
        if (instance == null) {
            instance = new AdministratorSettings();
        }
        return instance;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        return new CloneNotSupportedException();
    }

    private String result = "";
    private InputStream inputStream;

    public void loadFromDisk() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            clientId = prop.getProperty("clientId");
            clientId2 = prop.getProperty("clientId2");
            mqttIP = prop.getProperty("mqttIP");
            mqttPort = prop.getProperty("mqttPort");
            topic = prop.getProperty("topic");
            topic2 = prop.getProperty("topic2");
            android_frequency = Integer.parseInt(prop.getProperty("android_frequency"));
            String music_min_duration = prop.getProperty("music_minduration");
            String music_max_duration = prop.getProperty("music_maxduration");
            String flash_min_duration = prop.getProperty("flash_minduration");
            String flash_max_duration = prop.getProperty("flash_maxduration");
            pattern = prop.getProperty("sendPattern");
            dataFinalCSV = prop.getProperty("dataFinalCSV");
            tsCSV = prop.getProperty("tsCSV");

            music_minduration = Integer.parseInt(music_min_duration);
            music_maxduration = Integer.parseInt(music_max_duration);
            flash_minduration = Integer.parseInt(flash_min_duration);
            flash_maxduration = Integer.parseInt(flash_max_duration);



        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }


    public String getclientId() {
        return clientId;
    }

    public void setclientId(String clientId) {
        this.clientId = clientId;
    }

    public String gettopic() {
        return topic;
    }

    public void settopic(String topic) {
        this.topic = topic;
    }
    public String getMqttIP() {
        return mqttIP;
    }

    public void setMqttIP(String mqttIP) {
        this.mqttIP = mqttIP;
    }

    public String getMqttPort() {
        return mqttPort;
    }

    public void setMqttPort(String mqttPort) {
        this.mqttPort = mqttPort;
    }

    public int getMusic_minduration() {
        return music_minduration;
    }

    public void setMusic_minduration(int music_minduration) {
        this.music_minduration = music_minduration;
    }

    public int getMusic_maxduration() {
        return music_maxduration;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setMusic_maxduration(int music_maxduration) {
        this.music_maxduration = music_maxduration;
    }

    public static void setInstance(AdministratorSettings instance) {
        AdministratorSettings.instance = instance;
    }


    public int getFlash_minduration() {
        return flash_minduration;
    }

    public void setFlash_minduration(int flash_minduration) {
        this.flash_minduration = flash_minduration;
    }

    public int getFlash_maxduration() {
        return flash_maxduration;
    }

    public void setFlash_maxduration(int flash_maxduration) {
        this.flash_maxduration = flash_maxduration;
    }

    public int getMax_delay() {
        return max_delay;
    }

    public void setMax_delay(int max_delay) {
        this.max_delay = max_delay;
    }


    public String getTopic2() {
        return topic2;
    }

    public void setTopic2(String topic2) {
        this.topic2 = topic2;
    }

    public String getClientId2() {
        return clientId2;
    }

    public void setClientId2(String clientId2) {
        this.clientId2 = clientId2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getPattern() { return pattern; }

    public void setPattern(String pattern) { this.pattern = pattern; }

    public int getAndroid_frequency() {
        return android_frequency;
    }

    public void setAndroid_frequency(int android_frequency) {
        this.android_frequency = android_frequency;
    }


    public int getConsume_frequency() {
        return consume_frequency;
    }

    public void setConsume_frequency(int consume_frequency) {
        this.consume_frequency = consume_frequency;
    }

    public String getDataFinalCSV() {
        return dataFinalCSV;
    }

    public String getTsCSV() {
        return tsCSV;
    }
}
