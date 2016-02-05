package com.github.hyunj0.slackcodingchallenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        holder.memberContainer.setBackgroundColor(Color.parseColor("#"+teamMember.getColor()));
        Picasso.with(context).load(teamMember.getProfile().getImage192()).into(holder.image);
        holder.username.setText(teamMember.getUsername());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.d("MEMBER CLICKED", teamMembers.get(position).toString());
                Intent intent = new Intent(v.getContext(), MemberActivity.class);
                intent.putExtra("image", teamMembers.get(position).getProfile().getImage192());
                intent.putExtra("name", teamMembers.get(position).getProfile().getRealName());
                intent.putExtra("title", teamMembers.get(position).getProfile().getTitle());
                intent.putExtra("email", teamMembers.get(position).getProfile().getEmail());
                intent.putExtra("timezone", teamMembers.get(position).getTz());
                intent.putExtra("color", "#"+teamMembers.get(position).getColor());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        LinearLayout memberContainer;
        ImageView image;
        TextView username;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            memberContainer = (LinearLayout) itemView.findViewById(R.id.member_container);
            image = (ImageView) itemView.findViewById(R.id.image);
            username = (TextView) itemView.findViewById(R.id.username);
        }
    }
}