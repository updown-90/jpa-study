package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest3 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("updown");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 비영속 상태
			Member member = new Member();
			member.setId(100L);
			member.setName("HelloJPA");

			// 영속 상태 (entityManager)에 올라가있음
			// 1차 캐시에 저장됨
			em.persist(member);

			Member findMember = em.find(Member.class, 100L); // 1차 캐시에서 조회
			Member findMember1 = em.find(Member.class, 100L); // 1차 캐시에서 조회
			System.out.println("equal : "+findMember.equals(findMember1)); // 동일성 보장


			Member findMember2 = em.find(Member.class, 200L); // 데이터베이스에서 조회

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
