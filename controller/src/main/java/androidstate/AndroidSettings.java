package androidstate;


import threads.AndroidThread;

public class AndroidSettings {

    private AndroidThread thread = null;

    private static AndroidSettings instance = null;

    public static synchronized AndroidSettings getInstance() {
        if (instance == null) {
            instance = new AndroidSettings();
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        return new CloneNotSupportedException();
    }

    public AndroidThread getThread() {
        return thread;
    }

    public void setThread(AndroidThread thread) {
        this.thread = thread;
    }
}
