package com.aifinancialanalyst.application.usecase;

import com.aifinancialanalyst.domain.exception.BusinessException;
import com.aifinancialanalyst.domain.model.User;
import com.aifinancialanalyst.domain.model.UserRole;
import com.aifinancialanalyst.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @org.springframework.transaction.annotation.Transactional
    public User execute(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email already in use: " + email);
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }
}