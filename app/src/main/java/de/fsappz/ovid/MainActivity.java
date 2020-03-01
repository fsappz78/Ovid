package de.fsappz.ovid;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.fsappz.ovid.ui.home.HomeFragment;
import de.fsappz.ovid.ui.more.MoreFragment;
import de.fsappz.ovid.ui.work.WorksFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    HomeFragment homeFragment;
    WorksFragment worksFragment;
    MoreFragment moreFragment;

    FragmentManager fragmentManager;

    LinearLayout fragContainer;

    Fragment currentFrag;
    Fragment sel;

    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSharedPreferences("pref",MODE_PRIVATE).getBoolean("normalFontTheme",false)){
            setTheme(R.style.DefaultFontTheme);
        }else{
            setTheme(R.style.AppTheme);
        }

        setTitle("Publius Ovidius Naso");

        setContentView(R.layout.activity_main);

        fragContainer=findViewById(R.id.nav_frag_container);
        navView = findViewById(R.id.nav_view);

        fragmentManager = getSupportFragmentManager();

        homeFragment=new HomeFragment();
        worksFragment =new WorksFragment();
        moreFragment=new MoreFragment();

        currentFrag = homeFragment;

        fragmentManager.beginTransaction().add(R.id.nav_frag_container, homeFragment).commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                sel=(item.getItemId()==R.id.navigation_work ? worksFragment : (item.getItemId()==R.id.navigation_more ? moreFragment : homeFragment));

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                if(!sel.isAdded()){
                    fragmentTransaction.add(R.id.nav_frag_container, sel);
                }
                fragmentTransaction.hide(currentFrag).show(sel).commitNowAllowingStateLoss();
                currentFrag = sel;
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(currentFrag==homeFragment){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Warte!").setMessage("Ich sehe schon: Temous edax rerum - Die Zeit nagt...\nMöchtest du mich wirklich verlassen?");
            builder.setPositiveButton("Beenden", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.super.onBackPressed();
                }
            });
            builder.setNegativeButton("Nein, ich bleibe!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
        }else{
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).hide(currentFrag).show(homeFragment).commit();
            navView.setSelectedItemId(R.id.navigation_home);
        }
    }
    @SuppressLint("ApplySharedPref")
    public void toggleTheme(){
        getSharedPreferences("pref",MODE_PRIVATE).edit().putBoolean("normalFontTheme",!Util.useLatinTheme(this)).commit();
        finish();
        Intent i = getBaseContext().getPackageManager().
                getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}