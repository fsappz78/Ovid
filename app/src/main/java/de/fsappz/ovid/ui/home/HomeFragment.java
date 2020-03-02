package de.fsappz.ovid.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.InputStream;

import de.fsappz.ovid.MainActivity;
import de.fsappz.ovid.R;
import de.fsappz.ovid.Util;

public class HomeFragment extends Fragment {

    Button btnChangeTypeFace;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnChangeTypeFace = root.findViewById(R.id.button);

        if (Util.useLatinTheme(getContext())) {
            btnChangeTypeFace.setTypeface(Typeface.SANS_SERIF);
        }
        btnChangeTypeFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).toggleTheme();
            }
        });

        int[] pics = new int[]{R.raw.busto_emilio_marco, R.raw.properce_crop,R.raw.horacio_crop, -1, -1};
        ImageView[] targets = new ImageView[]{root.findViewById(R.id.iv_emilius), root.findViewById(R.id.iv_properz),root.findViewById(R.id.iv_horacio), root.findViewById(R.id.iv_corvinus), root.findViewById(R.id.iv_ponticus)};

        for (int i = 0; i < pics.length; i++) {
            if (pics[i] != -1) {
                InputStream imageStream = this.getResources().openRawResource(pics[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                targets[i].setImageBitmap(bitmap);
                targets[i].setBackground(getContext().getDrawable(R.drawable.round_shape));
            } else {
                targets[i].setImageDrawable(getContext().getDrawable(R.drawable.ic_person_black_24dp));
            }
        }
        LinearLayout timeline_items_container = root.findViewById(R.id.timeline_items_container);
        String[] dates = new String[]{
                "43 B.C.",
                "25 B.C.",
                "23 B.C.",
                "2 A.D.",
                "8 A.D.",
                "9 A.D.",
                "14 A.D.",
                "17 / 18 A.D."};
        //Verbannt - warum, weiß ich selbst nicht mehr so genau, ist ja mittlerweile auch schon über zweitausend jahre her
        String[] texts = new String[]{
                "Geburt",
                "Erster öffentlicher Vortrag - nach vorzeitig beendeter politischer Laufbahn",
                "Beginn meiner Frühphase mit \"Amores\", ein großer Erfolg!",
                "\"Ars Amatoria\" beendet - wieder ein Bestseller!\n\"Fasti\" begonnen.",
                "Verbannung durch Augustus - wegen \"carmen et error\" - Ich hätte den Grund damals genauer aufschreiben sollen.\nMuss Arbeit an \"Fasti\" bei der Hälfte unterbrechen.\nBeginn meiner Klagelieder",
                "In Tomis angekommen - ziemlich kalt.",
                "Augustus gestorben - Begnadigung kann ich vergessen.",
                "Der Fährmann bringt mich in den Hades. Weiß nur noch ungefähr, wann das war."};

        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < dates.length; i++) {
            String currentDate = dates[i];
            String currentText = texts[i];

            View item_timeline = layoutInflater.inflate(R.layout.item_timeline,null);

            TextView date = item_timeline.findViewById(R.id.timeline_date);
            TextView text = item_timeline.findViewById(R.id.timeline_text);

            date.setText(currentDate);
            text.setText(currentText);

            timeline_items_container.addView(item_timeline);
        }
        return root;
    }
}