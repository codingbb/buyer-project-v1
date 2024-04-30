package com.example.buyer.cart;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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

    //장바구니 업데이트 + 딜리트 .. 장바구니에서 구매하기 눌렀을 때
    @PostMapping("/cart/update")
    public @ResponseBody String update(@RequestBody List<CartRequest.UpdateDTO> updateDTOs) {
        System.out.println(updateDTOs);
        User sessionUser = (User) session.getAttribute("sessionUser");

        cartService.장바구니에선택된값만넣어야함(updateDTOs, sessionUser.getId());
        // [CartRequest.UpdateDTO(cartId=13, buyQty=81), CartRequest.UpdateDTO(cartId=7, buyQty=20)]
        // DB에서 내 장바구니 내역 조회 (4건)
        // 13과 7이 아닌 애들은 삭제 -> 근데 체크 안 한 애들은 장바구니에 남아있어야하는거 아닐까요? 안 샀으니까...
        // 13과 7은 업데이트

        return "redirect:/order-save-form";
    }


    //장바구니에 담기
    @PostMapping("/cart/save")
    public String save(@RequestParam("productId") Integer productId, @RequestParam("buyQty") Integer buyQty) {
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

        List<CartResponse.CartDTO> cartList = cartService.getCartList(sessionUser.getId());
//        System.out.println("카트리스트 " + cartList);
        request.setAttribute("cartList", cartList);

        return "/cart/cart-form";

    }

}
