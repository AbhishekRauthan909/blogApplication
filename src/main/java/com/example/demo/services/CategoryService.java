package com.example.demo.services;
import com.example.demo.entities.Category;
import com.example.demo.payloads.CategoryDto;
import java.util.List;

public interface CategoryService
{
    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);


    //get category
    CategoryDto getCategory(Integer categoryId);

    //get all
    List<CategoryDto> getCategories();
}
