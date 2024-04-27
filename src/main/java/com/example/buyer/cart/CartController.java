package com.example.buyer.cart;

import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CartController {
    private final CartService cartService;
    private final HttpSession session;

    //TODO : 같은 상품인 경우 수량만 증가하도록 해야함 + 폼 수정도
    //TODO : GetMapping으로 되는데 PostMapping으로 order 걸어놓은거 많다! 고쳐라!!

    //장바구니
    @PostMapping("/cart")
    public String cartAdd(@RequestParam("productId") Integer productId, @RequestParam("buyQty") Integer buyQty, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        cartService.save(sessionUser.getId(), productId, buyQty);

        return "redirect:/cart-form";
    }


    //TODO : 중복 물품 담기는거 막자 !
    //장바구니 폼
    @GetMapping("/cart-form")
    public String cartForm(HttpServletRequest request) {
        List<CartResponse.CartDTO> cartList = cartService.findAll();
        System.out.println(cartList);
        request.setAttribute("cartList", cartList);

        return "/order/cart-form";

    }

}
