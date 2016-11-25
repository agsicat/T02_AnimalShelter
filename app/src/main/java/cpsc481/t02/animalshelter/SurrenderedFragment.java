package cpsc481.t02.animalshelter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.content.Intent;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SurrenderedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SurrenderedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SurrenderedFragment extends Fragment {

    private Spinner ShelterStatus_Surrendered;
    private Spinner AnimalType_Surrendered;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgView;

    private OnFragmentInteractionListener mListener;

    public SurrenderedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SurrenderedFragment.
     */
    public static SurrenderedFragment newInstance() {
        SurrenderedFragment fragment = new SurrenderedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View currentView =  inflater.inflate(R.layout.fragment_surrendered, container, false);


        //Dropdown selections for Shelter Status
        ShelterStatus_Surrendered = (Spinner) currentView.findViewById(R.id.ShelterStatus);
        ArrayAdapter<String> spinnerAdapter_ShelterStatus_Surrendered = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.shelter_status_array));
        ShelterStatus_Surrendered.setAdapter(spinnerAdapter_ShelterStatus_Surrendered);

        //Dropdown selections for Animal Type
        AnimalType_Surrendered = (Spinner) currentView.findViewById(R.id.AnimalType);
        ArrayAdapter<String> spinnerAdapter_AnimalType_Surrendered = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.animal_types_array));
        AnimalType_Surrendered.setAdapter(spinnerAdapter_AnimalType_Surrendered);

        //Camera
        imgView = (ImageView) currentView.findViewById(R.id.imageView);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            // clicking the imageview will open the camera to add an image of the animal
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        return currentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Bitmap btmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                imgView.setImageBitmap(bitmap);
                imgView.setBackgroundColor(0);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
