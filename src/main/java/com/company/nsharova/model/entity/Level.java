package com.company.nsharova.model.entity;

public enum Level {
        BEGINNER(1), INTERMEDIATE(2), EXPERT(3), ALL_LEVELS(4);

        private final int userLevel;

        Level(int userLevel) {
            this.userLevel = userLevel;
        }

        public int getAccessLevel() {
            return userLevel;
        }
}
