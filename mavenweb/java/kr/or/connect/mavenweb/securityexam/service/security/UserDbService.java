package kr.or.connect.mavenweb.securityexam.service.security;

import java.util.List;

import kr.or.connect.mavenweb.securityexam.dto.Member;

public interface UserDbService {
	public UserEntity getUser(String loginUserId);

	public List<UserRoleEntity> getUserRoles(String loginUserId);

	public Member getMemberByEmail(String loginId);

	public Long addMember(Member member);

	public Long addMemberRole(Long memberId, String admin, String user);
}