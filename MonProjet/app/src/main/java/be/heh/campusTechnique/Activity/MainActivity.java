package be.heh.campusTechnique.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import be.heh.campusTechnique.R;

public class MainActivity extends Activity {

    private static final int CODE_ACTIVITE = 1;

    SharedPreferences prefs_datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs_datas = PreferenceManager.getDefaultSharedPreferences(getApplication());
        //Bundle extratxt = this.getIntent().getExtras();
        if(!prefs_datas.getAll().isEmpty()/*extratxt != null*/)
        {
            /*Toast.makeText(getApplicationContext(),
                    "votre login est : " + (extratxt.getString("login")) + "\n"
                    + "votre password est : " + (extratxt.getString("pwd")) + "\n"
                    + "votre email est : " + (extratxt.getString("email") + "\n" ), Toast.LENGTH_LONG)
                    .show();*/
            Toast.makeText(getApplicationContext(),
                    "votre login est : " + prefs_datas.getString(
                            "login", "NULL")+ "\n" +
                            "votre password est : " + prefs_datas.getString("pwd", "NULL")+ "\n" +
                            "votre email est : " + prefs_datas.getString("email", "NULL") + "\n" , Toast.LENGTH_SHORT).show();
        }
    }

    public void onMainClickManager(View view) {
        switch (view.getId())
        {
            case R.id.bt_main_gotochildren :
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alerte activité")
                        .setMessage("Voulez-vous afficher l'activité Children ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getApplicationContext(), "clic sur Bt : ==> Enfant", Toast.LENGTH_LONG).show();
                                Intent monIntent = new Intent(getApplicationContext(), ChildrenActivity.class);
                                //startActivity(monIntent);
                                startActivityForResult(monIntent, CODE_ACTIVITE);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create().show();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_ACTIVITE:
                switch (resultCode) {
                    case RESULT_OK:
                        Toast.makeText(this, "Action validée depuis l'activité children", Toast.LENGTH_LONG).show();
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Action annulée", Toast.LENGTH_LONG).show();
                        break;
                }

                break;
        }
    }
}