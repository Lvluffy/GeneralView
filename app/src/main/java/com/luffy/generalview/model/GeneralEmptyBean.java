package com.luffy.generalview.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvlufei on 2019/8/6
 *
 * @name
 * @desc
 */
public class GeneralEmptyBean implements Serializable {

    private List<BodyBean> body;

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * allProfessionalLevel : 5
         * resourceNo : 1329804
         * name : 周锦涛
         * id : 9472
         * avatar : http://ks3-cn-beijing.ksyun.com/aojixiaoxi/2018/01/22/2018012210241201703013.jpg
         * type : 咨询顾问
         * totalCount : 0
         * oaid : 9472
         * email : zhoujintao@aoji.cn
         * memberId : 9472
         * studentNo : 146999
         */

        private String allProfessionalLevel;
        private String resourceNo;
        private String name;
        private String id;
        private String avatar;
        private String type;
        private String totalCount;
        private String oaid;
        private String email;
        private String memberId;
        private String studentNo;

        public String getAllProfessionalLevel() {
            return allProfessionalLevel;
        }

        public void setAllProfessionalLevel(String allProfessionalLevel) {
            this.allProfessionalLevel = allProfessionalLevel;
        }

        public String getResourceNo() {
            return resourceNo;
        }

        public void setResourceNo(String resourceNo) {
            this.resourceNo = resourceNo;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getOaid() {
            return oaid;
        }

        public void setOaid(String oaid) {
            this.oaid = oaid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getStudentNo() {
            return studentNo;
        }

        public void setStudentNo(String studentNo) {
            this.studentNo = studentNo;
        }
    }
}
