package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("updown");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 삽입
			// Member member = new Member();
			// member.setId(2L);
			// member.setName("이름2");

			//조회
			Member findMember = em.find(Member.class, 1L);
			System.out.println(findMember.getId());
			System.out.println(findMember.getName());

			//삭제
			// em.remove(findMember);

			//수정
			// findMember.setName("HelloJPA");

			// // JPQL
			// List<Member> memberList = em.createQuery("select m from Member as m", Member.class)
			// 	.setFirstResult(1) // 페이징처리
			// 	.setMaxResults(10) // 페이징처리
			// 	.getResultList();

			// for (Member member : memberList) {
			// 	System.out.println(member);
			// }

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
