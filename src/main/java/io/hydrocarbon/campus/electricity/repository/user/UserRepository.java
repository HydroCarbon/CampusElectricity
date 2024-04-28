package io.hydrocarbon.campus.electricity.repository.user;

import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>,
        JpaSpecificationExecutor<UserEntity> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    Optional<UserEntity> findByUsername(String username);

    @Query("select distinct u from UserEntity u where u.roomId = ?1")
    List<UserEntity> findDistinctByRoomId(UUID roomId);
}
