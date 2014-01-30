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
	
	//get view variables from XML
	TextView textview_accelerometer_xAxis;
	TextView textview_accelerometer_yAxis;
	TextView textview_accelerometer_zAxis;
	TextView textview_light;
	
	//define accelerometer variables
	double xAxis, yAxis, zAxis;
	
	//define light sensor variables
	float lightValue;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textview_accelerometer_xAxis = (TextView) findViewById(R.id.textview_accelerometer_xAxis);
		textview_accelerometer_yAxis = (TextView) findViewById(R.id.textview_accelerometer_yAxis);
		textview_accelerometer_zAxis = (TextView) findViewById(R.id.textview_accelerometer_zAxis);
		textview_light = (TextView) findViewById(R.id.textview_light);
		
		sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		
		//Define Sensor instances
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        
        //Set listener for Accelerometer Sensor
		sensorManager.registerListener(new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				xAxis = event.values[0];
        		textview_accelerometer_xAxis.setText("X-Axis: " + getRoundedDoubleString(xAxis));
                yAxis = event.values[1];
                textview_accelerometer_yAxis.setText("Y-Axis: " + getRoundedDoubleString(yAxis));
                zAxis = event.values[2];
                textview_accelerometer_zAxis.setText("Z-Axis: " + getRoundedDoubleString(zAxis));
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
		}, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		
		//Set Listener for Light Sensor
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
