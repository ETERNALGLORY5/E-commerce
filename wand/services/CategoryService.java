package huffle.puff.wand.services;

import java.util.List;

import huffle.puff.wand.dtos.CategoryDto;
import huffle.puff.wand.dtos.PageableResponse;



public interface CategoryService {

	
	//CREATE
	// here CategoryDto is a return type of this method
	CategoryDto create (CategoryDto categoryDto);
	
	
	//UPDATE
	CategoryDto update (CategoryDto categoryDto, String categoryId);
	
	//DELETE
	void delete(String categoryId);
	
	//GET ALL
	
	PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy, String sortDir);
	
	
	//GET SINGLE CATEGORY DETAIL
	CategoryDto get(String categoryId);
	
	
	//SEARCH CATEGORY
	List<CategoryDto> searchCategory(String title);
	
	
	
	
	
	
}
