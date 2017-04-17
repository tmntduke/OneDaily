package tmnt.example.onedaily.bean.movie;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Cast {

        /**
         * alt : https://movie.douban.com/celebrity/1041020/
         * avatars : {"small":"https://img5.doubanio.com/img/celebrity/small/53186.jpg","large":"https://img5.doubanio.com/img/celebrity/large/53186.jpg","medium":"https://img5.doubanio.com/img/celebrity/medium/53186.jpg"}
         * name : 范·迪塞尔
         * id : 1041020
         */

        private String alt;
        private Avatar avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public Avatar getAvatars() {
            return avatars;
        }

        public void setAvatars(Avatar avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


    }

