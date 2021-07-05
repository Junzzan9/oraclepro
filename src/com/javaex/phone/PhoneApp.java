package com.javaex.phone;

import java.util.List;
import java.util.Scanner;


public class PhoneApp {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	List<PhoneVo> personList;
	PhoneDao phoneDao = new PhoneDao();
		System.out.println("*************************************");
		System.out.println("*        전화번호 관리 프로그램           *");
		System.out.println("*************************************");
		System.out.println("");
			
		
		
		while(true) {
			System.out.println("1.리스트\t2.등록\t3.수정\t4.삭제\t5.검색\t6.종료");
			System.out.println("-------------------------------------");
			System.out.print(">메뉴번호: ");
			int n=sc.nextInt();
			if(n==1) {
				personList = phoneDao.getPersonList();
				printList(personList);
				}
			else if(n==2) {
				System.out.print("이름>  ");
				String name=sc.next();
				System.out.println("");
				System.out.print("휴대전화>  ");
				String hp=sc.next();
				System.out.println("");
				System.out.print("회사번호>  ");
				String com=sc.next();
				
				PhoneVo addvo=new PhoneVo(name,hp,com);
				phoneDao.personInsert(addvo);
				
			}
			else if(n==3) {
				System.out.print("번호>  ");
				String personId = sc.next();
				System.out.println("");
				System.out.print("이름>  ");
				String name=sc.next();
				System.out.println("");
				System.out.print("휴대전화>  ");
				String hp=sc.next();
				System.out.println("");
				System.out.print("회사번호>  ");
				String com=sc.next();
				PhoneVo upVo=new PhoneVo(personId,name,hp,com);
				
				int count=phoneDao.personUpdate(upVo);
				if(count>0) {
					System.out.println("[등록되었습니다.]");
				}else {
					System.out.println("[관리자에게 문의하세요.]");
				}
			}
			else if(n==4) {
				System.out.print("번호>  ");
				int personId = sc.nextInt();
				System.out.println("");
				
				phoneDao.personDelete(personId);
			}
			else if(n==5) {
				System.out.print("검색어>   ");
				String srch =sc.next();
				List<PhoneVo> searchList=phoneDao.personSearch(srch);
				printList(searchList);
			}
			else if(n==6) {
				System.out.println("*************************************");
				System.out.println("*               감사합니다             *");
				System.out.println("*************************************");
				break;
			}
			else {
				System.out.println("[다시 입력해주세요.]");
			}
	
	}
	sc.close();

}

	public static void printList(List<PhoneVo> phoneList) {
		for (int i = 0; i < phoneList.size(); i++) {
			PhoneVo phoneVO = phoneList.get(i);
			System.out.println(phoneVO.getPersonId() + "\t" + phoneVO.getPersonName() + "\t" + phoneVO.getPersonHp()
					+ "\t" + phoneVO.getPersonCom());
			System.out.println("");
		}
	}
}
