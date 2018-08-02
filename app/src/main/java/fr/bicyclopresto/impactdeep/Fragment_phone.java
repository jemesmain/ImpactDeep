package fr.bicyclopresto.impactdeep;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_phone extends Fragment {


    int PICK_CONTACT_REQUEST = 1;  // The request code
    int from_emergency_btn;

    public Fragment_phone() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_setting, container, false);

        // DECLARATION DE VARIABLE
        // rootview permet d'accéder aux différents éléments du fragment
        final View rootView = inflater.inflate(R.layout.fragment_setting, container, false);


        TextView text_G, text_V, text_Move, text_Alert, text_Location, text_State;

        final EditText  input_Name, input_Sport, input_Gravity;

        final Intent data;

        //assign textEdit
        MainActivity.input_Number1 = (EditText) rootView.findViewById(R.id.editNumber1);
        MainActivity.input_Number2 = (EditText) rootView.findViewById(R.id.editNumber2);
        MainActivity.input_Number3 = (EditText) rootView.findViewById(R.id.editNumber3);
        //input_Name = (EditText) rootView.findViewById(R.id.editName);
        //input_Sport = (EditText) rootView.findViewById(R.id.editSport);
        //input_Gravity = (EditText) rootView.findViewById(R.id.editGravity);

        SharedPreferences settings = getActivity().getSharedPreferences("ImpactDeep_pref", Context.MODE_PRIVATE);
        //String loginemail = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(PREF_USER_NAME, "");;
        MainActivity.input_Number1.setText(settings.getString("Number1", "").toString());
        MainActivity.input_Number2.setText(settings.getString("Number2", "").toString());
        MainActivity.input_Number3.setText(settings.getString("Number3", "").toString());




        ImageButton btn_save = (ImageButton) rootView.findViewById(R.id.phone_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //String gravity_limit = input_Gravity.getText().toString();
                //if (gravity_limit.equals("")){
               //     MainActivity.G_limit=3.0;
              //  }
             //   else {
             //       MainActivity.G_limit = Double.valueOf(gravity_limit);
             //   }

                MainActivity.phone_string1 = MainActivity.input_Number1.getText().toString();
                MainActivity.phone_string2 = MainActivity.input_Number2.getText().toString();
                MainActivity.phone_string3 = MainActivity.input_Number3.getText().toString();
                //MainActivity.name_string = input_Name.getText().toString();
                //MainActivity.sport_string = input_Sport.getText().toString();

                // enregistrement des préférences utilisateurs
                //SharedPreferences settings = getSharedPreferences("DeepImpact_pref", 0);

                SharedPreferences settings = getActivity().getSharedPreferences("ImpactDeep_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Number1",MainActivity.input_Number1.getText().toString());
                editor.putString("Number2",MainActivity.input_Number2.getText().toString());
                editor.putString("Number3",MainActivity.input_Number3.getText().toString());
                //editor.putString("Name",input_Name.getText().toString());
                //editor.putString("Sport",input_Sport.getText().toString());
                //editor.putString("Gravity_Limit",input_Gravity.getText().toString());
                editor.commit();


                String toast_message ="Settings saved";
                Toast msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
                msg.show();
            }
        });

        ImageButton btn_contact1 = (ImageButton) rootView.findViewById(R.id.button_emergency1);
        btn_contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from_emergency_btn = 1;
                pickContact();

            }
        });
        ImageButton btn_contact2 = (ImageButton) rootView.findViewById(R.id.button_emergency2);
        btn_contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from_emergency_btn = 2;
                pickContact();
            }
        });
        ImageButton btn_contact3 = (ImageButton) rootView.findViewById(R.id.button_emergency3);
        btn_contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from_emergency_btn = 3;
                pickContact();
            }
        });



        return rootView;
    }



    private void pickContact() {

        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST); //activity is started with code number Pick_contact_request
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        final EditText input_Number1, input_Number2,input_Number3;
        //assign textEdit
        //input_Number1 = (EditText) this.getel(R.id.editNumber1).toString();
        //input_Number2 = (EditText) rootView.findViewById(R.id.editNumber2);
        //input_Number3 = (EditText) rootView.findViewById(R.id.editNumber3);

        // Check which request we're responding to
        String toast_message ;
        Toast msg;
        //toast_message ="pickContact ended ok";
        //Toast msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
        //msg.show();
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            //toast_message ="pick contact request ok";
            //msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
            //msg.show();
            if (resultCode == RESULT_OK) {
                //toast_message ="result ok";
                //msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
                //msg.show();
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)// Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContext().getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);

                // Do something with the phone number...

                //Tab_Activity.phone_string = number;
                //Tab_Activity.input_Number = number;
                toast_message = number;
                msg = Toast.makeText(getContext(), "phone: " + toast_message, Toast.LENGTH_SHORT);
                msg.show();

                //choix du bouton choisi
                if (from_emergency_btn == 1)
                    MainActivity.input_Number1.setText(number);
                if (from_emergency_btn == 2)
                    MainActivity.input_Number2.setText(number);
                if (from_emergency_btn == 3)
                    MainActivity.input_Number3.setText(number);


            }
        }
        //toast_message ="end get contact action ok";
        //msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
        //msg.show();
    }

}
