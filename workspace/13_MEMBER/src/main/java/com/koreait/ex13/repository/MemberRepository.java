package com.koreait.ex13.repository;

import org.springframework.stereotype.Repository;

import com.koreait.ex13.domain.Member;

@Repository
public interface MemberRepository {
	
	// 아이디 중복 체크
	public Member selectMemberById(String id);
	// 회원가입
	public Member joinMember(Member member);
	// 로그인
	public Member login(Member member);
	// 아이디 찾기
	public Member selectMemberByEmail(String email);
	// 비밀번호 찾기
	public Member updatePw(Member member);
	// 내 정보 바꾸기
	public Member updateMember(Member member);
	// 회원탈퇴
	public long leaveMember(long state);
	

}
