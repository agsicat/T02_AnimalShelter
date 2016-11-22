package cpsc481.t02.animalshelter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExistingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_list);

        final ListView animals = (ListView) findViewById(R.id.list);
        String[] names = new String[]{
                "Dog 01", "Cat 01", "Dog 02", "Cat 02", "Lizard 01", "Bird 01", "Dog 03", "Cat 03", "Cat 04"
        };

        String [] tags = new String []{
                "0000007", "000001", "0122000", "0100002", "0020033", "0800999", "4444222", "000002", "1333223", "1111223"
        };

        int [] images = new int[]{
                R.drawable.nyan,R.drawable.nyan,R.drawable.nyan,R.drawable.nyan,R.drawable.nyan,R.drawable.nyan,R.drawable.nyan,R.drawable.nyan,R.drawable.nyan
        };

        final ArrayList<AnimalItem> list = new ArrayList<AnimalItem>();

        for(int i=0; i<names.length; ++i){
         list.add(new AnimalItem(names[i], tags[i], images[i]));
        }

       // final aAdapter adapter = new aAdapter(this, R.id.secondLine, list);
        ArrayAdapter<AnimalItem> adapter = new ArrayAdapter<AnimalItem>(this, R.layout.individual_list_item, R.id.secondLine, list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView name = (TextView) view.findViewById(R.id.firstLine);
                TextView number = (TextView) view.findViewById(R.id.secondLine);
                ImageView image = (ImageView) view.findViewById(R.id.animalimage);

                AnimalItem a = list.get(position);

                name.setText(a.getName());
                number.setText(a.getNumber());
                image.setImageResource(a.getImage());

                return view;
            }


        };

        assert animals != null;
        animals.setAdapter(adapter);

        animals.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final AnimalItem item = (AnimalItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), SingleAnimalView.class);
                intent.putExtra("AnimalName", item.getName());
                intent.putExtra("AnimalNumber", item.getNumber());
                startActivity(intent);

            }

        });

    }

    private class AnimalItem{
         private String name;
         private String number;
         private int image;
         public AnimalItem(String name, String number, int image){
            this.name = name;
            this.number = number;
            this.image = image;
       }

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getNumber(){
            return number;
        }
        public void setNumber(String number){
            this.number = number;
        }
        public int getImage(){
            return image;
        }
        public void setImage(int image){
            this.image = image;
        }
    }
}
