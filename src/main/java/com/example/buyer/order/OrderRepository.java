package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    //buy-list 조회용
    public List<OrderResponse.ListDTO> findAll() {
        String q = """
                select o.id, o.buy_qty, o.payment, o.sum, o.created_at, p.name 
                from order_tb o 
                inner join product_tb p on o.product_id = p.id 
                order by o.id desc;
                """;
        Query query = em.createNativeQuery(q);

        //Object 배열 타입으로 받아야함.
        List<Object[]> rows = (List<Object[]>) query.getResultList();
        List<OrderResponse.ListDTO> orderList = new ArrayList<>();

        for (Object[] row : rows) {
            //listDTO
            Integer id = (Integer) row[0];
            Integer buyQty = (Integer) row[1];
            String payment = (String) row[2];
            Integer sum = (Integer) row[3];
            LocalDate createdAt = ((Timestamp) row[4]).toLocalDateTime().toLocalDate();
            String name = (String) row[5];

            OrderResponse.ListDTO listDTO = OrderResponse.ListDTO.builder()
                    .id(id)
                    .buyQty(buyQty)
                    .payment(payment)
                    .sum(sum)
                    .createdAt(createdAt)
                    .name(name)
                    .build();

            orderList.add(listDTO);
        }

        System.out.println("db값 확인용..." + orderList);

        return orderList;

    }
}
