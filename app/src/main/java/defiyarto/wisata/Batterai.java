package defiyarto.wisata;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

// http://cariprogram.blogspot.com
// nuramijaya@gmail.com

public class Batterai extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batterai);

        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent arg1) {
                // TODO Auto-generated method stub

                if (arg1.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                    TextView tvBatteryLevel = (TextView)findViewById(R.id.tvBatteryLevel);
                    TextView tvBatteryVoltage = (TextView)findViewById(R.id.tvBatteryVoltage);
                    TextView tvBatteryTemperature = (TextView)findViewById(R.id.tvBatteryTemperature);
                    TextView tvBatteryTechnology = (TextView)findViewById(R.id.tvBatteryTechnology);
                    TextView tvBatteryStatus = (TextView)findViewById(R.id.tvBatteryStatus);
                    TextView tvBatteryHealth = (TextView)findViewById(R.id.tvBatteryHealth);

                    tvBatteryLevel.setText("Level: "
                            + String.valueOf(arg1.getIntExtra("level", 0)) + "%");
                    tvBatteryVoltage.setText("Voltage: "
                            + String.valueOf((float)arg1.getIntExtra("voltage", 0)/1000) + "V");
                    tvBatteryTemperature.setText("Temperature: "
                            + String.valueOf((float)arg1.getIntExtra("temperature", 0)/10) + "c");
                    tvBatteryTechnology.setText("Technology: " + arg1.getStringExtra("technology"));

                    int status = arg1.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                    String strStatus;
                    if (status == BatteryManager.BATTERY_STATUS_CHARGING){
                        strStatus = "Charging";
                    } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING){
                        strStatus = "Dis-charging";
                    } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                        strStatus = "Not charging";
                    } else if (status == BatteryManager.BATTERY_STATUS_FULL){
                        strStatus = "Full";
                    } else {
                        strStatus = "Unknown";
                    }
                    tvBatteryStatus.setText("Status: " + strStatus);

                    int health = arg1.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN);
                    String strHealth;
                    if (health == BatteryManager.BATTERY_HEALTH_GOOD){
                        strHealth = "Good";
                    } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT){
                        strHealth = "Over Heat";
                    } else if (health == BatteryManager.BATTERY_HEALTH_DEAD){
                        strHealth = "Dead";
                    } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){
                        strHealth = "Over Voltage";
                    } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){
                        strHealth = "Unspecified Failure";
                    } else{
                        strHealth = "Unknown";
                    }
                    tvBatteryHealth.setText("Health: " + strHealth);
                }
            }
        }, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}