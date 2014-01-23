package at.hammer.sensor_reader;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	//define reference variables
	SensorManager sensorManager;
	Sensor accelerometer;
	
	//get view variables from XML
	TextView textview_accelerometer_xAxis = (TextView) findViewById(R.id.label_accelerometer_xAxis);
	TextView textview_accelerometer_yAxis = (TextView) findViewById(R.id.label_accelerometer_yAxis);
	TextView textview_accelerometer_zAxis = (TextView) findViewById(R.id.label_accelerometer_zAxis);
	
	//define accelerometer variables
	double xAxis, yAxis, zAxis;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
        		xAxis = event.values[0];
        		textview_accelerometer_xAxis.setText("X-Axis: " + String.valueOf(xAxis));
                yAxis = event.values[1];
                textview_accelerometer_yAxis.setText("Y-Axis: " + String.valueOf(yAxis));
                zAxis = event.values[2];
                textview_accelerometer_zAxis.setText("Z-Axis: " + String.valueOf(zAxis));
            }
		
	}

}
