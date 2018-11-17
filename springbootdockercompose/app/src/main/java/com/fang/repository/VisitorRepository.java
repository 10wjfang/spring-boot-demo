package com.fang.repository;

import com.fang.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/5/7 11:43
 * @Modified by:
 */
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    Visitor findByIp(String ip);
}
