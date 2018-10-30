package com.roger.makecoffee;

import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.writearticle.WriteArticleContract;
import com.roger.makecoffee.writearticle.WriteArticlePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class isPostingArticleLegal {
    private WriteArticleContract.View mView;
    private WriteArticlePresenter mPresenter;
    private NewArticle mArticle;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mView = mock(WriteArticleContract.View.class);
        mPresenter = new WriteArticlePresenter(mView);
        mArticle = mock(NewArticle.class);
    }

    @Test
    public void articleIsIllegal() {
        Mockito.when(mArticle.getTitle()).thenReturn("");
        Mockito.when(mArticle.getContent()).thenReturn("");
        Mockito.when(mArticle.getImageUrl()).thenReturn("");
        mPresenter.isArticleContentLegal(mArticle);

        verify(mView, times(1)).showToast(anyString());
    }

    @Test
    public void articleIsLegal() {
        Mockito.when(mArticle.getTitle()).thenReturn("123");
        Mockito.when(mArticle.getContent()).thenReturn("123");
        Mockito.when(mArticle.getImageUrl()).thenReturn("123");
        assertTrue(mPresenter.isArticleContentLegal(mArticle));
    }

}
