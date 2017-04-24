package tmnt.example.onedaily.bean.zhihu;

import java.util.List;

/**
 * Created by tmnt on 2017/4/24.
 */

public class ZhihuInfo {

    /**
     * date : 20170424
     * stories : [{"images":["https://pic3.zhimg.com/v2-8196e1d260ff3abde9822e9129776a86.jpg"],"type":0,"id":9379188,"ga_prefix":"042416","title":"有了共享单车后，你们叫网约车的次数减少了吗？"},{"images":["https://pic3.zhimg.com/v2-98464feaa952534484a270518f6c36b2.jpg"],"type":0,"id":9379274,"ga_prefix":"042415","title":"学校发的运动服居然来自大品牌，这在日本是怎么实现的？"},{"images":["https://pic1.zhimg.com/v2-b5427533fdb9a14527c0e377aed65c20.jpg"],"type":0,"id":9365229,"ga_prefix":"042415","title":"面试前需要做哪些准备？"},{"images":["https://pic4.zhimg.com/v2-0976aa8d7da56f1f1768cdf1b1e8ceb3.jpg"],"type":0,"id":9378732,"ga_prefix":"042414","title":"人类想要在太空漫游，最大的敌人可能不是外星人\u2026\u2026"},{"images":["https://pic3.zhimg.com/v2-994ed1a8fc8ac75362cb561da9243256.jpg"],"type":0,"id":9367472,"ga_prefix":"042412","title":"大误 · 《情深深雨濛濛》其实是环保剧"},{"images":["https://pic4.zhimg.com/v2-12179c9a5670183817c4330c3d34275f.jpg"],"type":0,"id":9366941,"ga_prefix":"042410","title":"有什么好的（不节食、不运动）减肥方式吗？"},{"images":["https://pic3.zhimg.com/v2-e50f16044867ca57bc401362ac456e36.jpg"],"type":0,"id":9369690,"ga_prefix":"042409","title":"举个例子告诉你为什么关键时刻还是需要律师"},{"images":["https://pic4.zhimg.com/v2-225a4d2a82b71b93cb89addb735bf797.jpg"],"type":0,"id":9376377,"ga_prefix":"042408","title":"学会四道日式腌渍食谱，做饭变得美妙又简单"},{"images":["https://pic3.zhimg.com/v2-d345282273a11f583a76555ea2ef4496.jpg"],"type":0,"id":9376706,"ga_prefix":"042407","title":"换护肤品就过敏？你可能不知道这些"},{"images":["https://pic3.zhimg.com/v2-32949d81ad02fb11e6c82b07cab7c12e.jpg"],"type":0,"id":9377909,"ga_prefix":"042407","title":"为什么这群人领着百万年薪，看起来好像什么都没干？"},{"images":["https://pic1.zhimg.com/v2-11c747a5043b3e4da18b2ac86a794cd8.jpg"],"type":0,"id":9377884,"ga_prefix":"042407","title":"对于华为、诺基亚、爱立信和中兴，2017 是个难熬的冬天"},{"images":["https://pic3.zhimg.com/v2-3fbffbff154194c308d438e2ca4665ea.jpg"],"type":0,"id":9375496,"ga_prefix":"042406","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-98568b1a079238f9f7b909ff45997e96.jpg","type":0,"id":9377884,"ga_prefix":"042407","title":"对于华为、诺基亚、爱立信和中兴，2017 是个难熬的冬天"},{"image":"https://pic3.zhimg.com/v2-decf047d3154bc987e2dbf0344ae9bc2.jpg","type":0,"id":9375775,"ga_prefix":"042219","title":"世界地球日里，我们有这几个故事想讲给你听"},{"image":"https://pic4.zhimg.com/v2-c42e8046e942dedd951ee5c0c139b35b.jpg","type":0,"id":9374087,"ga_prefix":"042213","title":"在阳台上种菜不算稀奇，但如果是在非洲呢？"},{"image":"https://pic1.zhimg.com/v2-8f0ef481d074801b658e9593cffad970.jpg","type":0,"id":9374066,"ga_prefix":"042207","title":"即将上线 WeGame 游戏平台，腾讯下了一步什么棋？"},{"image":"https://pic4.zhimg.com/v2-8cd128739ac6858b96ff186f54008e83.jpg","type":0,"id":9369557,"ga_prefix":"042207","title":"为什么我不赞成「女性要经济独立，所以要抛弃家务」"}]
     */

    private String date;
    private List<Story> stories;
    private List<TopStories> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStories> top_stories) {
        this.top_stories = top_stories;
    }


}
