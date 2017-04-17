package tmnt.example.onedaily.bean.movie;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Avatar {

        /**
         * small : https://img5.doubanio.com/img/celebrity/small/53186.jpg
         * large : https://img5.doubanio.com/img/celebrity/large/53186.jpg
         * medium : https://img5.doubanio.com/img/celebrity/medium/53186.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
}
