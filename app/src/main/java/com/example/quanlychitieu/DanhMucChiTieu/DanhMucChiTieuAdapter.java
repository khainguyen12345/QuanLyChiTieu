package com.example.quanlychitieu.DanhMucChiTieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

import java.util.List;

public class DanhMucChiTieuAdapter extends RecyclerView.Adapter<DanhMucChiTieuAdapter.DanhMucChiTieuViewHolder> {

    private List<DanhMucChiTieu> mDanhMucChiTieuList;
    private Context mContext;

    public DanhMucChiTieuAdapter(Context context) {
        this.mContext = context;
    }
    public void setData(List<DanhMucChiTieu> danhMucChiTieus) {
        this.mDanhMucChiTieuList = danhMucChiTieus;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DanhMucChiTieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thuchi, parent, false);
        return new DanhMucChiTieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucChiTieuViewHolder holder, int position) {
        DanhMucChiTieu danhMucChiTieu = mDanhMucChiTieuList.get(position);
        if(danhMucChiTieu == null ) {
            return;
        }

        holder.viewDanhmucChiTieu.setText(danhMucChiTieu.getNote());
        if(danhMucChiTieu.getTrangthai_thuchi() == 0) {
            holder.viewTienChiTieu.setText("-" + danhMucChiTieu.getMoney());
            holder.viewTienChiTieu.setTextColor(R.color.red);
        }else if(danhMucChiTieu.getTrangthai_thuchi() == 1) {
            holder.viewTienChiTieu.setText("+" + danhMucChiTieu.getMoney());
            holder.viewTienChiTieu.setTextColor(R.color.blue);
        }




    }

    @Override
    public int getItemCount() {
        if(mDanhMucChiTieuList!=null) {
            return mDanhMucChiTieuList.size();
        }
        return 0;
    }

    public class DanhMucChiTieuViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item_thuchi;
        private TextView viewDanhmucChiTieu, viewTienChiTieu;

        public DanhMucChiTieuViewHolder(@NonNull View itemView) {
            super(itemView);
            item_thuchi = itemView.findViewById(R.id.item_thuchi);
            viewDanhmucChiTieu = itemView.findViewById(R.id.viewDanhmucChiTieu);
            viewTienChiTieu = itemView.findViewById(R.id.viewTienChiTieu);
        }
    }
}
