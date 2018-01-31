package com.demo.tomcat.jobschedulerdemo;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


//https://github.com/tutsplus/Android-JobSchedulerAPI/


public class MainActivity extends AppCompatActivity
{
    final static String TAG = MainActivity.class.getSimpleName();

    private JobScheduler    mJobScheduler;
    private Button  mSchedduleJobButton;
    private Button  mCancelAllJobButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initControl();
    }


    //------ User define function. ---------------------------------------//
    private void initView()
    {
        mSchedduleJobButton = (Button) findViewById(R.id.schedule_job);
        mCancelAllJobButton = (Button) findViewById(R.id.cancel_all);
    }

    private void initControl()
    {
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        mSchedduleJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                JobInfo.Builder builder = new JobInfo.Builder(1,
                        new ComponentName(getPackageName(), JobSchedulerService.class.getName()) );
                builder.setPeriodic(3000);

                if (mJobScheduler.schedule(builder.build()) <= 0)
                {
                    Toast.makeText(getBaseContext(), "Job Scheduler fail !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCancelAllJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJobScheduler.cancelAll();
            }
        });
    }

}

