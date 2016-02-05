package com.github.hyunj0.slackcodingchallenge;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.hyunj0.slackcodingchallenge.model.Member;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private Context context;
    private List<Member> teamMembers;

    public MemberAdapter(Context context, List<Member> teamMembers) {
        this.context = context;
        this.teamMembers = teamMembers;
    }

    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MemberAdapter.ViewHolder holder, int position) {
        final Member teamMember = teamMembers.get(position);
        Picasso.with(context).load(teamMember.getProfile().getImage192()).into(holder.image);
        holder.username.setText(teamMember.getUsername());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.d("MEMBER CLICKED", teamMembers.get(position).toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView image;
        TextView username;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            image = (ImageView) itemView.findViewById(R.id.image);
            username = (TextView) itemView.findViewById(R.id.username);
        }
    }
}