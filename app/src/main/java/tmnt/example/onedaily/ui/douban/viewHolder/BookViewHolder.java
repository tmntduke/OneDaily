package tmnt.example.onedaily.ui.douban.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.douban.listener.OnBookItenListener;
import tmnt.example.onedaily.util.BookApiUtils;

/**
 * Created by tmnt on 2017/4/18.
 */

public class BookViewHolder extends BaseViewHolder<Book> {

    private ImageView mCover;
    private TextView mName;
    private TextView mRaing;
    private TextView mAuthor;
    private TextView mPulisher;
    private TextView mSummary;
    private LinearLayout mBookContain;
    private OnBookItenListener mOnBookItenListener;

    public BookViewHolder(View itemView, int type) {
        super(itemView, type);
        mCover = (ImageView) itemView.findViewById(R.id.img_book_cover);
        mName = (TextView) itemView.findViewById(R.id.tv_book_name);
        mRaing = (TextView) itemView.findViewById(R.id.tv_book_raing);
        mAuthor = (TextView) itemView.findViewById(R.id.tv_book_author);
        mPulisher = (TextView) itemView.findViewById(R.id.tv_book_publisher);
        mSummary = (TextView) itemView.findViewById(R.id.tv_book_summary);
        mBookContain = (LinearLayout) itemView.findViewById(R.id.book_contain);
    }


    public void setOnBookItenListener(OnBookItenListener onBookItenListener) {
        mOnBookItenListener = onBookItenListener;
    }


    @Override
    public void setData(Context context, Book book) {
        Glide.with(context).load(book.getImages().getLarge()).into(mCover);
        List<String> authors = book.getAuthor();

        mAuthor.setText(("作者:" + BookApiUtils.getAuthor(authors)));
        mName.setText(book.getTitle());
        mRaing.setText("评分:" + book.getRating().getAverage());
        mPulisher.setText("出版社:" + book.getPublisher());
        mSummary.setText(book.getSummary());
    }


    @Override
    public void setOperation(int position) {
        mBookContain.setOnClickListener(view -> {
                    if (mOnBookItenListener != null) {
                        mOnBookItenListener.onBookItem(view, position);
                    }
                }
        );
    }
}
