package vn.huynh.tikitest.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.huynh.tikitest.R;

/**
 * Created by duong on 3/28/2019.
 */

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {
    private ArrayList<String> list;
    private Context context;
    private int[] arrayColors;

    public KeywordAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        arrayColors = this.context.getResources().getIntArray(R.array.colors);
    }

    @Override
    public KeywordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword, null, false);
        KeywordViewHolder viewHolder = new KeywordViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KeywordViewHolder holder, final int position) {
        holder.tvKeyword.setText(list.get(holder.getAdapterPosition()));
        try {
            holder.cardView.setCardBackgroundColor(arrayColors[holder.getAdapterPosition() % 9]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        else
            return list.size();
    }

    public class KeywordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_keyword)
        TextView tvKeyword;
        @BindView(R.id.cardView)
        CardView cardView;


        public KeywordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
