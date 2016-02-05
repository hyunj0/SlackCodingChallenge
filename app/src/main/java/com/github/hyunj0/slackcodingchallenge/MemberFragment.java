package com.github.hyunj0.slackcodingchallenge;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.hyunj0.slackcodingchallenge.model.Member;
import com.squareup.picasso.Picasso;

public class MemberFragment extends Fragment {

    private LinearLayout memberCard;
    private ImageView memberImage;
    private TextView memberName;
    private TextView memberTitle;
    private TextView memberEmail;
    private TextView memberTimezone;

    private String image;
    private String name;
    private String title;
    private String email;
    private String timezone;
    private String color;

    public MemberFragment() {

    }

    public static MemberFragment newInstance(Member teamMember) {
        MemberFragment memberFragment = new MemberFragment();
        Bundle args = new Bundle();
        args.putString("image", teamMember.getProfile().getImage192());
        args.putString("name", teamMember.getProfile().getRealName());
        args.putString("title", teamMember.getProfile().getTitle());
        args.putString("email", teamMember.getProfile().getEmail());
        args.putString("timezone", teamMember.getTz());
        args.putString("color", teamMember.getColor());
        memberFragment.setArguments(args);
        return memberFragment;
    }

    public static MemberFragment newInstance(String image, String name, String title, String email, String timezone, String color) {
        MemberFragment memberFragment = new MemberFragment();
        Bundle args = new Bundle();
        args.putString("image", image);
        args.putString("name", name);
        args.putString("title", title);
        args.putString("email", email);
        args.putString("timezone", timezone);
        args.putString("color", color);
        memberFragment.setArguments(args);
        return memberFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getString("image");
            name = getArguments().getString("name");
            title = getArguments().getString("title");
            email = getArguments().getString("email");
            timezone = getArguments().getString("timezone");
            color = getArguments().getString("color");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member, container, false);
        memberCard = (LinearLayout) v.findViewById(R.id.member_card);
        memberImage = (ImageView) v.findViewById(R.id.member_image);
        memberName = (TextView) v.findViewById(R.id.member_name);
        memberTitle = (TextView) v.findViewById(R.id.member_title);
        memberEmail = (TextView) v.findViewById(R.id.member_email);
        memberTimezone = (TextView) v.findViewById(R.id.member_timezone);
        memberCard.setBackgroundColor(Color.parseColor(color));
        Picasso.with(getContext()).load(image).resize(750, 750).into(memberImage);
        memberName.setText(name);
        memberTitle.setText(title);
        memberEmail.setText(email);
        memberTimezone.setText(timezone);
        return v;
    }
}