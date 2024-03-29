package kr.or.connect.mavenweb.securityexam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.mavenweb.securityexam.dao.MemberDao;
import kr.or.connect.mavenweb.securityexam.dao.MemberRoleDao;
import kr.or.connect.mavenweb.securityexam.dto.Member;
import kr.or.connect.mavenweb.securityexam.dto.MemberRole;
import kr.or.connect.mavenweb.securityexam.service.security.UserEntity;
import kr.or.connect.mavenweb.securityexam.service.security.UserRoleEntity;

@Service
public class MemberServiceImpl implements MemberService {
	// 생성자에 위해 주입되는 객체이고, 해당 객체를 초기화할 필요가 이후에 없기 때문에 final로 선언하였다.
	// final로 선언하고 초기화를 안한 필드는 생성자에서 초기화를 해준다.
	private final MemberDao memberDao;
	private final MemberRoleDao memberRoleDao;

	// @Service가 붙은 객체는 스프링이 자동으로 Bean으로 생성하는데
	// 기본생성자가 없고 아래와 같이 인자를 받는 생성자만 있을 경우 자동으로 관련된 타입이 Bean으로 있을 경우 주입해서 사용하게 된다.
	public MemberServiceImpl(MemberDao memberDao, MemberRoleDao memberRoleDao) {
		this.memberDao = memberDao;
		this.memberRoleDao = memberRoleDao;
	}

	@Override
	@Transactional
	public UserEntity getUser(String loginUserId) {
		Member member = memberDao.getMemberByEmail(loginUserId);
		return new UserEntity(member.getEmail(), member.getPassword());
	}

	@Override
	@Transactional
	public List<UserRoleEntity> getUserRoles(String loginUserId) {
		List<MemberRole> memberRoles = memberRoleDao.getRolesByEmail(loginUserId);
		List<UserRoleEntity> list = new ArrayList<>();
		for (MemberRole memberRole : memberRoles) {
			list.add(new UserRoleEntity(loginUserId, memberRole.getRoleName()));
		}
		return list;
	}

	@Override
	@Transactional
	public Member getMemberByEmail(String loginId) {
		return memberDao.getMemberByEmail(loginId);
	}

	@Override
	@Transactional(readOnly = false)
	public Long addMember(Member member) {
		return memberDao.insertMember(member);
	}

	@Override
	public Long addMemberRole(Long memberId, String admin, String user) {
		return memberRoleDao.insertMemberRole(memberId, admin, user);
	}
}