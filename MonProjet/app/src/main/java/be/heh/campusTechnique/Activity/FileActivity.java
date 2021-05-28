package be.heh.campusTechnique.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import be.heh.campusTechnique.BDD.User;
import be.heh.campusTechnique.BDD.UserAccessBDD;
import be.heh.campusTechnique.R;

public class FileActivity extends AppCompatActivity {

    TextView tv_file_datas;
    ListView lv_file_liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        tv_file_datas = (TextView) findViewById(R.id.tv_file_datas);
        tv_file_datas.setText("Contenu de la table utilisateur \n");

        /*try {
            FileInputStream ins = openFileInput("monFichier.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            StringBuilder out = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null)
            {
                out.append(line);
            }
            reader.close();
            ins.close();
            String[] items = out.toString().split("#");
            int i = 0;
            for(String item : items)
            {
                tv_file_datas.setText(tv_file_datas.getText().toString() +
                        "item " + Integer.toString((i+1)) + " = " + item + "\n" );
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        lv_file_liste = (ListView) findViewById(R.id.lv_file_liste);
        UserAccessBDD userDB = new UserAccessBDD(this);
        userDB.openForRead();
        ArrayList<User> tab_user = userDB.getAllUser();
        userDB.Close();
        if(tab_user.isEmpty())
            Toast.makeText(this, "Base de données vide !", Toast.LENGTH_SHORT).show();
        else
        {
            ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tab_user);
            lv_file_liste.setAdapter(adapter);
        }

    }
}