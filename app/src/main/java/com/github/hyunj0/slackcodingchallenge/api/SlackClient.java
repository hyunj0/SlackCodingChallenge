package com.github.hyunj0.slackcodingchallenge.api;

import com.github.hyunj0.slackcodingchallenge.model.SlackTeam;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SlackClient {

    private static final String API_BASE_URL = "https://slack.com/api/";

    public static SlackClientInterface getServiceClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(SlackClientInterface.class);
    }

    public interface SlackClientInterface {
        @GET("users.list")
        Call<SlackTeam> getSlackTeam(@Query("token") String token);
    }
}