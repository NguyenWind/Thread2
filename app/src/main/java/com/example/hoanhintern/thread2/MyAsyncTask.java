package com.example.hoanhintern.thread2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;


public class MyAsyncTask extends AsyncTask<Object,Integer,Void> {
    private WeakReference<Activity> context;
    int current;



    public MyAsyncTask(Activity context){
        this.context = new WeakReference<>(context);
        activity = this.context.get();
    }

    Activity activity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Toast.makeText(activity,"Start", Toast.LENGTH_LONG).show();


    }

    @Override
    protected Void doInBackground(Object... voids) {
        for (int i = 0;i<=100;i++){
            SystemClock.sleep(10);
            publishProgress(i);

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (context != null) {
            current = values[0];
            TextView textView = (TextView) activity.findViewById(R.id.tv1);
            ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
            progressBar.setProgress(current);

            textView.setText(activity.getResources().getString(R.string.current, current));
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(activity,"finish",Toast.LENGTH_LONG).show();
    }
}
