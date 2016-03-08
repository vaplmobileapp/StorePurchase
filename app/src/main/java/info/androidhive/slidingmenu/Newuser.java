package info.androidhive.slidingmenu;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.regex.Pattern;


public class Newuser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);
        Button btn = (Button) findViewById(R.id.button1);
        final EditText username = (EditText) findViewById(R.id.editText1);
        btn.setOnClickListener(new OnClickListener() {
            //String s1=(getIntent().getExtras().getString("sdate1"));
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                String possibleEmail = null;
                Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
                Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
                for (Account account : accounts) {
                    if (emailPattern.matcher(account.name).matches()) {
                        possibleEmail = account.name;
                        //    Toast.makeText(getApplicationContext(),possibleEmail, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String imeino=telephonyManager.getDeviceId();
                InputStream webs = null;
                try
                {
                    String usern= username.getText().toString() ;
                    HttpClient httpclient = new DefaultHttpClient();
                    String surl = "http://223.30.82.100/StorePurchase/MobileData.php?var1="+possibleEmail+"&var2="+imeino+"&var3="+usern;
                    HttpPost httppost = new HttpPost(surl);
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    webs = entity.getContent();
                    Toast.makeText(getApplicationContext(), "Your Account Will be Activated Shortly", Toast.LENGTH_SHORT).show();
                    finish();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //final String un = username.getText().toString();

    }
}

