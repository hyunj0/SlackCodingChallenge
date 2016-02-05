package com.github.hyunj0.slackcodingchallenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.hyunj0.slackcodingchallenge.api.SlackClient;
import com.github.hyunj0.slackcodingchallenge.model.Member;
import com.github.hyunj0.slackcodingchallenge.model.SlackTeam;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamFragment extends Fragment {

    private static final String TOKEN = "xoxp-5048173296-5048487710-19045732087-b5427e3b46";

    private RecyclerView rv;
    private List<Member> teamMembers;
    private MemberAdapter adapter;

    public TeamFragment() {

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
        Call<SlackTeam> call = SlackClient.getServiceClient().getSlackTeam(TOKEN);
        call.enqueue(new Callback<SlackTeam>() {
            @Override
            public void onResponse(Call<SlackTeam> call, Response<SlackTeam> response) {
                SlackTeam slackTeam = response.body();
                String json = new Gson().toJson(slackTeam);
                Log.d("SUCCESS", json);
                teamMembers = slackTeam.getMembers();
                for (Member teamMember : teamMembers) {
                    Log.d("TEAM MEMBER FOUND", teamMember.toString());
                }
                adapter = new MemberAdapter(getContext(), teamMembers);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SlackTeam> call, Throwable t) {
                Log.d("FAILURE", t.toString());
            }
        });
    }
}