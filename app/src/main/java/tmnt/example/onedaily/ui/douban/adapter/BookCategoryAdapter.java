package tmnt.example.onedaily.ui.douban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.douban.listener.OnTextClickLlistener;
import tmnt.example.onedaily.ui.douban.viewHolder.BookCategoryHolder;

/**
 * Created by tmnt on 2017/9/17.
 */

public class BookCategoryAdapter extends RecyclerView.Adapter implements ItemDragHelperCallback.OnItemMoveListener {

    private Context mContext;
    private List<String> mData;
    private OnTextClickLlistener mOnTextClickLlistener;
    private ItemDragHelperCallback mItemDragHelperCallback;
    private boolean isCanMove;

    public BookCategoryAdapter(Context context, List<String> data, boolean isCanMove) {
        mContext = context;
        mData = data;
        this.isCanMove = isCanMove;
    }

    public void setItemDragHelperCallback(ItemDragHelperCallback itemDragHelperCallback) {
        mItemDragHelperCallback = itemDragHelperCallback;
    }

    public void setOnTextClickLlistener(OnTextClickLlistener onTextClickLlistener) {
        mOnTextClickLlistener = onTextClickLlistener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_book_category_item, parent, false);
        BookCategoryHolder holder = new BookCategoryHolder(view, isCanMove, mContext, 0);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookCategoryHolder swapHolder = (BookCategoryHolder) holder;
        swapHolder.setData(mData.get(position));
        swapHolder.setItemDragHelperCallback(mItemDragHelperCallback);
        swapHolder.setOperation(position);
        swapHolder.setOnTextClickLlistener(mOnTextClickLlistener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mData, fromPosition, toPosition);//利用Collections对数据源交换
        notifyItemMoved(fromPosition, toPosition);//RecyclerView中的方法，可以交换item
        return true;
    }
}
