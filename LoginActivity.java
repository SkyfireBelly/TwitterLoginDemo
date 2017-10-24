package com.example.shubham.twitterlogindemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetui.UserTimeline;

import static android.R.attr.duration;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class LoginActivity extends AppCompatActivity {

      TwitterLoginButton LoginButton;
    UserTimeline userTimeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_login);

        LoginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        userTimeline = new UserTimeline.Builder().screenName("twitterdev").build();


        LoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls

               /* TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
*/
              String  userName = result.data.getUserName();
                Intent searchintent = new Intent(Main)


                //login(session);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();

            }
        });
    }
   /* public void login(TwitterSession session){
          String username=session.getUserName();
        Intent intent = new Intent(LoginActivity.this,Homepage.class);
        intent.putExtra("UserName",username);
        startActivity(intent);
    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        LoginButton.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


         if( id == R.id.about){

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            String url = "https://en.wikipedia.org/wiki/Twitter";
            intent.setData(Uri.parse(url));
            startActivity(intent);

        }else if(id == R.id.feedback){

            Intent feedback = new Intent();
            feedback.setAction(Intent.ACTION_SENDTO);
            feedback.setData(Uri.parse("mailto:shubhamrustagi88@gmail.com"));
            feedback.putExtra(Intent.EXTRA_TEXT,"FEEDBACK");
            if(feedback.resolveActivity(getPackageManager()) != null){
                startActivity(feedback);
            }
            else {

            }
        }
        else if(id == R.id.contact){

            int permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);

            if(permission == PERMISSION_GRANTED){
                Intent call = new Intent();
                call.setAction(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:8826528382"));
                startActivity(call);
            }
            else {
                //  ActivityCompat.requestPermissions();
                Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }


        }
        return super.onOptionsItemSelected(item);
    }

}
