package tugaspipl.transbatam;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Adepu on 06/12/2017.
 */

public class User_Naikbus_Tujuan extends AppCompatActivity{
    private Button scan_btn;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Intent intent;

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_naikbus_tujuan);
        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedpreferences = getSharedPreferences(User_Login.my_shared_preferences, Context.MODE_PRIVATE);
        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_Dashboard.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation2:
                        Toast.makeText(getApplicationContext(),"Anda Sedang Berada di Naik Bus",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation3:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_Profile.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation4:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_Saldo.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation5:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_Jadwal.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation6:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_Riwayat.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation7:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_Setting.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation8:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_About.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation9:
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(User_Login.session_status, false);
                        editor.putString("username", null);
                        editor.commit();
                        Intent intent = new Intent(User_Naikbus_Tujuan.this, User_Login.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigationtambahan:
                        intent = new Intent(User_Naikbus_Tujuan.this, User_Riwayat_Perjalanan.class);
                        finish();
                        startActivity(intent);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Pemindaian di batalkan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("tujuan", result.getContents());
                editor.commit();
                Intent intent = new Intent(User_Naikbus_Tujuan.this, User_Naikbus_Report.class);
                finish();
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
