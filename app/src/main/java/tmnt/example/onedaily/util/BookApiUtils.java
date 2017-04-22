package tmnt.example.onedaily.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import rx.Observable;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.mvp.CallBack;

public class BookApiUtils {

    public static final int TAG_LEN = 3;


    //public static String Tag_Titles[] = {"综合", "文学", "流行", "生活"};
    public static String HomeTag[] = {"科普", "互联网", "科学", "科技", "科普", "用户体验", "通信", "交互", "旅行", "王小波", "生活", "励志", "成长", "悬疑", "武侠", "韩寒", "奇幻", "青春文学"};
    public static String LiterTag[] = {"海明威", "小说", "中国文学", "村上春树", "王小波", "余华", "鲁迅", "米兰·昆德拉", "外国文学", "经典", "童话", "儿童文学", "名著", "外国名著", "杜拉斯", "文学", "散文", "诗歌", "张爱玲", "钱钟书", "诗词", "港台", "随笔", "日本文学", "杂文", "古典文学", "当代文学", "茨威格", "米兰·昆德拉", "杜拉斯", "港台"};
    public static String CoderTag[] = {"编程", "交互", "设计", "算法", "web", "UE", "互联网", "用户体验", "通信", "交互", "UCD"};
    public static String PopularTag[] = {"漫画", "绘本", "推理", "青春", "言情", "科幻", "东野圭吾", "悬疑", "武侠", "韩寒", "奇幻", "日本漫画", "耽美", "亦舒", " 三毛", "安妮宝贝", "网络小说", "郭敬明", "推理小说", "穿越", "金庸", "轻小说", "几米", "阿加莎·克里斯蒂", "张小娴", "幾米", "魔幻", "青春文学", "J.K.罗琳", "科幻小说", "高木直子", "古龙", "沧月", "蔡康永", "落落", "张悦然"};
    public static String CultureTag[] = {"历史", "心理学", "哲学", "传记", "文化", "社会学", "艺术", "设计", "政治", "社会", "建筑", "宗教", "电影", "数学", "政治学", "回忆录", "思想", "国学", "中国历史", "音乐", "人文", "戏剧", "人物传记", "绘画", "艺术史", "佛教", "军事", "西方哲学", "近代史", "二战", "自由主义", "考古", "美术"};
    public static String LifeTag[] = {"爱情", "旅行", "生活", "励志", "成长", "心理", "摄影", "女性", "职场", "美食", "教育", "游记", "灵修", "情感", "健康", "手工", "养生", "两性", "人际关系", "家居", "自助游"};
    public static String FinancialTag[] = {"经济学", "管理", "经济", "金融", "商业", "投资", "营销", "创业", "理财", "广告", "股票", "企业史", "策划"};
    public static String LogicTag[] = {"东野圭吾", "悬疑", "阿加莎·克里斯蒂", "西村京太郎", "推理", "悬疑小说", "伊坂幸太郎",};
    public static String TechniqueTag[] = {"Android", "python", "Ruby", "PHP", "前端", "数据库", "MongoDB", "Oracle", "Node.js", "爬虫", "Django", "SpringMVC", "c#", "c++", "Java", "Git"};

    public static String[] getApiTag(int pos) {
        switch (pos) {
            case 0:
                return HomeTag;
            case 1:
                return LiterTag;
            case 2:
                return PopularTag;
            case 3:
                return CultureTag;

        }
        return null;
    }


    public static String getRandomTAG(List<String> list) {
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.get(i);
    }

    public static List<String> getRandomLable(int pos) {
        String[] strings = getApiTag(pos);
        List<String> l = Arrays.asList(strings);
        List<String> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int index = random.nextInt(l.size());
            list.add(l.get(index));
        }

        return list;

    }

    public static String getAuthor(List<String> list){
        StringBuilder builder = new StringBuilder();
        int count = list.size();
        for (int i = 0; i < count; i++) {
            builder.append(list.get(i));
            builder.append(" ");
        }
        if (builder != null && builder.length() != 0) {
            builder.deleteCharAt(builder.length() - 1);
        } else {
            builder.append("无");
        }
        return builder.toString();
    }

}
