package com.github.hyunj0.slackcodingchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.hyunj0.slackcodingchallenge.api.SlackClient;
import com.github.hyunj0.slackcodingchallenge.model.Member;
import com.github.hyunj0.slackcodingchallenge.model.SlackTeam;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamFragment extends Fragment {

    public static final String PREFERENCE_FILE = "com.github.hyunj0.slackPersistence";
    public static final String JSON = "json";

    private static final String TOKEN = "xoxp-5048173296-5048487710-19045732087-b5427e3b46";

    private ConnectivityManager connectivityManager;
    private NetworkInfo activeNetwork;
    private boolean isConnected;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String json;

    private RecyclerView rv;
    private List<Member> teamMembers;
    private MemberAdapter adapter;

    public TeamFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isConnected) {
            Call<SlackTeam> call = SlackClient.getServiceClient().getSlackTeam(TOKEN);
            call.enqueue(new Callback<SlackTeam>() {
                @Override
                public void onResponse(Call<SlackTeam> call, Response<SlackTeam> response) {
                    SlackTeam slackTeam = response.body();
                    json = new Gson().toJson(slackTeam);
                    teamMembers = slackTeam.getMembers();
                    adapter = new MemberAdapter(getContext(), teamMembers);
                    rv.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<SlackTeam> call, Throwable t) {
                    Log.d("FAILURE", t.toString());
                }
            });
        } else {
            String cache = sharedPreferences.getString(JSON, null);
            if (savedInstanceState == null && cache == null) {
                Toast.makeText(getContext(), "Enable network", Toast.LENGTH_LONG).show();
                getActivity().findViewById(R.id.no_network).setVisibility(View.VISIBLE);
            } else {
                SlackTeam slackTeam = new Gson().fromJson(cache, SlackTeam.class);
                teamMembers = slackTeam.getMembers();
                adapter = new MemberAdapter(getContext(), teamMembers);
                rv.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isConnected) {
            sharedPreferences.edit().putString(JSON, json).commit();
        }
    }
}