package com.example.demo.services.impl;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto)
    {
        Category cat=this.modelMapper.map(categoryDto,Category.class);
        Category addedCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId)
    {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        cat.setCategoryId(categoryDto.getCategoryId());
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updateCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(updateCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId)
    {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));
        this.categoryRepo.delete(cat);
    }


    @Override
    public CategoryDto getCategory(Integer categoryId)
    {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories()
    {
        List<Category> categories=this.categoryRepo.findAll();//getting all the categories
        List<CategoryDto> catDto=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return catDto;
    }
}
