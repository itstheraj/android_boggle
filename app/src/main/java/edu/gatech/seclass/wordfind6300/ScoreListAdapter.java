package edu.gatech.seclass.wordfind6300;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

/*
public class ScoreListAdapter extends RecyclerView.Adapter {
    private List<String> list;
    public ScoreListAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyScoreListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.textlist_view_layout, parent, false);
        MyScoreListViewHolder scoreListViewHolder = new MyScoreListViewHolder(textView);
        return scoreListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyScoreListViewHolder holder, int position) {
        holder.VersionName.setText(list.get(position));
        return;
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyScoreListViewHolder extends RecyclerView.ViewHolder
    {
        TextView VersionName;

        public MyScoreListViewHolder(@NonNull TextView itemView) {
            super(itemView);
            VersionName = itemView;
        }
    }
}
 */
