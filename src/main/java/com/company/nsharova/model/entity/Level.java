package com.company.nsharova.model.entity;

public enum Level {
        BEGGINER(1),
        INTERMEDIATE(2),
        EXPERT(3),
        ALL_LEVELS(4);

        private final int userLevel;

        Level(int userRole) {
            this.userLevel = userRole;
        }
        public int getAccessLevel() {
            return userLevel;
        }
}
