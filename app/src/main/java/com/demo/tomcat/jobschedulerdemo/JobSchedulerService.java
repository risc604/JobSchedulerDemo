package com.demo.tomcat.jobschedulerdemo;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JobSchedulerService extends JobService
{
    String timeStamp = "";

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Handler mJobHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(Message msg)
        {
            //int n = new Random().nextInt(100);
            Date date = new Date(System.currentTimeMillis());

            setTimeStamp(sdf.format(date));
            Toast.makeText(getApplicationContext(), sdf.format(date), Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters) msg.obj, false);

            return true;
        }
    });

    public JobSchedulerService() {
    }

    @Override
    public boolean onStartJob(JobParameters params)
    {
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, params));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mJobHandler.removeMessages(1);
        return false;
    }

    public String getTimeStamp()
    {
        return  timeStamp;
    }

    public void setTimeStamp(String timeStp)
    {
        timeStamp = timeStp;
    }

}

