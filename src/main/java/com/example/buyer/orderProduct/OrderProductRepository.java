package com.example.buyer.orderProduct;

import com.example.buyer.order.OrderRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Repository
public class OrderProductRepository {
    private final EntityManager em;

    //구매하기 !!
    public void save(OrderProductRequest.SaveDTO requestDTO) {
        String q = """
                insert into order_product_tb (name, buy_qty, order_id, created_at) 
                values (?, ?, ?, now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getPName());    //상품명
        query.setParameter(2, requestDTO.getBuyQty());
        query.setParameter(3, requestDTO.getOrderId());
        query.executeUpdate();
    }

    //상품을 구매하면 재고 차감
    public void updateQty(OrderProductRequest.SaveDTO requestDTO) {
        String q = """
                update product_tb set qty = qty - ? where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getBuyQty());
        query.setParameter(2, requestDTO.getProductId());
        query.executeUpdate();
    }


}
