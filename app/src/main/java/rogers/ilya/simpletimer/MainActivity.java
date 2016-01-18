package rogers.ilya.simpletimer;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private boolean started = false;
    private long stoppedTime = 0;
    private ScrollView lapsScroll;
    private TextView lapsText;
    private String laps;
    private rogers.ilya.simpletimer.Chronometer timer;

    private boolean getStarted()
    {
        return this.started;
    }

    private boolean setStarted(boolean paramBoolean)
    {
        this.started = paramBoolean;
        return this.started;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        laps = "";
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        timer = ((rogers.ilya.simpletimer.Chronometer)findViewById(R.id.chronometer));
        timer.setTextSize(60);
        lapsScroll = (ScrollView) findViewById(R.id.lapsScroll);
        lapsText = (TextView) findViewById(R.id.lapsText);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            //If timer is stopped then start it from the spot it was stopped at
            if (!getStarted()){
                if (!getStarted()){
                    timer.setBase(SystemClock.elapsedRealtime()
                            +stoppedTime);
                    timer.start();
                    setStarted(true);
                }
                else{

                    Toast.makeText(getApplicationContext(), "Timer Started", Toast.LENGTH_SHORT).show();
                    timer.setBase(SystemClock.elapsedRealtime() + stoppedTime);
                    timer.start();
                    setStarted(true);
                }

               // Toast.makeText(getApplicationContext(), "Feck you3", Toast.LENGTH_SHORT).show();


            }
            else if (getStarted()){
                //add stuff here to resume?
                String time = SystemClock.elapsedRealtime() -  + 0 +"";
                Toast.makeText(getApplicationContext(), "Feck you", Toast.LENGTH_SHORT).show();
                lapsText.setText(laps += time +"\n");
            }
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            //If timer is stopped reset the timer if not then just stop it.
            if (getStarted()) {
                stoppedTime = SystemClock.elapsedRealtime();
                timer.stop();
                setStarted(false);
                Toast.makeText(getApplicationContext(), "Timer Stopped", Toast.LENGTH_SHORT).show();
            }
            else if (!getStarted()){
                timer.stop();
                timer.setBase(SystemClock.elapsedRealtime());
                setStarted(false);
                stoppedTime = 0;
                Toast.makeText(getApplicationContext(), "Timer reset", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //   return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
