package autostate;


import threads.AutomaticThread;

public class AutomationSettings {

    private AutomaticThread thread = null;

    private static AutomationSettings instance = null;

    public static synchronized AutomationSettings getInstance() {
        if (instance == null) {
            instance = new AutomationSettings();
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        return new CloneNotSupportedException();
    }

    public AutomaticThread getThread() {
        return thread;
    }

    public void setThread(AutomaticThread thread) {
        this.thread = thread;
    }
}
