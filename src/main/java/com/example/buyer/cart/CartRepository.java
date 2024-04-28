package com.example.buyer.cart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CartRepository {
    private final EntityManager em;

    //장바구니 삭제
    public void deleteById(Integer id) {
        String q = """
                delete from cart_tb where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();

    }


    //장바구니 목록 보기
    public List<CartResponse.CartDTO> findAll() {
        String q = """
                select c.id, c.buy_qty, p.img_file_name, p.name, p.price from cart_tb c 
                inner join product_tb p 
                on c.product_id = p.id 
                order by c.id desc;
                """;
        Query query = em.createNativeQuery(q);

        List<Object[]> rows = query.getResultList();
        List<CartResponse.CartDTO> cartList = new ArrayList<>();

        for (Object[] row : rows) {
            Integer id = (Integer) row[0];
            Integer buyQty = (Integer) row[1];
            String imgFileName = (String) row[2];
            String pName = (String) row[3];
            Integer price = (Integer) row[4];

            CartResponse.CartDTO cartDTO = CartResponse.CartDTO.builder()
                    .id(id)
                    .buyQty(buyQty)
                    .imgFileName(imgFileName)
                    .pName(pName)
                    .price(price)
                    .build();

            cartList.add(cartDTO);

        }

//        System.out.println("레파 : " + cartList);

        return cartList;
    }


    //장바구니 담기
    public void save(Integer userId, Integer productId, Integer buyQty) {
        String q = """
                insert into cart_tb (user_id, product_id, buy_qty, created_at) values (?, ?, ?, now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, productId);
        query.setParameter(3, buyQty);
        query.executeUpdate();

    }

}
