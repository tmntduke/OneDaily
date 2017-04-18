package tmnt.example.onedaily.ui.douban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.ui.douban.listener.OnBookItenListener;
import tmnt.example.onedaily.ui.douban.viewHolder.BookViewHolder;

/**
 * Created by tmnt on 2017/4/18.
 */

public class BookAdapter extends RecyclerView.Adapter {

    private List<Book> mBooks;
    private Context mContext;
    private OnBookItenListener mOnBookItenListener;

    public void setOnBookItenListener(OnBookItenListener onBookItenListener) {
        mOnBookItenListener = onBookItenListener;
    }

    public BookAdapter(List<Book> books, Context context) {
        mBooks = books;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_book_item, parent);
        BookViewHolder adapter = new BookViewHolder(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookViewHolder bookViewHolder = (BookViewHolder) holder;
        bookViewHolder.setData(mContext, mBooks.get(position));
        bookViewHolder.setOnBookItenListener(mOnBookItenListener);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
