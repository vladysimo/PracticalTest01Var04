package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by vlad on 27/03/2017.
 */

public class ProcessingThread extends Thread {

    private Context context;
    private String sablon;
    private boolean isRunning;

    public ProcessingThread(Context context, String sablon) {

        this.context = context;
        this.sablon = sablon;
        isRunning = true;
    }

    public void stopThread() {
        isRunning = false;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        int i = 0, len;
        String[] tokens;

        tokens = sablon.split(", ");
        len = tokens.length;

        while (isRunning) {
            sleep();
            sendMessage(tokens, i);
            i++;
            if (i == len)
                i = 0;
        }

    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String[] tokens, int i) {

        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);
        intent.putExtra("message", tokens[i]);
        context.sendBroadcast(intent);
    }
}
