package com.arsyux.arrow.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.arrow.domain.UserVO;
import com.arsyux.arrow.persistence.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// ========================================
	// 1. 회원가입
	// ========================================
	
	// 회원가입
	@Transactional
	public void insertUser(UserVO user) {
		// 비밀번호 암호화
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// 회원 정보 저장
		userDAO.insertUser(user);
	}
	
	// 회원 정보 수정
	@Transactional
	public UserVO updateUser(UserVO user) {
		
		UserVO findUser = userDAO.findById(user.getUserid());
		
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));

		userDAO.updateUser(findUser);
		return findUser;
	}
	
	// 비밀번호 변경
	@Transactional
	public UserVO changePassword(UserVO user) {
		UserVO findUser = userDAO.findById(user.getUserid());
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.changePassword(findUser);
		return findUser;
	}
	
}
