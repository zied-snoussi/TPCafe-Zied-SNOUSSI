package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.dtos.ArticleDTO;
import tn.esprit.tpcafeziedsnoussi.mappers.ArticleMapper;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IArticleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ArticleRestController {

    private final IArticleService articleService;
    private final ArticleMapper articleMapper;

    @PostMapping
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO articleDTO) {
        var article = articleMapper.toEntity(articleDTO);
        var savedArticle = articleService.addArticle(article);
        return ResponseEntity.ok(articleMapper.toDTO(savedArticle));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ArticleDTO>> addArticles(@RequestBody List<ArticleDTO> articleDTOs) {
        var articles = articleDTOs.stream()
                .map(articleMapper::toEntity)
                .collect(Collectors.toList());
        var savedArticles = articleService.saveArticles(articles);
        return ResponseEntity.ok(savedArticles.stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        var article = articleService.selectArticleByIdWithOrElse(id);
        return ResponseEntity.ok(articleMapper.toDTO(article));
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        var articles = articleService.selectAllArticles();
        return ResponseEntity.ok(articles.stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO) {
        var article = articleMapper.toEntity(articleDTO);
        var updatedArticle = articleService.updateArticle(article);
        return ResponseEntity.ok(articleMapper.toDTO(updatedArticle));
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
