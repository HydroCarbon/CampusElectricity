package io.hydrocarbon.campus.electricity.service.user;

import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import io.hydrocarbon.campus.electricity.param.request.user.UserInfoRequest;
import io.hydrocarbon.campus.electricity.param.user.UserQueryParam;
import io.hydrocarbon.campus.electricity.repository.user.UserRepository;
import io.hydrocarbon.campus.electricity.util.UserUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       @Lazy
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * 分页查询用户
     *
     * @param userQueryParam 查询参数
     * @return 用户列表
     */
    public Page<UserEntity> page(UserQueryParam userQueryParam) {
        Specification<UserEntity> specification = (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();
            if (Objects.nonNull(userQueryParam.getName())) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.like(root.get("name"),
                        "%" + userQueryParam.getName() + "%"));
            }
            if (Objects.nonNull(userQueryParam.getStudentNo())) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.like(root.get("studentNo"),
                        "%" + userQueryParam.getStudentNo() + "%"));
            }
            if (Objects.nonNull(userQueryParam.getRoomId())) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("roomId"),
                        userQueryParam.getRoomId()));
            }
            return predicates;
        };

        return userRepository.findAll(specification, userQueryParam.toPageable());
    }

    /**
     * 编辑用户信息
     *
     * @param userId   用户 ID
     * @param userInfo 用户信息
     * @return 是否编辑成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean editInfo(String userId, UserInfoRequest userInfo) {
        var ref = new Object() {
            Boolean result = false;
        };
        userRepository.findById(UUID.fromString(userId))
                .ifPresent(user -> {
                    user.setUsername(userInfo.getUsername());
                    user.setStudentNo(userInfo.getStudentNo());
                    user.setName(userInfo.getName());
                    user.setRoomId(userInfo.getRoomId());
                    user.setPhone(userInfo.getPhone());
                    user.setEmail(userInfo.getEmail());
                    user.setRole(userInfo.getRole());
                    user.setUpdatedBy(UserUtil.getCurrentUserId());
                    userRepository.save(user);
                    ref.result = true;
                });
        return ref.result;
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户 ID
     * @return 用户信息
     */
    public UserEntity info(String userId) {
        return userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public Boolean changePassword(String userId, String password) {
        AtomicBoolean result = new AtomicBoolean(false);
        userRepository.findById(UUID.fromString(userId))
                .ifPresent(user -> {
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                    result.set(true);
                });
        return result.get();
    }
}
