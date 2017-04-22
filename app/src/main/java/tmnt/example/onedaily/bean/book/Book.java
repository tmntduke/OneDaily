package tmnt.example.onedaily.bean.book;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import tmnt.example.onedaily.bean.common.Image;
import tmnt.example.onedaily.bean.common.Rating;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Book implements Parcelable {
    /**
     * rating : {"max":10,"numRaters":1,"average":"0.0","min":0}
     * subtitle : Visual QuickStart Guide
     * author : ["Toby Donaldson"]
     * pubdate : 2013-7-15
     * tags : [{"count":1,"name":"python入门","title":"python入门"},{"count":1,"name":"计算机","title":"计算机"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s27175520.jpg
     * binding : Paperback
     * translator : []
     * catalog :
     * pages : 224
     * images : {"small":"https://img3.doubanio.com/spic/s27175520.jpg","large":"https://img3.doubanio.com/lpic/s27175520.jpg","medium":"https://img3.doubanio.com/mpic/s27175520.jpg"}
     * alt : https://book.douban.com/subject/24133123/
     * id : 24133123
     * publisher : Peachpit Press
     * isbn10 : 0321929551
     * isbn13 : 9780321929556
     * title : Python
     * url : https://api.douban.com/v2/book/24133123
     * alt_title :
     * author_intro :
     * summary : Python is a remarkably powerful dynamic programming language used in a wide variety of situations such as Web, database access, desktop GUIs, game and software development, and network programming. Fans of Python use the phrase "batteries included" to describe the standard library, which covers everything from asynchronous processing to zip files. The language itself is a flexible powerhouse that can handle practically any application domain. This task-based tutorial on Python is for those new to the language and walks you through the fundamentals. You'll learn about arithmetic, strings, and variables; writing programs; flow of control, functions; strings; data structures; input and output; and exception handling. At the end of the book, a special section walks you through a longer, realistic application, tying the concepts of the book together.
     * price : USD 34.99
     * ebook_url : https://read.douban.com/ebook/1499455/
     * ebook_price : 38.39
     * series : {"id":"660","title":"图灵程序设计丛书"}
     */

    private Rating rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private Image images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    private String ebook_url;
    private String ebook_price;
    private Series series;
    private List<String> author;
    private List<Tags> tags;
    private List<?> translator;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEbook_url() {
        return ebook_url;
    }

    public void setEbook_url(String ebook_url) {
        this.ebook_url = ebook_url;
    }

    public String getEbook_price() {
        return ebook_price;
    }

    public void setEbook_price(String ebook_price) {
        this.ebook_price = ebook_price;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<?> getTranslator() {
        return translator;
    }

    public void setTranslator(List<?> translator) {
        this.translator = translator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, flags);
        dest.writeString(this.subtitle);
        dest.writeString(this.pubdate);
        dest.writeString(this.origin_title);
        dest.writeString(this.image);
        dest.writeString(this.binding);
        dest.writeString(this.catalog);
        dest.writeString(this.pages);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.alt);
        dest.writeString(this.id);
        dest.writeString(this.publisher);
        dest.writeString(this.isbn10);
        dest.writeString(this.isbn13);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.alt_title);
        dest.writeString(this.author_intro);
        dest.writeString(this.summary);
        dest.writeString(this.price);
        dest.writeString(this.ebook_url);
        dest.writeString(this.ebook_price);
        dest.writeParcelable(this.series, flags);
        dest.writeStringList(this.author);
        dest.writeList(this.tags);
        dest.writeList(this.translator);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.rating = in.readParcelable(Rating.class.getClassLoader());
        this.subtitle = in.readString();
        this.pubdate = in.readString();
        this.origin_title = in.readString();
        this.image = in.readString();
        this.binding = in.readString();
        this.catalog = in.readString();
        this.pages = in.readString();
        this.images = in.readParcelable(Image.class.getClassLoader());
        this.alt = in.readString();
        this.id = in.readString();
        this.publisher = in.readString();
        this.isbn10 = in.readString();
        this.isbn13 = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.alt_title = in.readString();
        this.author_intro = in.readString();
        this.summary = in.readString();
        this.price = in.readString();
        this.ebook_url = in.readString();
        this.ebook_price = in.readString();
        this.series = in.readParcelable(Series.class.getClassLoader());
        this.author = in.createStringArrayList();
        this.tags = new ArrayList<Tags>();
        in.readList(this.tags, Tags.class.getClassLoader());
        this.translator = new ArrayList<String>();
        in.readList(this.translator, Object.class.getClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}


