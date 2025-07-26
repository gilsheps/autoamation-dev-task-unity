package com.home.task.unity.utils;

public class Enums {
    public enum PostStatus {
        ACTIVE("ACTIVE"), REMOVED("REMOVED");

        private String postStatus;

        PostStatus(String postStatus) {
            this.postStatus = postStatus;
        }

        @Override
        public String toString() {
            return postStatus;
        }
    }
}
