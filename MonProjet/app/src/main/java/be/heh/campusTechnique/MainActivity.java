package be.heh.campusTechnique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMainClickManager(View view) {
        switch (view.getId())
        {
            case R.id.bt_main_gotochildren :
                Toast.makeText(getApplicationContext(), "clic sur Bt : ==> Enfant", Toast.LENGTH_LONG).show();
        }
    }
}