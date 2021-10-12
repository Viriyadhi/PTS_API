package com.example.pts_api;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {

    private List<MovieModel> dataList;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MovieAdapter  (Context mContext, List<MovieModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_list, parent, false);
        return new ListViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.tv_title.setText(dataList.get(position).getOriginal_title());
        holder.tv_date.setText(dataList.get(position).getRelease_date());
        holder.tv_rating.setText(dataList.get(position).getVote_average());
        holder.tv_overview.setText(dataList.get(position).getOverview());

        //glide
        Glide.with(mContext)
                .load(dataList.get(position).getPoster_path())
                .apply(new RequestOptions().override(240,160))
                .into(holder.img_list);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_date, tv_rating,tv_overview;
        private ImageView img_list;
        private RelativeLayout relativeLayout;

        public ListViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.title);
            tv_date = itemView.findViewById(R.id.release_date);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_overview = itemView.findViewById(R.id.overview);
            img_list = itemView.findViewById(R.id.poster);
            relativeLayout = itemView.findViewById(R.id.rv_layout_list);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}



