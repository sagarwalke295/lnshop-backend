package com.jctiru.lnshop.api.io.repository;

import org.springframework.data.repository.CrudRepository;

import com.jctiru.lnshop.api.io.entity.PasswordResetTokenEntity;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long> {

	PasswordResetTokenEntity findPasswordResetTokenByUser_Email(String email);

	PasswordResetTokenEntity findPasswordResetTokenByToken(String token);

}
