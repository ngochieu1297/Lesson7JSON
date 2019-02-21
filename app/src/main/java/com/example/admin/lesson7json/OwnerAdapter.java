package com.example.admin.lesson7json;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.ViewHolder> {
    private List<Owner> mOwners;
    private LayoutInflater mInflater;

    public OwnerAdapter(Context context, List<Owner> owners) {
        mInflater = LayoutInflater.from(context);
        mOwners = owners;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_owner, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mOwners.get(position));
    }

    @Override
    public int getItemCount() {
        return mOwners.size() > 0 ? mOwners.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mId;
        private TextView mType;
        private ImageView mAvatarUser;

        public ViewHolder(View itemView) {
            super(itemView);
            mId = itemView.findViewById(R.id.text_id_owner);
            mType = itemView.findViewById(R.id.text_type);
            mAvatarUser = itemView.findViewById(R.id.image_avatar_user);
        }

        public void setData(Owner owner) {
            mId.setText(owner.getId());
            mType.setText(owner.getType());
            Picasso.get().load(owner.getAvatarURL()).into(mAvatarUser);
        }
    }
}
