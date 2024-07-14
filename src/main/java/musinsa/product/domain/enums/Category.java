package musinsa.product.domain.enums;

import musinsa.config.EnumType;

public enum Category implements EnumType {
    상의("상의"), 아우터("아우터"), 바지("바지"), 스니커즈("스니커즈"), 가방("가방"), 모자("모자"), 양말("양말"), 악세사리("악세사리");

    private final String description;


    Category(String description) {
        this.description = description;
    }


    public static int getCategoryCount() {
        return Category.values().length;
    }


    @Override
    public String getName() {
        return this.name();
    }


    @Override
    public String getDescription() {
        return this.description;
    }
}
