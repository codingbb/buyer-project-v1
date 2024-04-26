package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;

    // 유저 조회
    public User findByUserId(Integer id) {
        String q = """
                select * from user_tb where id = ?
                """;
        Query query = em.createNativeQuery(q, User.class);
        query.setParameter(1, id);
        User user = (User) query.getSingleResult();
        return user;
    }

    //상품 조회
    public Product findByProductId(Integer id) {
        String q = """
                select * from product_tb where id = ?
                """;
        Query query = em.createNativeQuery(q, Product.class);
        query.setParameter(1, id);
        Product product = (Product) query.getSingleResult();
        return product;
    }


    public void save(OrderRequest.DTO requestDTO) {
        String q = """
                insert into order_tb (user_id, product_id, buy_qty, sum, payment, created_at) values (?, ?, ?, ?, ?, now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getUserId());
        query.setParameter(2, requestDTO.getProductId());
        query.setParameter(3, requestDTO.getBuyQty());
        query.setParameter(4, requestDTO.getSum());
        query.setParameter(5, requestDTO.getPayment());

        query.executeUpdate();

    }
}
