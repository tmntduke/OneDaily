package tmnt.example.onedaily.ui.douban.viewHolder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.util.BookApiUtils;

/**
 * Created by tmnt on 2017/4/22.
 */

public class BookSearchViewHolder extends BaseViewHolder<Book> {
    private ImageView imgSearchCover;
    private TextView tvSearchTilte;
    private TextView tvSearchAuthor;
    private LinearLayout searchItem;
    private OnItemClickListener mOnItemClickListener;

    private static final String TAG = "BookSearchViewHolder";

    public BookSearchViewHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        imgSearchCover = (ImageView) itemView.findViewById(R.id.img_search_cover);
        tvSearchTilte = (TextView) itemView.findViewById(R.id.tv_search_title);
        tvSearchAuthor = (TextView) itemView.findViewById(R.id.tv_search_author);
        searchItem= (LinearLayout) itemView. findViewById(R.id.search_item);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void setData(Book book) {
        Log.i(TAG, "setData: "+book.getTitle());
        Glide.with(mContext).load(book.getImages().getLarge()).into(imgSearchCover);
        tvSearchTilte.setText(book.getTitle());

        tvSearchAuthor.setText(BookApiUtils.getAuthor(book.getAuthor()));
    }

    @Override
    public void setOperation( int position) {
        searchItem.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, position);
            }
        });


    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
