package tmnt.example.onedaily.ui.douban.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
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
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.bean.msg.Collect;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.View;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.douban.fragment.BookFragment;
import tmnt.example.onedaily.ui.douban.model.BookDetailModel;
import tmnt.example.onedaily.ui.douban.presenter.BookDetailPresent;
import tmnt.example.onedaily.ui.main.activity.CollectListActivity;
import tmnt.example.onedaily.util.BookApiUtils;

/**
 * 图书详细信息
 * Created by tmnt on 2017/4/22.
 */
@ContentView(R.layout.activity_book_details)
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
    @Bind(R.id.img_collect)
    ImageView mImgCollect;
    @Bind(R.id.cd_collect)
    CardView mCdCollect;

    private String book;
    private Intent mIntent;
    private Book mBook;
    private OneDailyDB mOneDailyDB;
    private boolean isCollect;
    private BookDetailPresent mDetailPresent;
    private CollectHandle mCollectHandle = new CollectHandle();
    public static final String BOOK_CATALOG = "book_catalog";
    private static final String TAG = "BookDetailActivity";
    private static final int CHANGE_COLLECT = 2013;

    @Override
    public void initData(Bundle savedInstanceState) {
        mIntent = getIntent();
        book = mIntent.getStringExtra(BookFragment.BOOK_ID);
        BookDetailModel model = new BookDetailModel();//
        Log.i(TAG, "initData: " + book);
        model.setType(book);
        if ("id".equals(book)) {
            mBook = mIntent.getParcelableExtra(BookSearchActivity.BOOK);
        } else {
            if ((BookDetailModel.ISBN_TYPE.equals(book))) {
                model.setName(mIntent.getStringExtra(BookSearchActivity.BOOK_ISBN));
            } else if (BookDetailModel.COLLECT_TYPE.equals(book)) {
                model.setBookId(mIntent.getStringExtra(CollectListActivity.COLLECT_ID));
            }

            mDetailPresent = new BookDetailPresent(model, this);
            mDetailPresent.handleData();
        }


        mOneDailyDB = OneDailyDB.newInstance(getApplicationContext());
    }

    @Override
    public void initView() {
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

        mImgCollect.setOnClickListener(v -> collectBook());
    }

    @Override
    public void loadData() {
        if (mBook != null) {
            setData(mBook);
            changeCollect(mBook);
        }
    }

    /**
     * 取消收藏
     *
     * @param book
     */
    private void changeCollect(Book book) {
        mOneDailyDB.queryCollectBook(book.getId(), new CallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                isCollect = aBoolean;
                Log.i(TAG, "onSuccess: " + isCollect);
                if (aBoolean) {
                    mImgCollect.setImageResource(R.drawable.ic_collection_pro);
                } else {
                    mImgCollect.setImageResource(R.drawable.ic_collection);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void showData(Book datas) {
        mBook = datas;
        Log.i(TAG, "showData: " + mBook);
        setData(datas);
        changeCollect(datas);
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

    /**
     * 收藏图书
     */
    private void collectBook() {
        Log.i(TAG, "collectBook: " + isCollect);
        if (isCollect) {
            mOneDailyDB.deleteCollect(mBook.getId(), new CallBack<Boolean>() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    mCollectHandle.sendEmptyMessage(CHANGE_COLLECT);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        } else {
            Collect collect = new Collect();
            collect.setId(mBook.getId());
            collect.setAuthor(mTvDetailAuthor.getText().toString());
            collect.setImage(mBook.getImages().getLarge());
            collect.setTitle(mBook.getTitle());
            mOneDailyDB.insertCollect(collect, new CallBack<Boolean>() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    if (aBoolean)
                        mCollectHandle.sendEmptyMessage(CHANGE_COLLECT);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }


    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOneDailyDB != null) {
            mOneDailyDB.closeDB();
            mOneDailyDB = null;
        }
    }

    /**
     * 装填数据
     *
     * @param book
     */
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

    /**
     * 根据不同图书封面改变背景图
     *
     * @param book
     * @param b
     */
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

    /**
     * 收藏图书或取消后发送通知改变按钮状态
     * 可以使用EventBus
     */
    class CollectHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == CHANGE_COLLECT) {
                if (mBook != null)
                    changeCollect(mBook);
            }
        }
    }

}
