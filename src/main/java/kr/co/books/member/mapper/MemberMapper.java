package kr.co.books.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.books.member.dto.RegisterDTO;

@Mapper
public interface MemberMapper {

	void register(RegisterDTO registerDTO);
}
