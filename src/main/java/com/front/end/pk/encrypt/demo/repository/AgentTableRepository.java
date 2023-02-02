package com.front.end.pk.encrypt.demo.repository;

import com.front.end.pk.encrypt.demo.model.AgentTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgentTableRepository extends JpaRepository<AgentTable,Integer> {
    Optional<AgentTable> findAgentTableByAgentId(Integer id);
    Optional<List<AgentTable>> findAgentTableByEmailAddress(String email);

    Optional<AgentTable> findAgentTableByCreditNumber(String creditNumber);

    Optional<AgentTable> findAgentTableByUserName(String UserName);


}
