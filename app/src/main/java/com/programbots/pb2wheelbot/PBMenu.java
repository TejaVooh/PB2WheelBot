package com.programbots.pb2wheelbot;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.view.Menu;

public class PBMenu extends AppCompatActivity {


    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mBluetoothSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    String a;
    Editable b;

    int front=1, back=2, left=3, right=4, stop=5, line=6, rc=7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final EditText name = (EditText) findViewById(R.id.editText);
        Button b6 = (Button) findViewById(R.id.button);
        b6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                b = name.getText();
                a = b.toString();
                try {
                    findBT();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        ImageButton b1 = (ImageButton) findViewById(R.id.imageButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mmOutputStream.write('1');
                } catch (IOException e) {
                    Toast.makeText(PBMenu.this, "Connection not established with the robot", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        ImageButton b2 = (ImageButton) findViewById(R.id.imageButton2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mmOutputStream.write('2');
                } catch (IOException e) {
                    Toast.makeText(PBMenu.this, "Connection not established with the robot", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        ImageButton b3 = (ImageButton) findViewById(R.id.imageButton3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mmOutputStream.write('3');
                } catch (IOException e) {
                    Toast.makeText(PBMenu.this, "Connection not established with the robot", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        ImageButton b4 = (ImageButton) findViewById(R.id.imageButton4);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mmOutputStream.write('4');
                } catch (IOException e) {
                    Toast.makeText(PBMenu.this, "Connection not established with the robot", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        ImageButton b5 = (ImageButton) findViewById(R.id.imageButton5);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mmOutputStream.write('5');
                } catch (IOException e) {
                    Toast.makeText(PBMenu.this, "Connection not established with the robot", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    void findBT() throws IOException {

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 1);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName() .equals(a))
                {
                    mmDevice = device;
                    break;
                }
            }
        }

        UUID uuid = UUID.fromString("00001101-0000-1000-8000-008055f9b34fb");
        mBluetoothSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);

        mBluetoothSocket.connect();
        mmOutputStream = mBluetoothSocket.getOutputStream();
        mmInputStream = mBluetoothSocket.getInputStream();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater h= getMenuInflater();
        h.inflate(R.menu.hardmenu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId())
        {
            case R.id.abou:
                startActivity(new Intent("com.programbots.pb2wheelbot.About"));
                return true;
        }
        return false;
    }
}




