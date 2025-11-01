package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.entities.Article;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IArticleService;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ArticleRestController {

    private final IArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.addArticle(article));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Article>> addArticles(@RequestBody List<Article> articles) {
        return ResponseEntity.ok(articleService.saveArticles(articles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.selectArticleByIdWithOrElse(id));
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.selectAllArticles());
    }

    @PutMapping
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.updateArticle(article));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllArticles() {
        articleService.deleteAllArticles();
        return ResponseEntity.ok().build();
    }
}
