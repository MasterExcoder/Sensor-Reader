package at.hammer.sensor_reader;

import java.text.DecimalFormat;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	//define reference variables
	SensorManager sensorManager;
	Sensor accelerometer;
	Sensor lightSensor;
	Sensor gyroscope;
	
	//Define view variables from XML
	TextView textview_accelerometer_xAxis;
	TextView textview_accelerometer_yAxis;
	TextView textview_accelerometer_zAxis;
	
	TextView textview_light;
	
	TextView textview_gyroscope_xAxis;
	TextView textview_gyroscope_yAxis;
	TextView textview_gyroscope_zAxis;
	
	//define accelerometer variables
	double accelerometer_xAxis, accelerometer_yAxis, accelerometer_zAxis;
	
	//define light sensor variables
	float lightValue;
	
	//define gyroscope variables
	double gyroscope_xAxis, gyroscope_yAxis, gyroscope_zAxis;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//instatiate view variables
		textview_accelerometer_xAxis = (TextView) findViewById(R.id.textview_accelerometer_xAxis);
		textview_accelerometer_yAxis = (TextView) findViewById(R.id.textview_accelerometer_yAxis);
		textview_accelerometer_zAxis = (TextView) findViewById(R.id.textview_accelerometer_zAxis);
		
		textview_light = (TextView) findViewById(R.id.textview_light);
		
		textview_gyroscope_xAxis = (TextView) findViewById(R.id.textview_gyroscope_xAxis);
		textview_gyroscope_yAxis = (TextView) findViewById(R.id.textview_gyroscope_yAxis);
		textview_gyroscope_zAxis = (TextView) findViewById(R.id.textview_gyroscope_zAxis);
		
		
		sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		
		//Define Sensor instances
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        
        
        //Set listener for ACCELEROMETER Sensor
		sensorManager.registerListener(new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				accelerometer_xAxis = event.values[0];
        		textview_accelerometer_xAxis.setText("X-Axis: " + getRoundedDoubleString(accelerometer_xAxis));
                accelerometer_yAxis = event.values[1];
                textview_accelerometer_yAxis.setText("Y-Axis: " + getRoundedDoubleString(accelerometer_yAxis));
                accelerometer_zAxis = event.values[2];
                textview_accelerometer_zAxis.setText("Z-Axis: " + getRoundedDoubleString(accelerometer_zAxis));
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
		}, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		
		//Set Listener for LIGHT Sensor
		sensorManager.registerListener(new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				lightValue = event.values[0];
				textview_light.setText(String.valueOf(lightValue));
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
		}, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
		
		//Set listener for GYROSCOPE
		sensorManager.registerListener(new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				gyroscope_xAxis = event.values[0];
				textview_gyroscope_xAxis.setText(getRoundedDoubleString(gyroscope_xAxis));
				gyroscope_yAxis = event.values[1];
				textview_gyroscope_yAxis.setText(getRoundedDoubleString(gyroscope_yAxis));
				gyroscope_zAxis = event.values[2];
				textview_gyroscope_zAxis.setText(getRoundedDoubleString(gyroscope_zAxis));
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
		}, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public String getRoundedDoubleString(double d) {
		DecimalFormat dFormat = new DecimalFormat("0.00");
		return dFormat.format(d);
	}

}
