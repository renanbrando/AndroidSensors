package com.gmail.reebrando.androidsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AcelerometerActivity extends AppCompatActivity implements SensorEventListener{

    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;
    private TextView txtDetail;

    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
    int count = 3;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometer);

        txtX = (TextView) findViewById(R.id.txtX);
        txtY = (TextView) findViewById(R.id.txtY);
        txtZ = (TextView) findViewById(R.id.txtZ);
        txtDetail = (TextView) findViewById(R.id.txtDetail);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Float x = event.values[0];
        Float y = event.values[1];
        Float z = event.values[2];
        txtX.setText("Posição X: " + x.intValue() + " Float: " + x);
        txtY.setText("Posição Y: " + y.intValue() + " Float: " + y);
        txtZ.setText("Posição Z: " + z.intValue() + " Float: " + z);
        if(y < 0) { // O dispositivo esta de cabeça pra baixo
            if(x > 0)
                txtDetail.setText("Virando para ESQUERDA ficando INVERTIDO");
            if(x < 0)
                txtDetail.setText("Virando para DIREITA ficando INVERTIDO");
        } else {
            if(x > 0)
                txtDetail.setText("Virando para ESQUERDA ");
            if(x < 0)
                txtDetail.setText("Virando para DIREITA ");
        }

        Float x1 = event.values[0];
        Float y1 = event.values[1];
        Float z1 = event.values[2];
        // SHAKE
        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x1*x1 + y1*y1 + z1*z1));
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta; // perform low-cut filter
        if (mAccel > 5) {
            count++;
            // precisa mexer 3x
            if (count >= 3) {
                count = 0;
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(2000);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
