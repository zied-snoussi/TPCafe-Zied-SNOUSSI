package tn.esprit.tpcafeziedsnoussi.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.exceptions.ResourceNotFoundException;
import tn.esprit.tpcafeziedsnoussi.repositories.ArticleRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IArticleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> saveArticles(List<Article> articles) {
        return articleRepository.saveAll(articles);
    }

    @Override
    public Article selectArticleByIdWithOrElse(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", id));
    }

    @Override
    public List<Article> selectAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article updateArticle(Article article) {
        if (!articleRepository.existsById(article.getIdArticle())) {
            throw new ResourceNotFoundException("Article", "id", article.getIdArticle());
        }
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public void deleteAllArticles() {
        articleRepository.deleteAll();
    }

    @Override
    public void deleteArticleById(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Article", "id", id);
        }
        articleRepository.deleteById(id);
    }

    @Override
    public boolean verifArticleById(Long id) {
        return articleRepository.existsById(id);
    }

    @Override
    public Article patchArticleById(Long id, Article article) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", id));
        
        if (article.getNomArticle() != null) {
            existingArticle.setNomArticle(article.getNomArticle());
        }
        if (article.getPrixArticle() != 0) {
            existingArticle.setPrixArticle(article.getPrixArticle());
        }
        if (article.getTypeArticle() != null) {
            existingArticle.setTypeArticle(article.getTypeArticle());
        }
        
        return articleRepository.save(existingArticle);
    }
}

