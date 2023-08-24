package com.tus.ecommerce.dao;

import com.tus.ecommerce.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
