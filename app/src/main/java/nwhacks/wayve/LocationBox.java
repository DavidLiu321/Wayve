package nwhacks.wayve;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LocationBox extends DialogFragment {

    String textContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ask_screen, container, false);

        Button ok = view.findViewById(R.id.dismiss);

        final EditText place = view.findViewById(R.id.editPlace);

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place.setText("");
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textContent = place.getText().toString();
                MapsActivity.res.setText("Currently searching for:\n" + textContent);
                getDialog().dismiss();
            }
        });

        return view;
    }
}

