package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Article Management", description = "APIs for managing articles/products in the TPCafe system")
public class ArticleRestController {

    private final IArticleService articleService;
    private final ArticleMapper articleMapper;

    @PostMapping
    @Operation(summary = "Create a new article", description = "Creates a new article/product in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article created successfully",
                    content = @Content(schema = @Schema(implementation = ArticleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<ArticleDTO> addArticle(
            @Parameter(description = "Article data to create", required = true)
            @Valid @RequestBody ArticleDTO articleDTO) {
        var article = articleMapper.toEntity(articleDTO);
        var savedArticle = articleService.addArticle(article);
        return ResponseEntity.ok(articleMapper.toDTO(savedArticle));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple articles", description = "Creates multiple articles in a single operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articles created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<List<ArticleDTO>> addArticles(
            @Parameter(description = "List of articles to create", required = true)
            @RequestBody List<ArticleDTO> articleDTOs) {
        var articles = articleDTOs.stream()
                .map(articleMapper::toEntity)
                .collect(Collectors.toList());
        var savedArticles = articleService.saveArticles(articles);
        return ResponseEntity.ok(savedArticles.stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get article by ID", description = "Retrieves a specific article by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article found",
                    content = @Content(schema = @Schema(implementation = ArticleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    public ResponseEntity<ArticleDTO> getArticleById(
            @Parameter(description = "ID of the article to retrieve", required = true)
            @PathVariable Long id) {
        var article = articleService.selectArticleByIdWithOrElse(id);
        return ResponseEntity.ok(articleMapper.toDTO(article));
    }

    @GetMapping
    @Operation(summary = "Get all articles", description = "Retrieves a list of all articles in the system")
    @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully")
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        var articles = articleService.selectAllArticles();
        return ResponseEntity.ok(articles.stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping
    @Operation(summary = "Update an article", description = "Updates an existing article (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article updated successfully",
                    content = @Content(schema = @Schema(implementation = ArticleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<ArticleDTO> updateArticle(
            @Parameter(description = "Article data to update", required = true)
            @Valid @RequestBody ArticleDTO articleDTO) {
        var article = articleMapper.toEntity(articleDTO);
        var updatedArticle = articleService.updateArticle(article);
        return ResponseEntity.ok(articleMapper.toDTO(updatedArticle));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an article", description = "Updates specific fields of an existing article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article partially updated successfully",
                    content = @Content(schema = @Schema(implementation = ArticleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    public ResponseEntity<ArticleDTO> patchArticle(
            @Parameter(description = "ID of the article to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial article data to update", required = true)
            @RequestBody ArticleDTO articleDTO) {
        var article = articleMapper.toEntity(articleDTO);
        var patchedArticle = articleService.patchArticleById(id, article);
        return ResponseEntity.ok(articleMapper.toDTO(patchedArticle));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete article by ID", description = "Deletes a specific article by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    public ResponseEntity<Void> deleteArticleById(
            @Parameter(description = "ID of the article to delete", required = true)
            @PathVariable Long id) {
        articleService.deleteArticleById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all articles", description = "Deletes all articles from the system")
    @ApiResponse(responseCode = "200", description = "All articles deleted successfully")
    public ResponseEntity<Void> deleteAllArticles() {
        articleService.deleteAllArticles();
        return ResponseEntity.ok().build();
    }
}
