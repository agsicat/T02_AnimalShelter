package cpsc481.t02.animalshelter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SingleAnimalView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_animal_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tag = (TextView) findViewById(R.id.single_tag);

        Intent intent = getIntent();
        String name = intent.getStringExtra("AnimalName");
        String number = intent.getStringExtra("AnimalNumber");

        tag.setText(number);

        getSupportActionBar().setTitle(name);
    }
}
