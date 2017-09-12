package com.aaron.w6d5_client_server_ble;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    IMyAidlInterface service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(IMyAidlInterface.class.getName());

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d("Srv", "Service connected!");
                service = IMyAidlInterface.Stub.asInterface(binder);
                Log.d("Srv", "Service interface ["+service+"]");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("Srv", "Service disconnected!");
                service = null;
            }
        };
        bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
