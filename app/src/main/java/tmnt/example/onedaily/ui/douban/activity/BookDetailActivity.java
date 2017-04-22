package tmnt.example.onedaily.ui.douban.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.mvp.View;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.douban.fragment.BookFragment;
import tmnt.example.onedaily.ui.douban.model.BookDetailModel;
import tmnt.example.onedaily.ui.douban.presenter.BookDetailPresent;
import tmnt.example.onedaily.util.BookApiUtils;

/**
 * Created by tmnt on 2017/4/22.
 */

public class BookDetailActivity extends BaseActivity implements View<Book> {

    @Bind(R.id.img_detail_cover)
    ImageView mImgDetailCover;
    @Bind(R.id.rl_cover)
    RelativeLayout mRlCover;
    @Bind(R.id.tv_detail_title)
    TextView mTvDetailTitle;
    @Bind(R.id.tv_detail_author)
    TextView mTvDetailAuthor;
    @Bind(R.id.tv_detail_publish)
    TextView mTvDetailPublish;
    @Bind(R.id.tv_detail_publish_date)
    TextView mTvDetailPublishDate;
    @Bind(R.id.tv_detail_raing)
    TextView mTvDetailRaing;
    @Bind(R.id.tv_detail_summary)
    TextView mTvDetailSummary;
    @Bind(R.id.tv_detail_author_info)
    TextView mTvDetailAuthorInfo;
    @Bind(R.id.tv_detail_catalog)
    TextView mTvDetailCatalog;
    @Bind(R.id.img_turn)
    ImageView mImgTurn;
    @Bind(R.id.img_share)
    ImageView mImgShare;

    private String book;
    private Intent mIntent;
    private Book mBook;

    private BookDetailPresent mDetailPresent;
    public static final String BOOK_CATALOG = "book_catalog";
    private static final String TAG = "BookDetailActivity";

    @Override
    public void initData(Bundle savedInstanceState) {
        mIntent = getIntent();
        book = mIntent.getStringExtra(BookFragment.BOOK_ID);
        if ("id".equals(book)) {
            mBook = mIntent.getParcelableExtra(BookSearchActivity.BOOK);
        } else if ("isbn".equals(book)) {
            BookDetailModel model = new BookDetailModel();//
            model.setName(mIntent.getStringExtra(BookSearchActivity.BOOK_ISBN));
            mDetailPresent = new BookDetailPresent(model, this);
            mDetailPresent.handleData();
        }
    }

    @Override
    public void initView() {

        setContentView(R.layout.activity_book_details);
        ButterKnife.bind(this);
    }

    @Override
    public void initOperation() {


        mImgTurn.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(BOOK_CATALOG, mBook.getCatalog());
            toActivity(BookCatalogActivity.class, bundle);
        });

        mImgShare.setOnClickListener(v -> {

        });

    }

    @Override
    public void loadData() {
        if (mBook != null) {
            setData(mBook);
        }
    }

    @Override
    public void showData(Book datas) {
        mBook = datas;
        Log.i(TAG, "showData: " + mBook);
        setData(datas);
    }

    @Override
    public void showLoadData(Book datas) {

    }

    @Override
    public void showRefreshData(Book datas) {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    private void setData(Book book) {
//        Glide.with(this).load(book.getImages().getLarge()).into(mImgDetailCover);

        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Glide.with(BookDetailActivity.this)
                        .load(book.getImages().getLarge()).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Log.i(TAG, "onResourceReady: " + resource);
                        subscriber.onNext(resource);
                    }
                });
            }
        }).subscribe(bitmap -> {
            set(book, bitmap);
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void set(Book book, Bitmap b) {
        Palette.from(b)
                .generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int vibrant = palette.getVibrantColor(Color.WHITE);
                        mRlCover.setBackgroundColor(vibrant);
                        Log.i(TAG, "onGenerated: " + vibrant);
                    }
//                    else {
//                            Palette.Swatch s = palette.getDarkMutedSwatch();
//                            mRlCover.setBackgroundColor(s.getRgb());
//                            setStatesBar(s.getRgb());
//                        }


                });
        mImgDetailCover.setImageBitmap(b);
        mTvDetailAuthor.setText(BookApiUtils.getAuthor(book.getAuthor()));
        mTvDetailAuthorInfo.setText(book.getAuthor_intro());
        if (TextUtils.isEmpty(mBook.getCatalog())) {
            mTvDetailCatalog.setText("无");
            mImgTurn.setVisibility(android.view.View.GONE);
        } else {
            mTvDetailCatalog.setText(book.getCatalog());
            mImgTurn.setVisibility(android.view.View.VISIBLE);
        }
        mTvDetailPublish.setText(book.getPublisher());
        mTvDetailPublishDate.setText(book.getPubdate());
        mTvDetailRaing.setText(book.getRating().getAverage());
        mTvDetailTitle.setText(book.getTitle());
        mTvDetailSummary.setText(book.getSummary());
    }

}
