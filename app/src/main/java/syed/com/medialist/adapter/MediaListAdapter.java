package syed.com.medialist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import syed.com.medialist.R;
import syed.com.medialist.entity.Media;

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.MediaViewHolder> {

    LayoutInflater mLayoutInflater;
    List<Media> mMediaList;
    MediaOnItemClickListener mOnItemClickListener;

    public MediaListAdapter(Context context, MediaOnItemClickListener listener) {
        mLayoutInflater = LayoutInflater.from(context);
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MediaViewHolder(mLayoutInflater.inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder mediaViewHolder, int i) {
        if (mMediaList != null) {
            mediaViewHolder.title.setText(mMediaList.get(i).getTitle());
            mediaViewHolder.description.setText(mMediaList.get(i).getDescription());
            mediaViewHolder.duration.setText("" + mMediaList.get(i).getDuration());
            mediaViewHolder.setListener(mOnItemClickListener);
            mediaViewHolder.setMedia(mMediaList.get(i));

            Picasso.get().load(mMediaList.get(i).getThumbnail()).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher).into(mediaViewHolder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        if (mMediaList != null) {
            return mMediaList.size();
        }
        return 0;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mMediaList = mediaList;
        notifyDataSetChanged();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView description;
        TextView duration;
        Media media;
        MediaOnItemClickListener listener;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            duration = itemView.findViewById(R.id.duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(v, media);
                }
            });
        }

        public void setListener(MediaOnItemClickListener listener) {
            this.listener = listener;
        }

        public void setMedia(Media media) {
            this.media = media;
        }
    }

    public interface MediaOnItemClickListener {
        void onItemClickListener(View view, Media media);
    }
}
