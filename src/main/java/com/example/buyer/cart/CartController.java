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

    //TODO: 버튼으로 변경 누르는 것 말고, 실시간으로 수량 체크하면서 반영하는건 AJAX 써야겠지요?
    //장바구니 수량 변경
    @PostMapping("/cart/update")
    public String cartUpdate(CartRequest.UpdateDTO requestDTO) {
//        System.out.println(requestDTO);
        cartService.updateQty(requestDTO);

        return "redirect:/cart-form";
    }


    //장바구나 삭제 -> 보관할 필요 없을 것 같아서 delete로 함
    @PostMapping("/cart/{id}/delete")
    public String cartDelete(@PathVariable Integer id) {
        cartService.deleteById(id);
        return "redirect:/cart-form";
    }


    //장바구니에 담기
    @PostMapping("/cart")
    public String cartAdd(@RequestParam("productId") Integer productId, @RequestParam("buyQty") Integer buyQty, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Boolean cart = cartService.save(sessionUser.getId(), productId, buyQty);

        if (cart == true) {
            return "redirect:/cart-form";
        } else {
            return "/err/duplication";
        }

    }

    //장바구니 폼
    @GetMapping("/cart-form")
    public String cartForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<CartResponse.CartDTO> cartList = cartService.findAll(sessionUser.getId());
        System.out.println("카트리스트 " + cartList);
        request.setAttribute("cartList", cartList);

        return "/order/cart-form";

    }

}
