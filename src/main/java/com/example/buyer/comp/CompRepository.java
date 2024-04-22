package com.example.buyer.comp;

import com.example.buyer.user.UserRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CompRepository {
    private final EntityManager em;

    public void save(CompRequest.JoinDTO requestDTO) {
        String q = """
                insert into user_tb 
                (username, password, comp_name, name, comp_num, phone, birth, address, role, created_at) 
                values (?, ?, ?, ?, ?, ?, ?, ?, ?, now())
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getCompName());
        query.setParameter(4, requestDTO.getName());
        query.setParameter(5, requestDTO.getCompNum());
        query.setParameter(6, requestDTO.getPhone());
        query.setParameter(7, requestDTO.getBirth());
        query.setParameter(8, requestDTO.getAddress());
        query.setParameter(9, requestDTO.getRole());

        query.executeUpdate();

    }

}
