package ai.man.al.samplegrpc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new IsRegisteredATask("9137138099", isRegistered1 -> {
            Toast.makeText(this, "is registered: "+isRegistered1, Toast.LENGTH_LONG).show();
        }).execute();

    }
}