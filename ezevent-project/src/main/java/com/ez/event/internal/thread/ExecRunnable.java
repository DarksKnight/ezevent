package com.ez.event.internal.thread;

public abstract class ExecRunnable implements Runnable {

    private boolean running;
    private boolean complete;

    public abstract void execute();

    @Override
    public void run() {
        running = true;
        execute();
        running = false;
        complete = true;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isComplete() {
        return complete;
    }
}
