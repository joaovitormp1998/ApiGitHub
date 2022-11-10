package com.example.appgithub;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView nameView, userView, orgView, bioView, fwsView, fwgView, repView, modeText;
    private RequestQueue queue;
    private ImageView avatar;
    private Context context;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkMode);

        } else {
            setTheme(R.style.Theme_AppGitHub);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.mode);
        modeText = findViewById(R.id.modeText);

        if (aSwitch.isActivated()) {
            modeText.setText("Dark Mode");

        } else {
            modeText.setText("Light Mode");

        }


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            aSwitch.setChecked(true);
            modeText.setText("Dark Mode");
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    modeText.setText("Dark Mode");

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    modeText.setText("Light Mode");

                }
            }
        });
        components();
        requestUser();
    }

    private void components() {
        nameView = findViewById(R.id.nameVal);
        userView = findViewById(R.id.userVal);
        orgView = findViewById(R.id.orgVal);
        bioView = findViewById(R.id.bioVal);
        fwsView = findViewById(R.id.fwsVal);
        fwgView = findViewById(R.id.fwgVal);
        repView = findViewById(R.id.public_reposVal);
        avatar = findViewById(R.id.avatar);
        context = getApplicationContext();
        queue = Volley.newRequestQueue(context);
    }

    private void requestUser() {
        JsonObjectRequest request = new JsonObjectRequest(
                U.BASE_URL + "/users/joaovitormp1998",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            User gitUser = new User(
                                    U.checkNull(response.getString("name")),
                                    U.checkNull(response.getString("login")),
                                    U.checkNull(response.getString("company")),
                                    U.checkNull(response.getString("bio")),
                                    response.getInt("followers"),
                                    response.getInt("following"),
                                    response.getInt("public_repos"),
                                    U.checkNull(response.getString("avatar_url"))
                            );
                            setComponents(gitUser);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );


        queue.add(request);
    }

    private void setComponents(User user) {
        nameView.setText(user.getName());
        userView.setText(user.getUsername());
        orgView.setText(user.getOrganization());
        bioView.setText(user.getBio());
        fwsView.setText("" + user.getFollowers());
        fwgView.setText("" + user.getFollowing());
        repView.setText("" + user.getRepositories());

        Glide.with(context).
                load(user.getAvatarUrl()).
                centerInside().
                into(avatar);
    }
}