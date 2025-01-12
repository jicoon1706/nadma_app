package com.example.group03;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class temperatureSensor extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private SensorEventListener temperatureListener;
    private TextView tvTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_sensor);

        tvTemperature = findViewById(R.id.tvTemperature);

        // Initialize the SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get the Ambient Temperature Sensor
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (temperatureSensor == null) {
            Toast.makeText(this, "Ambient Temperature Sensor is not available on this device.", Toast.LENGTH_SHORT).show();
            tvTemperature.setText("Temperature sensor not available.");
            return;
        }

        // Create a SensorEventListener
        temperatureListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // Get the temperature value
                float temperature = event.values[0];
                tvTemperature.setText("Temperature: " + temperature + "°C");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // You can handle accuracy changes here (if needed)
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (temperatureSensor != null) {
            sensorManager.registerListener(temperatureListener, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (temperatureSensor != null) {
            sensorManager.unregisterListener(temperatureListener);
        }
    }
}