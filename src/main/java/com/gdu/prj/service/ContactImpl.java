package com.gdu.prj.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.gdu.prj.dao.ContactDao;
import com.gdu.prj.dto.ContactDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContactImpl implements ContactService {
  
  private final ContactDao contactDao;

  @Override
  public void registerContact(HttpServletRequest request, HttpServletResponse response) {
      ContactDto contactDto = ContactDto.builder()
          .name(request.getParameter("name"))
          .mobile(request.getParameter("mobile"))
          .email(request.getParameter("email"))
          .address(request.getParameter("address"))
          .build();
      
      int insertCount = contactDao.registerContact(contactDto);
      
      response.setContentType("text/html; charset=UTF-8");
      try {
        PrintWriter out = response.getWriter();
        out.println("<script>");
        if(insertCount == 1) {
          out.println("alert('연락처 등록되었습니다.')");
          out.println("location.href='" + request.getContextPath() + "/contact/list.do'");
        } else {
          out.println("alert('연락처가 등록되지 않았습니다.')");
          out.println("history.back()");
        }
        out.println("</script>");
        out.flush();
        out.close();
      } catch(Exception e) {
        e.printStackTrace();
      }
  }

  @Override
  public void modifyContact(HttpServletRequest request, HttpServletResponse response) {
    
    int contactNo = Integer.parseInt(request.getParameter("contact-no"));
    ContactDto contactDto = ContactDto.builder()
        .contactNo(contactNo)
        .name(request.getParameter("name"))
        .mobile(request.getParameter("mobile"))
        .email(request.getParameter("email"))
        .address(request.getParameter("address"))
        .build();
    
    int updateCount = contactDao.modifyContact(contactDto);

    response.setContentType("text/html; charset=UTF-8");
    try {
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if(updateCount == 1) {
        out.println("alert('연락처가 수정되었습니다.')");
        out.println("location.href='" + request.getContextPath() + "/contact/detail.do?contact-no=" + contactNo + "'");
      } else {
        out.println("alert('연락처가 수정되지 않았습니다.')");
        out.println("history.back()");
      }
      out.println("</script>");
      out.flush();
      out.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
    

  }

  @Override
  public void removeContact(HttpServletRequest request, HttpServletResponse response) {
    int contactNo = Integer.parseInt(request.getParameter("contact-no"));
    
    int deleteCount = contactDao.removeContact(contactNo);
    
    
    response.setContentType("text/html; charset=UTF-8");
    try {
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if(deleteCount == 1) {
        out.println("alert('연락처가 삭제되었습니다.')");
        out.println("location.href='" + request.getContextPath() + "/contact/list.do'");  // redirect 를 의미하는 코드
      } else {
        out.println("alert('연락처가 삭제되지 않았습니다.')");
        out.println("history.back()");
      }
      out.println("</script>");
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<ContactDto> getContactList() {
    return contactDao.getContactList();
  }

  @Override
  public ContactDto getContactByNo(int contactNo) {
    return contactDao.getContactByNo(contactNo);
  }

}
