package com.example.user.dentistapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.dentistapp.Blog;
import com.example.user.dentistapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter <ImageAdapter.ImageViewHolder> {
      private Context mContext;
      private List<Blog> mUploads;

      public ImageAdapter(Context context , List<Blog> uploads)
      {
          mContext = context;
          mUploads = uploads;
      }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
      View v = LayoutInflater.from(mContext).inflate(R.layout.blog_row, viewGroup , false);
        return new ImageViewHolder(v);

      }

    @Override
    public void onBindViewHolder( ImageViewHolder holder, int i) {
       Blog uploadCurrent = mUploads.get(i);
       holder.post_title.setText(uploadCurrent.getTitle());
       holder.post_desc.setText(uploadCurrent.getDesc());
        Picasso.with(mContext)
                .load(uploadCurrent.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
              public TextView post_title;
              public TextView post_desc;
              public ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.post_title);
            post_desc = itemView.findViewById(R.id.post_desc);
            imageView= itemView.findViewById(R.id.post_image);
        }
    }

}
