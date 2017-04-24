package tmnt.example.onedaily.bean.zhihu;

import java.util.List;

/**
 * Created by tmnt on 2017/4/24.
 */

public class Story {

        /**
         * images : ["https://pic3.zhimg.com/v2-8196e1d260ff3abde9822e9129776a86.jpg"]
         * type : 0
         * id : 9379188
         * ga_prefix : 042416
         * title : 有了共享单车后，你们叫网约车的次数减少了吗？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

