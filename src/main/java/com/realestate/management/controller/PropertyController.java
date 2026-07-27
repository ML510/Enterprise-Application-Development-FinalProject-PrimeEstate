package com.realestate.management.controller;

import com.realestate.management.Dto.PropertyDto;
import com.realestate.management.enums.PropertyStatus;
import com.realestate.management.service.PropertyService;
import com.realestate.management.util.ApiResponse;
import com.realestate.management.util.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/property")
@RequiredArgsConstructor
public class PropertyController {

    final PropertyService propertyService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody PropertyDto propertyDto) {
        return propertyService.add(propertyDto);
    }

    @GetMapping("/get-all")
    public ApiResponse getAll(){
        return propertyService.getAll();
    }

    @GetMapping("/get-by-status/{status}")
    public ApiResponse getBYStatus(@PathVariable PropertyStatus status){
        return propertyService.getByStatus(status);
    }

    @GetMapping("/search/{name}/{state}/{price}")
    public ApiResponse getBySearch(@PathVariable String name,@PathVariable String state,@PathVariable Double price){
        return propertyService.getBySearch(name,state,price);
    }

    @GetMapping("/get-by-id")
    public ApiResponse getById(@RequestParam Integer id){
        return propertyService.getById(id);
    }

    @GetMapping("/get-related-collection")
    public ApiResponse getRelatedCollection(){
        return propertyService.getRelatedCollection();
    }

    @GetMapping("/get-count")
    public ApiResponse getCount(){
        return propertyService.getCount();
    }

    @GetMapping("/get")
    public ApiResponse<Page<List<PropertyDto>>> get(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "4") Integer size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ){
        return propertyService.get(page,size,sort,direction);
    }

    @GetMapping("/get-with-filters")
    public ApiResponse<Page<List<PropertyDto>>> getWithFilters(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "4") Integer size,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String state,
            @RequestParam(defaultValue = "") Double price
    ){
        return null;
        //return propertyService.get(page,size,name,state,price);
    }
}
