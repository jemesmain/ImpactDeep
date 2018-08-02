package fr.bicyclopresto.impactdeep;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_profil extends Fragment {


    public Fragment_profil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profil, container, false);
        // rootview permet d'accéder aux différents éléments du fragment
        final View rootView = inflater.inflate(R.layout.fragment_profil, container, false);


        TextView text_G, text_V, text_Move, text_Alert, text_Location, text_State;

        final EditText input_Name, input_Sport, input_Delay;

        final Intent data;

        //assign textEdit
        //MainActivity.input_Number1 = (EditText) rootView.findViewById(R.id.editNumber1);
        //MainActivity.input_Number2 = (EditText) rootView.findViewById(R.id.editNumber2);
        //MainActivity.input_Number3 = (EditText) rootView.findViewById(R.id.editNumber3);
        input_Name = (EditText) rootView.findViewById(R.id.editName);
        input_Sport = (EditText) rootView.findViewById(R.id.editSport);
        input_Delay = (EditText) rootView.findViewById(R.id.editDelay);

        SharedPreferences settings = getActivity().getSharedPreferences("ImpactDeep_pref", Context.MODE_PRIVATE);
        input_Name.setText(settings.getString("Name", "").toString());
        input_Sport.setText(settings.getString("Sport", "").toString());
        input_Delay.setText(settings.getString("Delay", "").toString());




        ImageButton btn_save = (ImageButton) rootView.findViewById(R.id.profil_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //MainActivity.phone_string1 = MainActivity.input_Number1.getText().toString();
                //MainActivity.phone_string2 = MainActivity.input_Number2.getText().toString();
                //MainActivity.phone_string3 = MainActivity.input_Number3.getText().toString();
                MainActivity.name_string = input_Name.getText().toString();
                MainActivity.sport_string = input_Sport.getText().toString();

                // enregistrement des préférences utilisateurs
                //SharedPreferences settings = getSharedPreferences("DeepImpact_pref", 0);

                SharedPreferences settings = getActivity().getSharedPreferences("ImpactDeep_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                //editor.putString("Number1",MainActivity.input_Number1.getText().toString());
                // editor.putString("Number2",MainActivity.input_Number2.getText().toString());
                // editor.putString("Number3",MainActivity.input_Number3.getText().toString());
                editor.putString("Name", input_Name.getText().toString());
                editor.putString("Sport", input_Sport.getText().toString());
                editor.putString("Delay", input_Delay.getText().toString());
                editor.commit();


                String toast_message = "profil sauvegardé";
                Toast msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
                msg.show();
            }
        });
        return rootView;
    }

}
