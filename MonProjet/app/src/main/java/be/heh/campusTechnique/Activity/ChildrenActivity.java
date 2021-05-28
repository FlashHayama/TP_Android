package be.heh.campusTechnique.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import be.heh.campusTechnique.BDD.User;
import be.heh.campusTechnique.BDD.UserAccessBDD;
import be.heh.campusTechnique.R;

public class ChildrenActivity extends AppCompatActivity {

    EditText et_children_login;
    EditText et_children_pwd;
    EditText et_children_email;

    SharedPreferences prefs_datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children);

        et_children_login = (EditText) findViewById(R.id.et_children_login);
        et_children_pwd = (EditText) findViewById(R.id.et_children_pwd);
        et_children_email = (EditText) findViewById(R.id.et_children_email);

        prefs_datas = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void onChildrenClickManager(View view) {
        switch (view.getId())
        {
            case R.id.bt_children_save :
                String str = et_children_login.getText().toString() + "#" +
                        et_children_pwd.getText().toString() + "#" +
                        et_children_email.getText().toString() + "#";
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                /*try {
                    FileOutputStream ous = openFileOutput("monFichier.txt", MODE_APPEND);
                    byte[] tab;
                    tab = str.toString().getBytes();
                    ous.write(tab);
                    ous.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                User user1 = new User(
                        et_children_login.getText().toString(),
                        et_children_pwd.getText().toString(),
                        et_children_email.getText().toString());
                UserAccessBDD userDB = new UserAccessBDD(this);
                userDB.openForWrite();
                userDB.insertUser(user1);
                userDB.Close();
                break;
            case R.id.bt_children_Read :
                Intent readFile = new Intent(this, FileActivity.class);
                startActivity(readFile);
                finish();
                break;
            case R.id.bt_children_main :
                if(et_children_login.getText().toString().isEmpty() ||
                        et_children_pwd.getText().toString().isEmpty() ||
                        et_children_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Complétez tous les champs !",Toast.LENGTH_SHORT).show();
                }
                else {
                /*Toast.makeText(getApplicationContext(),
                        "Login : " + et_children_login.getText() +
                        "\nPassword : " + et_children_pwd.getText() +
                        "\nEmail : " + et_children_email.getText(),
                        Toast.LENGTH_LONG).show();*/
                    //setResult(RESULT_OK);
                    SharedPreferences.Editor editeur_datas = prefs_datas.edit();
                    editeur_datas.putString("login", et_children_login.getText().toString());
                    editeur_datas.putString("pwd", et_children_pwd.getText().toString());
                    editeur_datas.putString("email", et_children_email.getText().toString());
                    editeur_datas.commit();
                    Intent intxt = new Intent(this, MainActivity.class);
                    //intxt.putExtra("login", et_children_login.getText().toString());
                    //intxt.putExtra("pwd", et_children_pwd.getText().toString());
                    //intxt.putExtra("email", et_children_email.getText().toString());

                    startActivity(intxt);
                    finish();
                }
                break;

            case R.id.bt_children_list :
                Intent intentToList = new Intent(this, ListActivity.class);
                startActivity(intentToList);
                break;
        }
    }
}