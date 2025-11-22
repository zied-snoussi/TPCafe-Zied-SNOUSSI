package tn.esprit.tpcafeziedsnoussi.services.interfaces;

import tn.esprit.tpcafeziedsnoussi.entities.Article;

import java.util.List;

public interface IArticleService {
    Article addArticle(Article article);
    List<Article> saveArticles(List<Article> articles);
    Article selectArticleByIdWithOrElse(Long id);
    List<Article> selectAllArticles();
    Article updateArticle(Article article);
    Article patchArticleById(Long id, Article article);
    void deleteArticle(Article article);
    void deleteAllArticles();
    void deleteArticleById(Long id);
    boolean verifArticleById(Long id);
    void affecterPromotionAArticle(Long idArticle, Long idPromo);
    void desaffecterPromotionAArticle(Long idArticle, Long idPromo);
}
