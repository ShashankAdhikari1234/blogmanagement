package com.example.blogappapis.controllers;

import com.example.blogappapis.payloads.ApiResponse;
import com.example.blogappapis.payloads.CategoryDto;
import com.example.blogappapis.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto>creatCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto created = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(created, HttpStatus.CREATED);


    }
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid  @RequestBody CategoryDto categoryDto, @PathVariable  Integer id){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse>deleteCategory(@PathVariable(value = "categoryId") Integer id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("category is deleted successfully!!",true), HttpStatus.BAD_REQUEST);


    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer id){
        CategoryDto categoryDto = this.categoryService.getCategory(id);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);

    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getCategories(){
        List<CategoryDto>  categories = this.categoryService.getCategories();
        return ResponseEntity.ok(categories);


    }



}
