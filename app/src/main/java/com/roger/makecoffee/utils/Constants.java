package com.roger.makecoffee.utils;

public class Constants {
    public static final String TAG = "Make Coffee";

    public static final String NEWS_URL =
            "https://newsapi.org/v2/everything?q=coffee&apiKey=af49a2f28f1f4e78b1267bb01efadaa3";

    public static final String PRIVACY_POLICY_URL = "https://rogertsai917.wixsite.com/makecoffee";

    public static final int GET_IMAGE_FROM_ALBUM = 0x21;

    // News page view type
    public static final int VIEW_TYPE_NEWS_LOADING = 0;
    public static final int VIEW_TYPE_NEWS_BIG = 1;
    public static final int VIEW_TYPE_NEWS_SMALL = 2;

    public static final int VIEW_TYPE_WRITE_ARTICLE_TITLE = 0;
    public static final int VIEW_TYPE_WRITE_ARTICLE_COFFEE_DETAIL = 1;
    public static final int VIEW_TYPE_WRITE_ARTICLE_COFFEE_FLAVOR = 2;

    public static final int VIEW_TYPE_ARTICLES_LOADING = 0;
    public static final int VIEW_TYPE_ARTICLES_LIST = 1;

    public static final int VIEW_TYPE_ARTICLE_TITLE = 0;
    public static final int VIEW_TYPE_ARTICLE_COFFEE_DETAIL = 1;
    public static final int VIEW_TYPE_ARTICLE_COFFEE_FLAVOR = 2;

    // News Api
    public static final String STATUS = "status";
    public static final String OK = "ok";
    public static final String ARTICLES = "articles";
    public static final String SOURCE = "source";
    public static final String NAME = "name";
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String URL_TO_IMAGE = "urlToImage";
    public static final String PUBLISH_AT = "publishedAt";
    public static final String CONTENT = "content";

    public static final String PREPARE = "prepare";
    public static final String WATER_TEMP = "waterTemp";
    public static final String TIMING = "timing";
    public static final String NORMAL = "normal";
    public static final String COMPLETED = "completed";

    public static final String USER_DATA = "user_data";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PHOTO_URL = "user_photo_url";
    public static final String USER_UID = "user_uid";

    public static final String ARTICLES_PHOTOS = "articlesPhotos/";

    public static final String USERS = "users";

    public static final String POSTED_ARTICLES = "postedArticles";
    public static final String LIKED_ARTICLES = "likedArticles";

    public static final String CREATE_TIME = "createdTime";
    public static final String ADD_TIME = "addTime";

    public static final int RC_SIGN_IN = 9001;
}
