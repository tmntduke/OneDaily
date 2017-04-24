package tmnt.example.onedaily.bean.zhihu;

/**
 * Created by tmnt on 2017/4/24.
 */

public class TopStories {
        /**
         * image : https://pic3.zhimg.com/v2-98568b1a079238f9f7b909ff45997e96.jpg
         * type : 0
         * id : 9377884
         * ga_prefix : 042407
         * title : 对于华为、诺基亚、爱立信和中兴，2017 是个难熬的冬天
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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
    }
