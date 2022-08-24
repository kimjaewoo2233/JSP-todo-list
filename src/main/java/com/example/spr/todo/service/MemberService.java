package com.example.spr.todo.service;


import com.example.spr.todo.dao.MapperUtil;
import com.example.spr.todo.dao.MemberDAO;
import com.example.spr.todo.domain.MemberVO;
import com.example.spr.todo.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService { //MemberService는 여러 곳에서도 동이랗ㄴ 객체로 사용할 수 있도록 enum으로 하나의 객체만을 구성하고
                            //MemberDAO를 이용하도록 구성

        INSTANCE;

        private MemberDAO dao;
        private ModelMapper modelMapper;

        MemberService(){
            dao = new MemberDAO();
            modelMapper = MapperUtil.INSTANCE.get();
        }

        public MemberDTO login(String mid, String mpw)throws Exception{
            MemberVO vo = dao.getWithPassword(mid,mpw);

            MemberDTO memberDTO = modelMapper.map(vo,MemberDTO.class);

            return memberDTO;
        }

        public void updateUuid(String mid,String uuid)throws Exception{
            dao.updateUuid(mid,uuid);
        }


        public MemberDTO getByUUId(String uuid) throws  Exception{
            MemberVO vo = dao.selectUUID(uuid);
            MemberDTO memberDTO = modelMapper.map(vo,MemberDTO.class);
            return memberDTO;
        }
}
