package cpsc481.t02.animalshelter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ExistingList extends AppCompatActivity {


    // Search EditText
    EditText searchBar;

    //ArrayAdapter
    ArrayAdapter<AnimalItem> adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_list);

        final ListView animals = (ListView) findViewById(R.id.list);

        searchBar = (EditText) findViewById(R.id.searchbar);

        searchBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                //Dynamic filtering
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {


            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        String[] names = new String[]{
                "Dog 01", "Cat 01", "Dog 02", "Cat 02", "Lizard 01", "Bird 01", "Dog 03", "Cat 03", "Cat 04"
        };

        String[] tags = new String[]{
                "0000007", "000001", "0122000", "0100002", "0020033", "0800999", "4444222", "000002", "1333223", "1111223"
        };

        int[] images = new int[]{
                R.drawable.nyan, R.drawable.nyan, R.drawable.nyan, R.drawable.nyan, R.drawable.nyan, R.drawable.nyan, R.drawable.nyan, R.drawable.nyan, R.drawable.nyan
        };

        final ArrayList<AnimalItem> list = new ArrayList<AnimalItem>();

        for (int i = 0; i < names.length; ++i) {
            list.add(new AnimalItem(names[i], tags[i], images[i]));
        }

        // final aAdapter adapter = new aAdapter(this, R.id.secondLine, list);
        adapter = new ArrayAdapter<AnimalItem>(this, R.layout.individual_list_item, R.id.secondLine, list) {
            List<AnimalItem> dynamicList = list;
            List<AnimalItem> originalList = list;

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
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

            @Override
            public int getCount() {
                return dynamicList.size();
            }

            @Override
            public AnimalItem getItem(int position) {
                return dynamicList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Filter getFilter() {
                Filter filter = new Filter() {

                    @Override
                    protected void publishResults(CharSequence constraint, FilterResults results) {

                        dynamicList = (List<AnimalItem>) results.values; // has the filtered values
                        notifyDataSetChanged();  // notifies the data with new filtered values
                    }

                    @Override
                    protected FilterResults performFiltering(CharSequence constraint) {
                        FilterResults results = new FilterResults();
                        List<AnimalItem> FilteredArrList = new ArrayList<AnimalItem>();

                        if (originalList == null) {
                            originalList = new ArrayList<AnimalItem>(dynamicList);
                        }

                        if (constraint == null || constraint.length() == 0) {

                            // set the Original result to return
                            results.count = originalList.size();
                            results.values = originalList;
                        } else {
                            constraint = constraint.toString().toLowerCase();
                            for (int i = 0; i < originalList.size(); i++) {
                                AnimalItem data = originalList.get(i);
                                if (data.getName().toLowerCase().startsWith(constraint.toString())) {
                                    FilteredArrList.add(data);
                                }
                            }
                            // set the Filtered result to return
                            results.count = FilteredArrList.size();
                            results.values = FilteredArrList;
                        }
                        return results;
                    }
                };
                return filter;
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ExistingList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://cpsc481.t02.animalshelter/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ExistingList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://cpsc481.t02.animalshelter/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class AnimalItem {
        private String name;
        private String number;
        private int image;

        public AnimalItem(String name, String number, int image) {
            this.name = name;
            this.number = number;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }

}
