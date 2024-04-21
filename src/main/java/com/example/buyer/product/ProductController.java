package com.example.buyer.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product-list")
    public String productList(HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAllList();
        request.setAttribute("productList", productList);
        return "/product/product-list";
    }

    @GetMapping("/buy-list")
    public String buyList(HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAllList();
        request.setAttribute("productList", productList);
        return "/product/buy-list";
    }


    //상품 삭제
    @PostMapping("/product/{id}/delete")
    public String deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
        //product/list를 반환하는 컨트롤러 존재 -> redirect 할 것
        //어려우면 PostMapping은 redirect로 준다고 생각하세요
        return "redirect:/product";
    }

    //상품 목록보기
    @GetMapping("/product")
    public String listForm(HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAllList();
        request.setAttribute("productList", productList);

        return "buy-list";
    }

    //상품 상세보기
    @GetMapping("/product/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        ProductResponse.DetailDTO product = productService.findByIdDetail(id);
        request.setAttribute("product", product);
        return "/product/detail";
    }

    // 상품 등록
    @PostMapping("/product/save")
    public String save(ProductRequest.SaveDTO requestDTO) {
        productService.save(requestDTO);
        return "redirect:/product";
    }


    @GetMapping("/product/save-form")
    public String saveForm() {

        return "/product/save-form";
    }

    //업데이트 폼 (업데이트는 2개가 나와야합니다 ^^)
    @GetMapping("/product/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        ProductResponse.UpdateDTO product = productService.findByIdUpdate(id);
        request.setAttribute("product", product);
        return "/product/update-form";
    }

    //업데이트
    @PostMapping("/product/{id}/update")
    public String update(@PathVariable Integer id, ProductRequest.UpdateDTO requestDTO) {
        productService.updateById(id, requestDTO);
        return "redirect:/product";
    }


    @GetMapping("/")
    public String main(HttpServletRequest request) {
        List<ProductResponse.MainDTO> productList = productService.findAllMain();
        request.setAttribute("productList", productList);
        return "/index";
    }

    //상품명 실시간 중복체크
    @GetMapping("/product/name-check")
    public @ResponseBody String nameSameCheck(String name) {
        Product product = productService.findByName(name);
        if (product == null) {
            return "true"; //상품 등록 가능
        } else {
            return "false"; //상품 등록 불가
        }
    }

    //상품명 실시간 중복체크 (업데이트용)
    @GetMapping("/product/name-check/update")
    public @ResponseBody String nameSameCheckUpdate(String name, Integer id) {
        Product product = productService.findByNameUpdate(name, id);
        if (product == null) {
            return "true"; //상품 등록 가능
        } else {
            return "false"; //상품 등록 불가
        }
    }




}
