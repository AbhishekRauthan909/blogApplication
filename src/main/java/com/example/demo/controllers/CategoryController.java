package com.example.demo.controllers;
import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.payloads.UserDto;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{

   @Autowired
   private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
    {
        CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto,categoryId);
        return  ResponseEntity.ok(updateCategory);
    }
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cId)
    {
        this.categoryService.deleteCategory(cId);
        return new ResponseEntity<>(new ApiResponse("category Deleted successfully",true),HttpStatus.OK);
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
    //get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getUser(@PathVariable("categoryId") Integer cId)
    {
        return ResponseEntity.ok(this.categoryService.getCategory(cId));
    }

}
