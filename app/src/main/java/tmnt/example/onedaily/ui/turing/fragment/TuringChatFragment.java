package tmnt.example.onedaily.ui.turing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.chat.ChatInfo;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.turing.adapter.ChatAdapter;

/**
 * Created by tmnt on 2017/6/1.
 */

public class TuringChatFragment extends BaseFragment implements tmnt.example.onedaily.mvp.View<String> {

    @Bind(R.id.rv_turing)
    RecyclerView mRvTuring;
    @Bind(R.id.ed_input_text_send)
    EditText mEdInputTextSend;
    @Bind(R.id.img_send)
    ImageView mImgSend;
    @Bind(R.id.input_text_layout)
    LinearLayout mInputTextLayout;

    private View mView;
    private List<ChatInfo> mChats;
    private ChatAdapter mChatAdapter;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_turing, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mChats = new ArrayList<>();
        mChatAdapter = new ChatAdapter(mChats, getActivity());
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

        mRvTuring.setLayoutManager(new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false));
        mRvTuring.setAdapter(mChatAdapter);

        mImgSend.setOnClickListener(v -> {
            String send = mEdInputTextSend.getText().toString();
            mChats.add(new ChatInfo(send, ChatAdapter.SENG_TYPE));
        });

    }

    @Override
    public void loadData() {
        testData();
        mChatAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static Fragment getInstance() {
        Fragment fragment = new TuringChatFragment();
        return fragment;
    }

    @Override
    public void showData(String datas) {

    }

    @Override
    public void showLoadData(String datas) {

    }

    @Override
    public void showRefreshData(String datas) {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    private void testData() {
        mChats.add(new ChatInfo("hello i am send", ChatAdapter.SENG_TYPE));
        mChats.add(new ChatInfo("hello i am receive", ChatAdapter.RECEIVE_TYPE));
        mChats.add(new ChatInfo("how are you", ChatAdapter.SENG_TYPE));
        mChats.add(new ChatInfo("I AM fine", ChatAdapter.RECEIVE_TYPE));

    }
}
