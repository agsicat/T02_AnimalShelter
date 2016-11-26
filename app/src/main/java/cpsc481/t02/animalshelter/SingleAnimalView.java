package cpsc481.t02.animalshelter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SingleAnimalView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_animal_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView tag = (TextView) findViewById(R.id.single_tag);
        TextView surradd = (TextView) findViewById(R.id.single_surroradd);
        TextView type = (TextView) findViewById(R.id.single_type);
        TextView status = (TextView) findViewById(R.id.single_status);
        TextView animalType = (TextView) findViewById(R.id.single_animaltype);
        TextView behavior = (TextView) findViewById(R.id.single_behavior);
        TextView contactName = (TextView) findViewById(R.id.single_contactname);
        TextView contactPhone = (TextView) findViewById(R.id.single_contactphone);

        Intent intent = getIntent();
        String name = intent.getStringExtra("AnimalName");
        String number = intent.getStringExtra("AnimalNumber");
        String surrAdd = intent.getStringExtra("SurrAdd");

        tag.setText("Tag Number: "+number);
        surradd.setText("Admission Type: "+surrAdd);
        type.setText("Chip Type: Microchip");
        status.setText("Animal Status: Admitted");
        animalType.setText("Animal Type: Dog");
        behavior.setText("Animal Behavior: Aggressive, Quiet, Hyper");
        contactName.setText("Contact Name: Sample Name");
        contactPhone.setText("Contact Phone: 403-555-5555");


        getSupportActionBar().setTitle(name);
    }
}
