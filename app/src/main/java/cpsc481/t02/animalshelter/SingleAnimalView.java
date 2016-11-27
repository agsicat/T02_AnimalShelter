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
        TextView contactAddr = (TextView) findViewById(R.id.single_contactaddr);
        TextView contactPhoneNum = (TextView) findViewById(R.id.single_contactphone);

        TextView contactInfo = (TextView) findViewById(R.id.prevOwnerInfo);
        TextView contactPhone = (TextView) findViewById(R.id.contact_phone);
        TextView contactLocation = (TextView) findViewById(R.id.contact_address);

        Intent intent = getIntent();
        String name = intent.getStringExtra("AnimalName");
        String number = intent.getStringExtra("AnimalNumber");
        String surrAdd = intent.getStringExtra("SurrAdd");

        if (surrAdd.equals("Admitted")) {
            contactInfo.setText("Employee Information for Admission");
            contactLocation.setText("Location Found");
            contactPhone.setVisibility(View.GONE);
            contactPhoneNum.setVisibility(View.GONE);
        }
        else
            contactPhoneNum.setText("403-555-5555");

        tag.setText(number);
        surradd.setText(surrAdd);
        type.setText("Microchip");
        status.setText("Admitted");
        animalType.setText("Dog");
        behavior.setText("Aggressive, Quiet, Hyper");
        contactName.setText("Sample Name");
        contactAddr.setText("2500 University Drive NE");


        getSupportActionBar().setTitle(name);
    }
}
