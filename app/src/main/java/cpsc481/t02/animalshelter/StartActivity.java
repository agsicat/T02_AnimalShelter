package cpsc481.t02.animalshelter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button newAnimal;
    private Button existingAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        newAnimal = (Button)findViewById(R.id.newButton);
        existingAnimal = (Button) findViewById(R.id.existing);

        existingAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExistingList.class);
                startActivity(intent);
            }
        });

        newAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewAnimalForm.class);
                startActivity(intent);
            }
        });
    }
}
