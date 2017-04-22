package tmnt.example.onedaily.ui.douban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.ui.douban.viewHolder.BookSearchViewHolder;

/**
 * Created by tmnt on 2017/4/22.
 */

public class BookSearchAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Book> mBooks;
    private BookSearchViewHolder.OnItemClickListener mOnItemClickListener;

    private static final String TAG = "BookSearchAdapter";

    public BookSearchAdapter(Context context, List<Book> books) {
        mContext = context;
        mBooks = books;

    }

    public void setOnItemClickListener(BookSearchViewHolder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_search_item, parent, false);
        BookSearchViewHolder bookSearchViewHolder = new BookSearchViewHolder(view);
        return bookSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: "+mBooks.size());
        BookSearchViewHolder bookSearchViewHolder = (BookSearchViewHolder) holder;
        bookSearchViewHolder.setData(mContext, mBooks.get(position));
        bookSearchViewHolder.setOnItemClickListener(mOnItemClickListener);
        bookSearchViewHolder.setOperation(position);

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }
}
