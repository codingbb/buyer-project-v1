package com.example.buyer.cart;

import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//        System.out.println(cartList);
        request.setAttribute("cartList", cartList);

        return "/order/cart-form";

    }

}
