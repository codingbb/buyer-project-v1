package com.example.buyer.cart;

import com.example.buyer._core.utils.ApiUtil;
import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CartController {
    private final CartService cartService;
    private final HttpSession session;



    //장바구나 삭제 -> 보관할 필요 없을 것 같아서 delete로 함
    @PostMapping("/cart/{id}/delete")
    public String delete(@PathVariable Integer id) {
        cartService.cartDelete(id);
        return "redirect:/cart-form";
    }

    @PostMapping("/cart/update")
    public @ResponseBody String save(@RequestBody List<CartRequest.UpdateDTO> updateDtos) {
        System.out.println(updateDtos);

        // [CartRequest.UpdateDTO(cartId=13, buyQty=81), CartRequest.UpdateDTO(cartId=7, buyQty=20)]
        // DB에서 내 장바구니 내역 조회 (4건)
        // 13과 7이 아닌 애들은 삭제
        // 13과 7은 업데이트

        return "ok";
    }


    //장바구니에 담기
//    @PostMapping("/cart")
//    public String save(@RequestParam("productId") Integer productId, @RequestParam("buyQty") Integer buyQty) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        Boolean cart = cartService.save(sessionUser.getId(), productId, buyQty);
//
//        if (cart == true) {
//            return "redirect:/cart-form";
//        } else {
//            return "/err/duplication";
//        }
//
//    }

    //장바구니 폼
    @GetMapping("/cart-form")
    public String cartForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<CartResponse.CartDTO> cartList = cartService.getCartList(sessionUser.getId());
//        System.out.println("카트리스트 " + cartList);
        request.setAttribute("cartList", cartList);

        return "/order/cart-form";

    }

}
