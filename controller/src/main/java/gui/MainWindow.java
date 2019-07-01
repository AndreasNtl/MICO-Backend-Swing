package gui;

import computation.ConsumerRunnable;
import computation.ProducerRunnable;
import listeners.*;
import mqtt.MqttMessageService;
import mqtt.OurCallback;
import queue.Queue;
import settings.AdministratorSettings;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class MainWindow extends JFrame {
    private JButton buttonMusicOn = new JButton("Send Music On");
    private JButton buttonMusicOff = new JButton("Send Music Off");
    private JButton buttonFlashOn = new JButton("Send Flash On");
    private JButton buttonFlashOff = new JButton("Send Flash Off");
    private JButton buttonAutoOn = new JButton("Automatic mode On");
    private JButton buttonAutoOff = new JButton("Automatic mode Off");
    private JButton buttonPatternOn = new JButton("Pattern Enable");
    private JButton buttonPatternOff = new JButton("Pattern Disable");
    private JButton buttonAndroidOn = new JButton("Android mode On");
    private JButton buttonAndroidOff = new JButton("Android mode Off");
    private JButton buttonStartClassification = new JButton("Start classification");
    private JButton buttonStopClassification = new JButton("Stop classification");

    private JComboBox musicDurationList;
    private JComboBox flashDurationList;

    private JLabel l1 = new JLabel("Time duration: ");
    private JLabel l2 = new JLabel("Time duration: ");

    private JLabel l3 = new JLabel("sec ");
    private JLabel l4 = new JLabel("sec ");

    private JTextArea area = new JTextArea();

    private String log = "";

    private MqttMessageService service;
    private Queue queue = new Queue();
    private ConsumerRunnable consumerRunnable = new ConsumerRunnable(queue);
    private ProducerRunnable producerRunnable = new ProducerRunnable(queue, this);
    private Thread consumerThread = new Thread(consumerRunnable);
    private Thread producerThread = new Thread(producerRunnable);

    public void appendToLog(String msg) {
        log = log + "\n" + new Date() + ":" + msg;


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                area.setText(log);
            }
        });
    }


    public MainWindow() {
        setTitle("MiCo");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        int x = AdministratorSettings.getInstance().getMusic_minduration();
        int y = AdministratorSettings.getInstance().getMusic_maxduration();

        String [] music_choices = new String[y-x+1];
        for (int i=x ;i<=y;i++) {
            music_choices[i-x] = "" + i;
        }

        musicDurationList = new JComboBox(music_choices);


        x = AdministratorSettings.getInstance().getMusic_minduration();
        y = AdministratorSettings.getInstance().getMusic_maxduration();

        String [] flash_choices = new String[y-x+1];
        for (int i=x ;i<=y;i++) {
            flash_choices[i-x] = "" + i;
        }

        flashDurationList = new JComboBox(flash_choices);


        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(l1);
        p1.add(musicDurationList);
        p1.add(l3);
        p1.add(buttonMusicOn);
        p1.add(buttonMusicOff);


        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(l2);
        p2.add(flashDurationList);
        p2.add(l4);
        p2.add(buttonFlashOn);
        p2.add(buttonFlashOff);


        JPanel p3 = new JPanel(new FlowLayout());
        p3.add(buttonAutoOn);
        p3.add(buttonAutoOff);

        JPanel p5 = new JPanel(new FlowLayout());
        p5.add(buttonPatternOn);
        p5.add(buttonPatternOff);


        JPanel p6 = new JPanel(new FlowLayout());
        p6.add(buttonAndroidOn);
        p6.add(buttonAndroidOff);

      //  JPanel p7 = new JPanel(new FlowLayout());

        area.setEditable(false);
        area.setMargin(new Insets(10,10,10,10));

        JPanel p4 = new JPanel(new GridLayout(5,1,40,40));
        p4.add(p1);
        p4.add(p2);
        p4.add(p3);
        p4.add(p5);
      //  p4.add(p6);
       // p4.add(p7);
        p4.add(buttonStartClassification);
        p4.add(buttonStopClassification);

        add(p4, BorderLayout.NORTH);


        JScrollPane js = new JScrollPane(area);
        add(js, BorderLayout.CENTER);

        appendToLog("hi");

        buttonAutoOff.setEnabled(false);
        buttonPatternOff.setEnabled(false);
        buttonStopClassification.setEnabled(false);

        buttonAutoOn.addActionListener(new AutoOnListener(this));
        buttonAutoOff.addActionListener(new AutoOffListener(this));
        buttonMusicOn.addActionListener(new MusicOnListener(this));
        buttonMusicOff.addActionListener(new MusicOffListener(this));
        buttonFlashOn.addActionListener(new FlashOnListener(this));
        buttonFlashOff.addActionListener(new FlashOffListener(this));
        buttonPatternOn.addActionListener(new PatternOnListener(this));
        buttonPatternOff.addActionListener(new PatternOffListener(this));
        buttonAndroidOn.addActionListener(new AndroidOnListener(this));
        buttonAndroidOff.addActionListener(new AndroidOffListener(this));
        buttonStartClassification.addActionListener(new ClassificationOnListener(this));
        buttonStopClassification.addActionListener(new ClassificationOffListener(this));

        OurCallback c= new OurCallback();

        service = new MqttMessageService(c);

        service.start();
    }

    // #############################################################

    public JButton getButtonMusicOn() {
        return buttonMusicOn;
    }

    public JButton getButtonMusicOff() {
        return buttonMusicOff;
    }

    public JButton getButtonFlashOn() {
        return buttonFlashOn;
    }

    public JButton getButtonFlashOff() {
        return buttonFlashOff;
    }

    public JButton getButtonAutoOn() {
        return buttonAutoOn;
    }

    public JButton getButtonAutoOff() {
        return buttonAutoOff;
    }

    public JComboBox getMusicDurationList() {
        return musicDurationList;
    }

    public JComboBox getFlashDurationList() {
        return flashDurationList;
    }

    public JButton getButtonAndroidOn() {
        return buttonAndroidOn;
    }

    public void setButtonAndroidOn(JButton buttonAndroidOn) {
        this.buttonAndroidOn = buttonAndroidOn;
    }

    public JButton getButtonAndroidOff() {
        return buttonAndroidOff;
    }

    public void setButtonAndroidOff(JButton buttonAndroidOff) {
        this.buttonAndroidOff = buttonAndroidOff;
    }

    public JButton getButtonPatternOn() {
        return buttonPatternOn;
    }

    public void setButtonPatternOn(JButton buttonPatternOn) {
        this.buttonPatternOn = buttonPatternOn;
    }

    public JButton getButtonPatternOff() {
        return buttonPatternOff;
    }

    public void setButtonPatternOff(JButton buttonPatternOff) {
        this.buttonPatternOff = buttonPatternOff;
    }

    public ProducerRunnable getProducerRunnable() {
        return producerRunnable;
    }

    public void setProducerRunnable(ProducerRunnable producerRunnable) {
        this.producerRunnable = producerRunnable;
    }

    public ConsumerRunnable getConsumerRunnable() {
        return consumerRunnable;
    }

    public void setConsumerRunnable(ConsumerRunnable consumerRunnable) {
        this.consumerRunnable = consumerRunnable;
    }

    public Thread getConsumerThread() {
        return consumerThread;
    }

    public void setConsumerThread(Thread consumerThread) {
        this.consumerThread = consumerThread;
    }


    public Thread getProducerThread() {
        return producerThread;
    }

    public void setProducerThread(Thread producerThread) {
        this.producerThread = producerThread;
    }

    public void reinitialize() {
        consumerThread = new Thread(consumerRunnable);
        producerThread = new Thread(producerRunnable);
    }

    public JButton getButtonStartClassification() {
        return buttonStartClassification;
    }

    public void setButtonStartClassification(JButton buttonStartClassification) {
        this.buttonStartClassification = buttonStartClassification;
    }

    public JButton getButtonStopClassification() {
        return buttonStopClassification;
    }

    public void setButtonStopClassification(JButton buttonStopClassification) {
        this.buttonStopClassification = buttonStopClassification;
    }

    public void updateClassificationGui() {
        this.getButtonMusicOn().setEnabled(true);
        this.getButtonMusicOff().setEnabled(true);
        this.getButtonFlashOn().setEnabled(true);
        this.getButtonFlashOff().setEnabled(true);
        this.getButtonAutoOn().setEnabled(true);
        this.getButtonAutoOff().setEnabled(false);
        this.getFlashDurationList().setEnabled(true);
        this.getMusicDurationList().setEnabled(true);
        this.getButtonAndroidOn().setEnabled(true);
        this.getButtonAndroidOff().setEnabled(false);
        this.getButtonStartClassification().setEnabled(true);
        this.getButtonStopClassification().setEnabled(false);
        this.getButtonPatternOn().setEnabled(true);
        this.getButtonPatternOff().setEnabled(false);
      //  this.appendToLog("Automatic mode disabled");

        reinitialize();
    }
}

