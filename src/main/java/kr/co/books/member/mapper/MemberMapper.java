package kr.co.books.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.books.member.domain.Member;
import kr.co.books.member.dto.RegisterDTO;
import kr.co.books.member.dto.RegisterRequestDTO;

@Mapper
public interface MemberMapper {

	void register(Member member);
}
